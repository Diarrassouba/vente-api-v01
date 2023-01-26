package ci.kossovo.ventecoreapi.events.produit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitRemovedEvent {

  private final String orderId;
  private final String codeProduit;
  private final Double total;
}
