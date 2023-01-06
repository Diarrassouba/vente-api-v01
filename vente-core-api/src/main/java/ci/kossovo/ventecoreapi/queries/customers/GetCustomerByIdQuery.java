package ci.kossovo.ventecoreapi.queries.customers;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerByIdQuery {

  private String customerId;
}
