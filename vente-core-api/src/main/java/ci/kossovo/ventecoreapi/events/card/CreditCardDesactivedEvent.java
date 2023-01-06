package ci.kossovo.ventecoreapi.events.card;

import ci.kossovo.ventecoreapi.enums.CardStatut;

public record CreditCardDesactivedEvent(
  String cardCreditId,
  String cardNumber,
  CardStatut statut
) {}
