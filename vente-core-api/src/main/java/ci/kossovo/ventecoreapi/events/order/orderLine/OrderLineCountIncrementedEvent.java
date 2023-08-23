package ci.kossovo.ventecoreapi.events.order.orderLine;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderLineCountIncrementedEvent {

  private final String orderId;
  private final String codeProduit;
}
