package ci.kossovo.ordermongoqueryservices.data.documents;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import java.util.Date;
import java.util.Map;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.MapKey;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Order {

  @Id
  private String id;
  @MapKey(name = "orderLineId")
  private Map<String, Article> articles;

  private OrderCustomer customer;
  private boolean orderConfirmed;
  private Double orderTotal;

  @Enumerated(EnumType.STRING)
  private OrderStatus orderStatus;

  @Temporal(TemporalType.DATE)
  private Date createdAt;
}
