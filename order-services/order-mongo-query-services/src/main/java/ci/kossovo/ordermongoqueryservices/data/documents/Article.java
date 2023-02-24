package ci.kossovo.ordermongoqueryservices.data.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Article {
    private String id;

  private String titre;
  private Double prix; 
  private Integer count;
 
  private Double montant;
}

