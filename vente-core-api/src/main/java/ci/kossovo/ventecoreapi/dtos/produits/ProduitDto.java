package ci.kossovo.ventecoreapi.dtos.produits;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProduitDto {

  private String code;
  private String titre;
  private Double prix;
  private int stock;
  private String desc;
}
