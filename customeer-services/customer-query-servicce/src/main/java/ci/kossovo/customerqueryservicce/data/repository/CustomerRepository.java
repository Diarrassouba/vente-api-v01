package ci.kossovo.customerqueryservicce.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.customerqueryservicce.data.documents.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> {
  // Optional<Customer> findByCustomerId(String id);
}
