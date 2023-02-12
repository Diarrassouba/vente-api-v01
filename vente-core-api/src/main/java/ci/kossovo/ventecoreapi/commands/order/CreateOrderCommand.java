package ci.kossovo.ventecoreapi.commands.order;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateOrderCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private String codeProduit;
  // private OrderProduitDtos produit;
  private final String customerId;
  // private final Double prix;
  private final Integer quantite;
  private final OrderStatus orderStatus;
}
