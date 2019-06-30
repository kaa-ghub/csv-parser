package cp.csv.parses.cvsparses.service;

import cp.csv.parses.cvsparses.dto.InputWithFileDto;
import cp.csv.parses.cvsparses.dto.ResponseDto;
import cp.csv.parses.cvsparses.model.CsvFile;
import cp.csv.parses.cvsparses.model.FileString;
import cp.csv.parses.cvsparses.model.Order;
import cp.csv.parses.cvsparses.model.Result;
import cp.csv.parses.cvsparses.repository.CsvFileRepository;
import cp.csv.parses.cvsparses.repository.OrderRepository;
import cp.csv.parses.cvsparses.support.FileResultToResponseConverter;
import cp.csv.parses.cvsparses.support.InputDtoToOrderConverter;
import cp.csv.parses.cvsparses.support.StringsArrayToInputDtoConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

@Service
public class InputDtoDataLoaderImpl implements DataLoader<InputWithFileDto> {
    private final StringsArrayToInputDtoConverter stringsArrayToInputDtoConverter;
    private final InputDtoToOrderConverter inputDtoToOrderConverter;
    private final OrderRepository orderRepository;
    private final CsvFileRepository csvFileRepository;
    private final FileResultToResponseConverter fileResultToResponseConverter;

    @Autowired
    public InputDtoDataLoaderImpl(StringsArrayToInputDtoConverter stringsArrayToInputDtoConverter, InputDtoToOrderConverter inputDtoToOrderConverter, OrderRepository orderRepository, CsvFileRepository csvFileRepository, FileResultToResponseConverter fileResultToResponseConverter) {
        this.stringsArrayToInputDtoConverter = stringsArrayToInputDtoConverter;
        this.inputDtoToOrderConverter = inputDtoToOrderConverter;
        this.orderRepository = orderRepository;
        this.csvFileRepository = csvFileRepository;
        this.fileResultToResponseConverter = fileResultToResponseConverter;
    }

    @Override
    public Collection<InputWithFileDto> load(Iterator<String[]> iterator, String file) {
        Collection<InputWithFileDto> list = new ArrayList<>();
        Long line = new Long(0);
        while(iterator.hasNext()) {
            String[] elements = iterator.next();
            line++;
            list.add(stringsArrayToInputDtoConverter.convert(elements, line, file));
        }
        return list;
    }

    @Override
    public Collection<ResponseDto> handleRequest(Collection<InputWithFileDto> data) {
        CsvFile csvFile = new CsvFile();
        Collection<FileString> fileStrings = new ArrayList<>();
        for(InputWithFileDto dto: data) {
            FileString fileString = new FileString();
            Order order = null;
            try {
                order = inputDtoToOrderConverter.convert(dto);
                fileString.setResult(Result.OK);
            } catch (Exception e) {
                fileString.setError(e.toString());
                fileString.setResult(Result.ERROR);
            }
            fileString.setLineNumber(dto.getLine());
            fileString.setOrder(order);
            fileStrings.add(fileString);
            csvFile.setFilePath(dto.getFile());
        }
        csvFile.setFileStrings(fileStrings);
        csvFileRepository.save(csvFile);
        return fileResultToResponseConverter.convert(csvFile);
    }
}
