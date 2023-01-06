package ci.kossovo.customerqueryservicce.comtrollers;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kossovo.customerqueryservicce.models.CustomerQueryResponse;
import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import ci.kossovo.ventecoreapi.queries.customers.GetAllCustomerQuery;
import ci.kossovo.ventecoreapi.queries.customers.GetCustomerByIdQuery;

@RestController
// @CrossOrigin
@RequestMapping("/customers")
public class CustomerQueryController {

  @Autowired
  private QueryGateway queryGateway;

  @GetMapping
  public CustomerQueryResponse findAllCustomer() {
    GetAllCustomerQuery query = new GetAllCustomerQuery();

    try {
      return queryGateway
        .query(query, ResponseTypes.instanceOf(CustomerQueryResponse.class))
        .get();
    } catch (Exception e) {
      return new CustomerQueryResponse(null);
    }
  }

  @GetMapping("/{id}")
  public CustomerDto findById(@PathVariable String id) {
    GetCustomerByIdQuery query = new GetCustomerByIdQuery(id);

    return queryGateway
      .query(query, ResponseTypes.instanceOf(CustomerDto.class))
      .join();
  }
}
