package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Data;
import lombok.Value;

@Data
@Value
public class CancelOrderCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private OrderStatus orderStatus=OrderStatus.CANCELED;
}
