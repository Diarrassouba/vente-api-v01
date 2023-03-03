package ci.kossovo.ordercommandservices.models;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class OrderCustomer implements Serializable {

  @Id
  private String id;

  private String nom;
  private String prenom;
  private String nationalId;
  private String email;
  private String quartier;
  private String ville;
  private String tel;
  
}
