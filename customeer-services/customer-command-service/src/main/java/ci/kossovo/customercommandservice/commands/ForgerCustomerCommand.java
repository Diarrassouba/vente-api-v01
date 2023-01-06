package ci.kossovo.customercommandservice.commands;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
@AllArgsConstructor
public class ForgerCustomerCommand {

  @TargetAggregateIdentifier
  private String customerId;

  private boolean disable;
}
