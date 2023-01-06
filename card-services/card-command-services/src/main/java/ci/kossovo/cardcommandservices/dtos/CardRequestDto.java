package ci.kossovo.cardcommandservices.dtos;

import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CardRequestDto {

  @NotBlank
  private String userId;

  private Double balance;
  // private Integer cvv;
}
