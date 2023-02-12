package ci.kossovo.ordercommandservices.reposotories;

import org.springframework.data.jpa.repository.JpaRepository;
import ci.kossovo.ordercommandservices.models.OrderCustomer;

public interface CustomerRepository extends JpaRepository<OrderCustomer, String> {
  
}
