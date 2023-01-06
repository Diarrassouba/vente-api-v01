package ci.kossovo.cardcommandservices.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreateCreditCardCommand(
  @TargetAggregateIdentifier String cardCreditId,
  String userId,
  Integer validUntilMonth,
  Integer validUntilYear,
  Double balance
  
 
) {}