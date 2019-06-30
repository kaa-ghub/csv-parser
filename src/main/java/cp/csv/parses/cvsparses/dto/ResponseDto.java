package cp.csv.parses.cvsparses.dto;

import lombok.Data;

@Data
public class ResponseDto {
    private String id;
    private String amount;
    private String currency;
    private String comment;
    private String filename;
    private Long line;
    private String result;
}
