package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderCommand {

  @TargetAggregateIdentifier
  private String orderId;

  private String codeProduit;
  private OrderProduitDtos produit;
  private final Long userId;
  // private final Double prix;
  private final Integer quantite;
  private final OrderStatus orderStatus;
}
