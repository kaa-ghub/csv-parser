package cp.csv.parses.cvsparses.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import cp.csv.parses.cvsparses.config.AppProperties;
import cp.csv.parses.cvsparses.dto.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Component
public class JsonConverterImpl implements JsonConverter<ResponseDto> {
    private final AppProperties appProperties;

    @Autowired
    public JsonConverterImpl(AppProperties appProperties) {
        this.appProperties = appProperties;
    }

    @Override
    public String toJson(Collection<ResponseDto> responseDtos) throws IOException {
        ObjectWriter objectWriter = new ObjectMapper().writer().withDefaultPrettyPrinter();
        objectWriter.writeValue(new File(appProperties.getOutput()), responseDtos);
        return objectWriter.writeValueAsString(responseDtos);
    }
}
