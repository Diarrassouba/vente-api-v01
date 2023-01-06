package ci.kossovo.ventecoreapi.dtos.cards;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

  private String cardNumber;
  private Integer validUntilMonth;
  private Integer validUntilYear;
  private String proprietaire;
  private Double balance;
  //   private String ownerNom;
  //   private String ownerPrenom;
}
