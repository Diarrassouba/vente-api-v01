package ci.kossovo.ventecoreapi.dtos.cards;

import ci.kossovo.ventecoreapi.enums.CardStatut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDetailDto {

  private String cardCreditId;
  private String cardNumber;
  private Integer validUntilMonth;
  private Integer validUntilYear;
  private String proprietaire;
  private Double balance;
  CardStatut statut= CardStatut.DESACTIVE;
}
