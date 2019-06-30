package cp.csv.parses.cvsparses.service;

import cp.csv.parses.cvsparses.dto.ResponseDto;

import java.util.Collection;
import java.util.Iterator;

public interface DataLoader<T> {
    Collection<T> load(Iterator<String[]> array, String file);
    Collection<ResponseDto> handleRequest(Collection<T> data);
}
