package ci.kossovo.ventecoreapi.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderLineRemoveRequest {

  private String orderId;
  private String codeProduit;
  // private Double total;
  // private String orderLineId;
}
