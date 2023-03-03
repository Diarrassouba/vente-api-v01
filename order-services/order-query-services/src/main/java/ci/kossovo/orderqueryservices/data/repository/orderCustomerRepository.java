package ci.kossovo.orderqueryservices.data.repository;

import ci.kossovo.orderqueryservices.data.entities.OrderCustomer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface orderCustomerRepository
  extends JpaRepository<OrderCustomer, String> {}
