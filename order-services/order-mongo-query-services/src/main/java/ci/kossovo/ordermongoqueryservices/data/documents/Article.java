package ci.kossovo.ordermongoqueryservices.data.documents;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @Entity(name = "T_ORDERLINE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Article {

  // @Id
  private String articleId;
  private String titre;
  private Double prix;
  private String description;
  private Integer count;
  private Double montant;
  // private Order order;
}
