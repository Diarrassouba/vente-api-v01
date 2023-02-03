package ci.kossovo.ordercommandservices.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "T_PRODUIT")
public class Produit {

  @Id
  private String codeProduit;

  private String titre;
  private Double prix;
  private Integer stock;
  private String description;
}
