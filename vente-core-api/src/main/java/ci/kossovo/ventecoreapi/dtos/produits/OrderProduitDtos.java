package ci.kossovo.ventecoreapi.dtos.produits;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OrderProduitDtos {

  private String code;
  private String titre;
  private Double prix;
}
