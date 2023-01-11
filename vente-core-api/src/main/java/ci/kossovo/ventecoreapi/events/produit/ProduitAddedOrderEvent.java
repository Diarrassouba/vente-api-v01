package ci.kossovo.ventecoreapi.events.produit;


import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitAddedOrderEvent {

  private final String orderId;
  private final String codeProduit;
  //private final OrderDtos orderDtos;
  private final OrderProduitDtos orderProduitDtos;
  private final Integer quantite;
  //private final Double prix;
}
