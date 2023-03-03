package ci.kossovo.orderqueryservices.data.entities;

import java.util.Date;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "T_ORDER")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  private String orderId;

  @OneToMany(mappedBy = "order")
  @MapKey(name = "orderLineId")
  private Map<String, OrderLineEntity> orderLines;

  private OrderCustomer customer;
  private boolean orderConfirmed;
  private Double orderTotal;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Temporal(TemporalType.DATE)
  private Date createdAt;
}
