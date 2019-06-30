package cp.csv.parses.cvsparses.repository;

import cp.csv.parses.cvsparses.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
