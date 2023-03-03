package ci.kossovo.ventecoreapi.events.order;

import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderCreatedEvent {

  private final String orderId;
  private final String codeProduit;
  private final CustomerDto customer;
  private final Integer quantite;
  private final OrderStatus orderStatus;
}
