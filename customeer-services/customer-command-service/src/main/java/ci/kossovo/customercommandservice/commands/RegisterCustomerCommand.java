package ci.kossovo.customercommandservice.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class RegisterCustomerCommand {

  @TargetAggregateIdentifier
  private String customerId;

  private final  String nom;
  private final  String prenom;
  private final  String nationalId;
  private final  String tel;
  private final  String cel;
  private final  String email;
  private final  String quartier;
  private final  String ville;
}
