package ci.kossovo.ventecoreapi.events.produit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class ProduitStockUpdatedEvent {

  private String codeProduit;
  private final Integer nombre;
  

}
