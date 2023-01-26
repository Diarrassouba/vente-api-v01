package ci.kossovo.produitcommandservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduitUpdateRequest {

  private String titre;
  private Double prix;
  private String description;
}
