package ci.kossovo.ventecoreapi.commands.order;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class ShipOrderCommand {

  @TargetAggregateIdentifier
  private String shipmentId;

  private final String orderId;
  private final String paymentId;
  private final Long userId;
  private final Long operateurId;
  private final Long addresseId;
}
