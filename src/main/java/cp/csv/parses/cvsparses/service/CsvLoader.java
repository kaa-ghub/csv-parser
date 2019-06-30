package cp.csv.parses.cvsparses.service;

import java.util.Iterator;

public interface CsvLoader {
    public Iterator<String[]> loadObjectList(String fileName);
}
