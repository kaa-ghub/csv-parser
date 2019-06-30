package cp.csv.parses.cvsparses.support;

import cp.csv.parses.cvsparses.dto.InputDto;
import cp.csv.parses.cvsparses.model.Order;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Currency;

@Component
public class InputDtoToOrderConverter implements Converter<InputDto, Order> {
    @Override
    public Order convert(InputDto inputDto) {
        Order order = new Order();
        order.setOrderId(inputDto.getOrderId());
        order.setAmount(new BigDecimal(inputDto.getOrderSum()));
        order.setCurrency(Currency.getInstance(inputDto.getOrderCurrency()));
        order.setComment(inputDto.getComment());
        return order;
    }
}
