package cp.csv.parses.cvsparses.dto;

import lombok.Data;

@Data
public class InputWithFileDto extends InputDto{
    private Long line;
    private String file;
}
