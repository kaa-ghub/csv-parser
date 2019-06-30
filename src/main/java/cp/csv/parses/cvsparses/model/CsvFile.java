package cp.csv.parses.cvsparses.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
public class CsvFile {
    @Id
    @GeneratedValue
    private Long id;
    private String filePath;
    @OneToMany(targetEntity = FileString.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    Collection<FileString> fileStrings;
}
