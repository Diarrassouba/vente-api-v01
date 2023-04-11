package ci.kossovo.ordermongoqueryservices.data.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produit {

  private String id;

  private String titre;
  private Double prix;
  private Integer stock;
  private String description;


}
