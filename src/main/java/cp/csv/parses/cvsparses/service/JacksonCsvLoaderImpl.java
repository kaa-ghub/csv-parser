package cp.csv.parses.cvsparses.service;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import cp.csv.parses.cvsparses.model.CsvFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

import static com.fasterxml.jackson.dataformat.csv.CsvParser.Feature.WRAP_AS_ARRAY;

@Service
@Primary
@Slf4j
public class JacksonCsvLoaderImpl implements CsvLoader {
    @Override
    public Iterator<String[]> loadObjectList(String fileName) {
        CsvMapper mapper = new CsvMapper();
        mapper.enable(WRAP_AS_ARRAY);
        try {
            File file = new ClassPathResource(fileName).getFile();
            return mapper.readerFor(String[].class).readValues(file);
        } catch (IOException e) {
            log.error("error", e);
            return Collections.emptyIterator();
        }
    }

}
