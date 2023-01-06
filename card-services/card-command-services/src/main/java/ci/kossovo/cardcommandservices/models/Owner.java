package ci.kossovo.cardcommandservices.models;

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
@AllArgsConstructor
@Builder
public class Owner implements Serializable {

  @Id
  private String id;

  private String nom;
  private String prenom;
  private String ownerNationalCardId;
  private String email;
  private String quartier;
  private String ville;
  private String tel;
  private String cel;

  public String getProp() {
    return nom + " " + prenom;
  }
}
