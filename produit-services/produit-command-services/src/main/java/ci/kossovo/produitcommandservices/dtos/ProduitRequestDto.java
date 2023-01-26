package ci.kossovo.produitcommandservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduitRequestDto {
 
  private String titre;
  private Double prix;
  private Integer stock;
  private String description;
}
