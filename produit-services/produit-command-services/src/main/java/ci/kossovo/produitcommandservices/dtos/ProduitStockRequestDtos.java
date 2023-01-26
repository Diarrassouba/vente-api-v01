package ci.kossovo.produitcommandservices.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProduitStockRequestDtos {

  private String codeProduit;
  private Integer nombre;
}
