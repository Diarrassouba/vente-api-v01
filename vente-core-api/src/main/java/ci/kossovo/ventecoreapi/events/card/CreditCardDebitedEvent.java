package ci.kossovo.ventecoreapi.events.card;

public record CreditCardDebitedEvent(
 
    String cardCreditId,
    String cardNumber, 
     Double amount
) {}
