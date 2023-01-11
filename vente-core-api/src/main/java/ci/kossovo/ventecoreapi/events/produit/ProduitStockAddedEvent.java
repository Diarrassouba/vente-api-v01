package ci.kossovo.ventecoreapi.events.produit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class ProduitStockAddedEvent {

  private String codeProduit;

  private final Integer nombre;
}
