package cp.csv.parses.cvsparses.repository;

import cp.csv.parses.cvsparses.model.CsvFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CsvFileRepository extends JpaRepository<CsvFile, Long> {
}
