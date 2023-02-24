package ci.kossovo.orderqueryservices.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.orderqueryservices.data.entities.Order;

public interface OrderRepository extends JpaRepository<Order, String> {
  Optional<Order> findByOrderId(String orderId);
}
