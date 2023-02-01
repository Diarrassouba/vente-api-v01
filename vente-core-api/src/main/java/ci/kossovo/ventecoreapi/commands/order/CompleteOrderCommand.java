package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CompleteOrderCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private OrderStatus orderStatus;
  private Long userId;
  private Long addresseId;
}
