package ci.kossovo.ventecoreapi.events.card;

public record CreditCardCreditedEvent(
  
    String cardCreditId,
    String cardNumber, 
     Double amount) {}
