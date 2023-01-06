package ci.kossovo.ventecoreapi.events.card;

import ci.kossovo.ventecoreapi.enums.CardStatut;

public record CreditCardCreatedEvent(
  String cardCreditId,
  String cardNumber,
  Integer validUntilMonth,
  Integer validUntilYear,
  Integer cvv,
  Double balance,
  CardStatut statut,
  String proprietaire,
  String ownerNationalCardId,
  String email,
  String quartier,
  String ville,
  String tel,
  String cel
) {}
