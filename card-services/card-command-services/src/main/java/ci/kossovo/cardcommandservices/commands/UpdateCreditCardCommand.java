package ci.kossovo.cardcommandservices.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record UpdateCreditCardCommand(
  @TargetAggregateIdentifier String cardCreditId,
  String cardNumber,
  String userId,
  Integer validUntilMonth,
  Integer validUntilYear,
  Integer cvv

) {}
