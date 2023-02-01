package ci.kossovo.ventecoreapi.dtos.order;


import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDtos {

  private String orderId;

  private Long userId;
  private OrderStatus orderStatus;
  private boolean orderConfirmed;
  private Double orderTotal;
}