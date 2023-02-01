package ci.kossovo.ventecoreapi.events.order;

import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Data;

@Data
public class OrderCreatedEvent {

  private String orderId;
  private String codeProduit;
  private OrderProduitDtos produit;
  private Long userId;
  //private Double prix;
  private Integer quantite;
  private OrderStatus orderStatus;
}
