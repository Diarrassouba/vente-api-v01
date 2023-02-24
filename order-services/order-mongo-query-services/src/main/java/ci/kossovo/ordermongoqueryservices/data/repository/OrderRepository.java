package ci.kossovo.ordermongoqueryservices.data.repository;

import ci.kossovo.ordermongoqueryservices.data.documents.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, String> {}
