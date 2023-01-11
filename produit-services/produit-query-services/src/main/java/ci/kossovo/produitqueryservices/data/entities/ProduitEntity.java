package ci.kossovo.produitqueryservices.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "T_PRODUIT")
public class ProduitEntity {

  @Id
  private String codeProduit;

  private String titre;
  private Double prix;
  private Integer stock;
  private String description;

  public ProduitEntity(
    String codeProduit,
    String titre,
    Double prix,
    Integer stock,
    String description
  ) {
    this.codeProduit = codeProduit;
    this.titre = titre;
    this.prix = prix;
    this.stock = stock;
    this.description = description;
  }
}
