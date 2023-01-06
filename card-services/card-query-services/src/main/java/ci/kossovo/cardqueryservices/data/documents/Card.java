package ci.kossovo.cardqueryservices.data.documents;

import ci.kossovo.ventecoreapi.enums.CardStatut;
import java.io.Serializable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "CreditCard")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Card implements Serializable {

  @Id
  private String id;

  @Indexed(unique = true)
  private String cardNumber;

  private Integer validUntilMonth;
  private Integer validUntilYear;
  private Integer cvv;
  private Double balance;

  @Enumerated(EnumType.STRING)
  CardStatut statut;

  private String proprietaire;
  private String ownerNationalCardId;
  private String email;
  private String quartier;
  private String ville;
  private String tel;
  private String cel;
}
