package ci.kossovo.ventecoreapi.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderProduitAddRequest {

  private String orderId;
  private String codeProduit;
  private Integer quantite;
}
