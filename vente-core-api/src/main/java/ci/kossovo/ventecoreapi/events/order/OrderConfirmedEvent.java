package ci.kossovo.ventecoreapi.events.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderConfirmedEvent {

  private final String orderId;
  private final Long userId;
}
