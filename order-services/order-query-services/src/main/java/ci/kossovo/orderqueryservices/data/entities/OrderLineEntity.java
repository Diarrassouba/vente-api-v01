package ci.kossovo.orderqueryservices.data.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "T_ORDERLINE")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class OrderLineEntity {

  @Id
  private String orderLineId;

  @ManyToOne
  private Produit produit;

  @ManyToOne
  private Order order;

  private Integer count;
  private Double prix;
  private Double montant;
  // @ManyToOne
  //@JoinColumn(name = "codeProduit")
  // private Produit produit;

  //@ManyToOne
  // @JoinColumn(name = "orderId")
  // private Order order;
  // clés étrangères
  // @Column(name = "orderId", insertable = false, updatable = false)
  // private String orderId;
}
