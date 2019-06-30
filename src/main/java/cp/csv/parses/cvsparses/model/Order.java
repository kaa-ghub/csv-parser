package cp.csv.parses.cvsparses.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.math.BigDecimal;
import java.util.Currency;

@Entity(name = "order_table")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    private Long id;
    @NonNull
    private String orderId;
    @NonNull
    private BigDecimal amount;
    @NonNull
    private Currency currency;
    @NonNull
    private String comment;
}
