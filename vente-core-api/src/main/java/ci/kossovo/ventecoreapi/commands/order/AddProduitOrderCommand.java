package ci.kossovo.ventecoreapi.commands.order;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class AddProduitOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;

  private final String codeProduit;
  private final Integer quantite;
  // private final OrderDtos orderDtos;
  // private final OrderProduitDtos orderProduitDtos;
  //private final Double prix;

}
