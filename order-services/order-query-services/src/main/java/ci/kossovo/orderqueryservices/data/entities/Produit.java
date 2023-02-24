package ci.kossovo.orderqueryservices.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "T_PRODUIT")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Produit {

  @Id
  private String idProduit;

  private String titre;
  private Double prix;
}
