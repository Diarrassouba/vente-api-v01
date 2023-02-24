package ci.kossovo.ordermongoqueryservices.data.documents;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderCustomer {

  private String id;

  private String nom;
  private String prenom;
  private String nationalId;
  private String email;
  private String quartier;
  private String ville;
  private String tel;
  private String cel;
}
