package ci.kossovo.customerqueryservicce.data.documents;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document
// @Entity
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Customer implements Serializable {

  @Id
  private String id;

  private String nom;
  private String prenom;
  private String nationalId;
  private String email;

  @OneToOne
  private Adresse adresse;
}
