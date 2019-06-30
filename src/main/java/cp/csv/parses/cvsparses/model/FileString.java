package cp.csv.parses.cvsparses.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class FileString {
    @Id
    @GeneratedValue
    private Long id;
    private Long lineNumber;
    @OneToOne(targetEntity = Order.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Order order;
    private String error;
    private Result result;
}
