package cp.csv.parses.cvsparses.service;

import com.opencsv.CSVReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Iterator;

@Service
@Slf4j
public class OpenCsvLoaderImpl implements CsvLoader {
    @Override
    public Iterator<String[]> loadObjectList(File file) {
        try {
            try (CSVReader reader = new CSVReader(new FileReader(file))) {
                return reader.iterator();
            }
        } catch (IOException e) {
            log.error("error", e);
            return Collections.emptyIterator();
        }
    }
}
