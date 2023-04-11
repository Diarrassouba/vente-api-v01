package ci.kossovo.ordermongoqueryservices.data.repository;

import ci.kossovo.ordermongoqueryservices.data.documents.OrderCustomer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository
  extends MongoRepository<OrderCustomer, String> {}
