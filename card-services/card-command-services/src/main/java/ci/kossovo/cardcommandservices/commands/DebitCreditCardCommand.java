package ci.kossovo.cardcommandservices.commands;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record DebitCreditCardCommand(
  @TargetAggregateIdentifier String cardCreditId,
  String cardNumber,
  Double amount
) {}
