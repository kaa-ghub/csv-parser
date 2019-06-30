package cp.csv.parses.cvsparses.support;

import cp.csv.parses.cvsparses.dto.InputDto;
import cp.csv.parses.cvsparses.dto.InputWithFileDto;
import org.springframework.stereotype.Component;

@Component
public class StringsArrayToInputDtoConverter {
    public InputWithFileDto convert(String[] elements, Long line, String file) {
        InputWithFileDto dto = new InputWithFileDto();
        dto.setOrderId(elements[0]);
        dto.setOrderSum(elements[1]);
        dto.setOrderCurrency(elements[2]);
        dto.setComment(elements[3]);
        dto.setLine(line);
        dto.setFile(file);
        return dto;
    }
}
