package ci.kossovo.ventecoreapi.events.card;

import ci.kossovo.ventecoreapi.enums.CardStatut;

public record CreditCardActivedEvent(
  String cardCreditId,
  String cardNumber,
  CardStatut statut
) {}
