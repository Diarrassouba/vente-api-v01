package ci.kossovo.ventecoreapi.events.produit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ProduitUpdatedEvent {

  private String codeProduit;

  private final String titre;
  private final Double prix;
  private String description;
}
