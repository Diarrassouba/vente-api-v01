package ci.kossovo.ventecoreapi.queries.produits;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetProduitDetailsQuery {
  private String codeProduit;
}
