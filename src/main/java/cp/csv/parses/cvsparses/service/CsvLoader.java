package cp.csv.parses.cvsparses.service;

import java.io.File;
import java.util.Iterator;

public interface CsvLoader {
    Iterator<String[]> loadObjectList(File file);
}
