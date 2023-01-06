package ci.kossovo.cardqueryservices.projections;

import ci.kossovo.cardqueryservices.data.documents.Card;
import ci.kossovo.cardqueryservices.servicees.CardService;
import ci.kossovo.ventecoreapi.events.card.CreditCardCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
// @Slf4j
@RequiredArgsConstructor
public class CardEventHandler {

  private final CardService cardService;

  // private final EventGateway eventGateway;

  @EventHandler
  public void on(CreditCardCreatedEvent event) {
    // log.info("#############################################################");
    // log.info("Le proprietaire de la carte est: {}s", event.userId());
    // Owner owner = cardService.findByIdOwnwr(event.userId());
    // String[] cels = owner.getCel().split("-");
    // String number = cels[0] + "-" + cels[1] + cels[2] + "-" + cels[3] + cels[4];

    // log.info("#############################################################");
    // log.info("Le numero  de la carte est: {}", number);
    Card card = Card
      .builder()
      .id(event.cardCreditId())
      .cardNumber(event.cardNumber())
      .proprietaire(event.proprietaire())
      .ownerNationalCardId(event.ownerNationalCardId())
      .email(event.email())
      .quartier(event.quartier())
      .ville(event.ville())
      .tel(event.tel())
      .cel(event.cel())
      .validUntilMonth(event.validUntilMonth())
      .validUntilYear(event.validUntilYear())
      .balance(event.balance())
      .cvv(event.cvv())
      .statut(event.statut())
      .build();

    cardService.saveCard(card);
    //  catch (DuplicateKeyException e) {
    //   throw new RuntimeException("Probleme de creation de la carte de credit");
    // }
  }
}
