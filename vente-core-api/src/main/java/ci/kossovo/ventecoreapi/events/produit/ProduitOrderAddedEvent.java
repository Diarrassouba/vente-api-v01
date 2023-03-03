package ci.kossovo.ventecoreapi.events.produit;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitOrderAddedEvent {

  private final String orderId;
  private final String codeProduit;
  // pour mongo query seulement
  private final String description;
  private final Integer quantite;
  private final Double prix;
}
