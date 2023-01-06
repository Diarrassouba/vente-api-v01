package ci.kossovo.ventecoreapi.events.card;

public record CreditCardUpdatedEvent(
 
   String cardCreditId,
   String cardNumber,
   String nom,
   String prenom,
   String ownerNationalCardId,
   String email,
   String quartier,
   String ville,
   String tel,
   String cel) {}
   //  Integer validUntilMonth,
   //  Integer validUntilYear,
   //  Integer cvv,
   //  CardStatut statut,
