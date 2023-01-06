package ci.kossovo.ventecoreapi.events.customer;

import lombok.Data;

@Data
public class CustomerRegisteredEvent {

  private String customerId;
  private String nom;
  private String prenom;
  private String nationalId;
  private String tel;
  private String cel;
  private String email;
  private String quartier;
  private String ville;
}
