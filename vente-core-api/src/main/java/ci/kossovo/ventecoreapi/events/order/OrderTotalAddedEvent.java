package ci.kossovo.ventecoreapi.events.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderTotalAddedEvent {

  private final String orderId;
  private final Double oldSomme;
  private final Double newSomme;
}
