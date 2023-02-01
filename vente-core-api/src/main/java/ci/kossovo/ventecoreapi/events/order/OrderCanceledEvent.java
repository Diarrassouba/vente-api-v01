package ci.kossovo.ventecoreapi.events.order;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCanceledEvent {

  private String orderId;

  private OrderStatus orderStatus;
}
