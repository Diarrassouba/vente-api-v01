package ci.kossovo.cardcommandservices.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record CreditCreditCardCommand(
  @TargetAggregateIdentifier String cardCreditId,
  String cardNumber,
  Double amount
) {}
