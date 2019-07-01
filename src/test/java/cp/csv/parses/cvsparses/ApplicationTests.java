package cp.csv.parses.cvsparses;

import com.fasterxml.jackson.core.JsonProcessingException;
import cp.csv.parses.cvsparses.dto.InputWithFileDto;
import cp.csv.parses.cvsparses.dto.ResponseDto;
import cp.csv.parses.cvsparses.model.Result;
import cp.csv.parses.cvsparses.service.CsvLoader;
import cp.csv.parses.cvsparses.service.DataLoader;
import cp.csv.parses.cvsparses.service.JsonConverter;
import cp.csv.parses.cvsparses.support.InputDtoToOrderConverter;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
	@Autowired
	private CsvLoader parser;
	@Autowired
	private InputDtoToOrderConverter inputDtoToOrderConverter;
	@Autowired
	DataLoader<InputWithFileDto> dataLoader;
	@Autowired
	JsonConverter jsonConverter;

	private final String VALID_STRING = "1,100,USD,оплата заказа";

	@Test
	public void contextLoads() {
	}

	@Test
	public void whenLoadingFromCsvFile_thenLoadStringArray() throws IOException {
		File file = new ClassPathResource("cvsValid.cvs").getFile();
		Iterator<String[]> strings = parser.loadObjectList(file);
		String line;
		String[] expected = VALID_STRING.split(",");
		Assert.assertTrue(strings.hasNext());
		Assert.assertArrayEquals(expected, strings.next());
	}

	@Test
	public void whenLoadingFromNotValidCsvFile_thenLoadStringArrayWithError() throws IOException {
		File file = new ClassPathResource("cvsNotValid.cvs").getFile();
		Iterator<String[]> strings = parser.loadObjectList(file);
		String line;
		String[] expected = VALID_STRING.split(",");
		Collection<InputWithFileDto> collection = dataLoader.load(strings, "cvsValid.cvs");
		Collection<ResponseDto> responseDtos = dataLoader.handleRequest(collection);
		Assert.assertEquals(1, responseDtos.size());
		Assert.assertNotEquals(Result.OK, responseDtos.iterator().next().getResult());
	}

	@Test
	public void whenLoadingOrdersFromCsvFile_thenLoadInputList() throws IOException {
		String fileString = "cvsValid.cvs";
		File file = new ClassPathResource(fileString).getFile();
		Iterator<String[]> strings = parser.loadObjectList(file);
		Collection<InputWithFileDto> collection = dataLoader.load(strings, fileString);
		Collection<ResponseDto> responseDtos = dataLoader.handleRequest(collection);
		String json = jsonConverter.toJson(responseDtos);
		Assert.assertNotNull(json);

	}

}

