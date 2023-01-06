package ci.kossovo.customercommandservice.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;
import org.mapstruct.control.NoComplexMapping;

@Data
@NoComplexMapping
@AllArgsConstructor
@Builder
public class CorrectCustomerContactCcommand {

  @TargetAggregateIdentifier
  private String customerId;

 private String nationalId;
  private String tel;
  private String cel;
  private String email;
  private String quartier;
  private String ville;
}
