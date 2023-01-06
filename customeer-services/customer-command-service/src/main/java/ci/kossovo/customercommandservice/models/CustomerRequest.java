package ci.kossovo.customercommandservice.models;

import java.io.Serializable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CustomerRequest implements Serializable {

  @NotBlank(message = "Le nom ne peut etre mull.")
  private String nom;

  @NotBlank(message = "Le prenom ne peut etre etre null")
  private String prenom;

  private String nationalId;

  @NotBlank(message = "Le tel ne peut etre etre null")
  private String tel;

  @NotBlank(message = "Le cel ne peut etre null")
  private String cel;

  @Email(message = "L'email n'est pas valide")
  private String email;

  private String quartier;

  @NotBlank(message = "La ville ne peut etre etre null")
  private String ville;
}
