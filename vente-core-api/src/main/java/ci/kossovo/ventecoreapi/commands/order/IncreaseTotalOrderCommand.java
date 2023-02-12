package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;
@Data
@Builder
public class IncreaseTotalOrderCommand {
    @TargetAggregateIdentifier
  private final String orderId;

  private final Double montant;
}
