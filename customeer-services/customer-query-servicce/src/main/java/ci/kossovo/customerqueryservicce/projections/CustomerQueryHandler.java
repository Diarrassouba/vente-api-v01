package ci.kossovo.customerqueryservicce.projections;

import ci.kossovo.customerqueryservicce.models.CustomerQueryResponse;
import ci.kossovo.customerqueryservicce.services.CustomerService;
import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import ci.kossovo.ventecoreapi.queries.customers.GetAllCustomerQuery;
import ci.kossovo.ventecoreapi.queries.customers.GetCustomerByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerQueryHandler {

  private final CustomerService customerService;

  @QueryHandler
  public CustomerQueryResponse findAllCustomers(GetAllCustomerQuery query) {
    return new CustomerQueryResponse(customerService.findAllCustomer());
  }

  @QueryHandler
  public CustomerDto findCustomersById(GetCustomerByIdQuery query) {
    return customerService.getByIdCustomer(query.getCustomerId());
  }
}
