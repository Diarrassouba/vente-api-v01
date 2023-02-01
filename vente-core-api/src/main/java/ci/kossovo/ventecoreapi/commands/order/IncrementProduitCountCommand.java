package ci.kossovo.ventecoreapi.commands.order;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class IncrementProduitCountCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private final String codeProduit;
}
