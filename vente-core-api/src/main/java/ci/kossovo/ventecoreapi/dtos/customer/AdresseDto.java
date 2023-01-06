package ci.kossovo.ventecoreapi.dtos.customer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AdresseDto {

  private String adresseId;
  private String tel;
  private String cel;
  private String quartier;
  private String ville;
}
