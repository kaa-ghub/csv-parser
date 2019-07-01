package cp.csv.parses.cvsparses.service;

import cp.csv.parses.cvsparses.config.AppProperties;
import cp.csv.parses.cvsparses.dto.InputWithFileDto;
import cp.csv.parses.cvsparses.dto.ResponseDto;
import cp.csv.parses.cvsparses.support.InputDtoToOrderConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.io.File;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;

@Component
public class MainService {
    @Autowired
    private CsvLoader parser;
    @Autowired
    private InputDtoToOrderConverter inputDtoToOrderConverter;
    @Autowired
    DataLoader<InputWithFileDto> dataLoader;
    @Autowired
    JsonConverter jsonConverter;
    @Autowired
    private ApplicationArguments arguments;
    @Autowired
    AppProperties appProperties;

    @EventListener({ContextRefreshedEvent.class})
    void parse() throws IOException {
        File file = null;
        if (arguments.getSourceArgs().length > 0) {
            String sourcePath = arguments.getSourceArgs()[0];
            file = new File(sourcePath);
        } else {
            file = new ClassPathResource(appProperties.getInputFile()).getFile();
        }

        Iterator<String[]> strings = parser.loadObjectList(file);
        Collection<InputWithFileDto> collection = dataLoader.load(strings, file.getName());
        Collection<ResponseDto> responseDtos = dataLoader.handleRequest(collection);
        System.out.println(jsonConverter.toJson(responseDtos));
    }
}
