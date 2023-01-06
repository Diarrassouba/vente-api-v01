package ci.kossovo.ventecoreapi.events.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerForgottenEvent {

  private String customerId;

  private Boolean disable;
}
