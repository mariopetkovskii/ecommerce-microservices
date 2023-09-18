package petkovskimariobachelor.orderservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import petkovskimariobachelor.orderservice.entity.Order;
import petkovskimariobachelor.orderservice.entity.OrderId;

public interface OrderRepository extends JpaRepository<Order, OrderId> {
}
