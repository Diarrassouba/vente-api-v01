package ci.kossovo.ventecoreapi.events.produit;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProduitCreatedEvent {

  private String codeProduit;
  private String titre;
  private Double prix;
  private Integer stock;
  private String description;
}
