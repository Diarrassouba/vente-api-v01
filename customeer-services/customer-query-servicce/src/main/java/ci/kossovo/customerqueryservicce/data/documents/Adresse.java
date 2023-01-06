package ci.kossovo.customerqueryservicce.data.documents;

import java.io.Serializable;

import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
// @Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Adresse implements Serializable {

  @Id
  String id;

  private String quartier;
  private String ville;
  private String tel;
  private String cel;
}
