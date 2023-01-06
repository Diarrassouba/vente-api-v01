package ci.kossovo.ventecoreapi.events.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerCorrectedEvent {

  private String customerId;

  private String tel;
  private String cel;
  private String email;
  private String quartier;
  private String ville;
}
