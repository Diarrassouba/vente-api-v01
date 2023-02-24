package ci.kossovo.produitelasticqueryservices.data.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(indexName = "produits")
public class Produit {

  @Id
  private String codeProduit;

  @Field(type = FieldType.Text, name = "titre")
  private String titre;

  @Field(type = FieldType.Double, name = "tarif")
  private Double prix;

  private Integer stock;
  private String description;
}
