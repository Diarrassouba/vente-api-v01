package ci.kossovo.ventecoreapi.commands.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RemoveOrderLineCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private String codeProduit;
  // private String orderLineId;
  // private Double total;
}
