package ci.kossovo.ventecoreapi.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

  private String produitId;
  private Long userId;
  private Integer quantite;
}
