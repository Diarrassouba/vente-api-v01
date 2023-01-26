package ci.kossovo.ventecoreapi.events.produit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitCountUpdatedEvent {

  private final String orderId;

  private final String codeProduit;
  private final Integer count;
}
