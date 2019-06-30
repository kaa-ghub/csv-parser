package cp.csv.parses.cvsparses.dto;

import lombok.Data;

@Data
public class InputDto {
    private String orderId;
    private String orderSum;
    private String orderCurrency;
    private String comment;
}
