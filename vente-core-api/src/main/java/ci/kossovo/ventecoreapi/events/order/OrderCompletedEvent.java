package ci.kossovo.ventecoreapi.events.order;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderCompletedEvent {

  private String orderId;
  private OrderStatus orderStatus;
  private Long userId;
  private Long addresseId;
}
