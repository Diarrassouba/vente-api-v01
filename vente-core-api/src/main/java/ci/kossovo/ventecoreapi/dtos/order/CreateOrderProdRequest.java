package ci.kossovo.ventecoreapi.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CreateOrderProdRequest {

  private String produitId;
  private Long userId;
}
