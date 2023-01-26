package ci.kossovo.ventecoreapi.events.produit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitCountDecrementedEvent {

  private final String orderId;
  private final String codeProduit;
}
