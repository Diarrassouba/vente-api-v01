package ci.kossovo.ventecoreapi.events.order;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderShippedEvent {

  private String shipmentId;
  private final String orderId;
  private final String paymentId;
  private final Long userId;
  private final Long addresseId;
  private final OrderStatus shipStatus;
}
