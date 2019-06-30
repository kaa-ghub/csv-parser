package cp.csv.parses.cvsparses.support;

import cp.csv.parses.cvsparses.dto.ResponseDto;
import cp.csv.parses.cvsparses.model.CsvFile;
import cp.csv.parses.cvsparses.model.FileString;
import cp.csv.parses.cvsparses.model.Result;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collection;

@Component
public class FileResultToResponseConverter implements Converter<CsvFile, Collection<ResponseDto>> {

    @Override
    public Collection<ResponseDto> convert(CsvFile csvFile) {
        Collection<ResponseDto> responseDtos = new ArrayList<>();
        for (FileString fileString: csvFile.getFileStrings()) {
            ResponseDto responseDto = new ResponseDto();
            if (fileString.getOrder() != null ) {
                responseDto.setId(fileString.getOrder().getOrderId());
                responseDto.setAmount(fileString.getOrder().getAmount().toString());
                responseDto.setCurrency(fileString.getOrder().getCurrency().toString());
                responseDto.setComment(fileString.getOrder().getComment());
            }
            responseDto.setFilename(csvFile.getFilePath());
            responseDto.setLine(fileString.getLineNumber());

            if (Result.OK.equals(fileString.getResult())) {
                responseDto.setResult(fileString.getResult().toString());
            } else {
                responseDto.setResult(fileString.getError());
            }
            responseDtos.add(responseDto);
        }
        return responseDtos;
    }
}
