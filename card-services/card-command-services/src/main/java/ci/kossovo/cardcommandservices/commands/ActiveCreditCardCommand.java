package ci.kossovo.cardcommandservices.commands;

import ci.kossovo.ventecoreapi.enums.CardStatut;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

public record ActiveCreditCardCommand(
  @TargetAggregateIdentifier String cardCreditId,
  String cardNumber,
  CardStatut statut
) {}
