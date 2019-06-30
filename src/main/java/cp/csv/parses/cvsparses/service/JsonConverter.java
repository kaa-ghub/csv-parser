package cp.csv.parses.cvsparses.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import cp.csv.parses.cvsparses.dto.ResponseDto;

import java.io.IOException;
import java.util.Collection;

public interface JsonConverter<T> {
    String toJson(Collection<T> collection) throws IOException;
}
