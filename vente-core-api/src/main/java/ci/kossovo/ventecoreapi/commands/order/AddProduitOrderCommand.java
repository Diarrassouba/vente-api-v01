package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import ci.kossovo.ventecoreapi.dtos.order.OrderDtos;
import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AddProduitOrderCommand {

  @TargetAggregateIdentifier
  private final String orderId;

  private final String codeProduit;
  private final OrderDtos orderDtos;
  private final OrderProduitDtos orderProduitDtos;
  private final Integer quantite;
  //private final Double prix;

 

 
}
