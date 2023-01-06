package ci.kossovo.ventecoreapi.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CustomerDto {

  private String customerId;
  private String nom;
  private String prenom;
  private String nationalId;
  private String email;
  private String tel;
  private String cel;
  private String quartier;
  private String ville;
}
