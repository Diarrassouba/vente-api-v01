package ci.kossovo.cardcommandservices.aggregates;

import ci.kossovo.cardcommandservices.commands.ActiveCreditCardCommand;
import ci.kossovo.cardcommandservices.commands.CreateCreditCardCommand;
import ci.kossovo.cardcommandservices.commands.CreditCreditCardCommand;
import ci.kossovo.cardcommandservices.commands.DebitCreditCardCommand;
import ci.kossovo.cardcommandservices.commands.DesactiveCreditCardCommand;
import ci.kossovo.cardcommandservices.models.Owner;
import ci.kossovo.cardcommandservices.repository.OwnerRepository;
import ci.kossovo.exceptions.SoldeInsuffisant;
import ci.kossovo.ventecoreapi.enums.CardStatut;
import ci.kossovo.ventecoreapi.events.card.CreditCardActivedEvent;
import ci.kossovo.ventecoreapi.events.card.CreditCardCreatedEvent;
import ci.kossovo.ventecoreapi.events.card.CreditCardCreditedEvent;
import ci.kossovo.ventecoreapi.events.card.CreditCardDebitedEvent;
import ci.kossovo.ventecoreapi.events.card.CreditCardDesactivedEvent;
import ci.kossovo.ventecoreapi.exceptions.card.NotCardValidateException;
import ci.kossovo.ventecoreapi.exceptions.customers.NotFoundCustomerException;

import java.time.LocalDate;
import java.util.Random;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
@Slf4j
@Getter
public class CreditCardAggregate {

  @AggregateIdentifier
  private String cardCreditId;

  // private String userId;
  private String cardNumber;
  private Integer validUntilMonth;
  private Integer validUntilYear;
  private Integer cvv;
  private Double balance;
  private CardStatut statut;
  private String proprietaire;
  private String ownerNationalCardId;
  private String email;
  private String quartier;
  private String ville;
  private String tel;
  private String cel;

  public CreditCardAggregate() {}

  @CommandHandler
  public CreditCardAggregate(
    CreateCreditCardCommand cmd,
    OwnerRepository ownerRepository
  ) {
    Owner owner = ownerRepository
      .findById(cmd.userId())
      .orElseThrow((() -> new NotFoundCustomerException(cmd.userId())));

    LocalDate localDate = LocalDate.of(
      cmd.validUntilYear(),
      cmd.validUntilMonth(),
      1
    );
    log.info("#############################################");
    log.info("La date de validite de la carte est: {}", localDate);

    if (localDate.isBefore(LocalDate.now())) {
      log.info("********************************************");
      log.info("La date de validite est invalide: {}", localDate.toString());
      throw new NotCardValidateException(localDate);
    }

    log.info(
      "*********************************************************************"
    );
    log.info("La date de validite de la carte est valide");

    String number =
      generer(1000, 3000) +
      "-" +
      generer(4000, 6000) +
      "-" +
      generer(6500, 8000) +
      "-" +
      generer(8001, 9999);

    CreditCardCreatedEvent event = new CreditCardCreatedEvent(
      cmd.cardCreditId(),
      number,
      cmd.validUntilMonth(),
      cmd.validUntilYear(),
      generer(100, 400),
      cmd.balance(),
      CardStatut.ACTIVE,
      owner.getProp(),
      owner.getOwnerNationalCardId(),
      owner.getEmail(),
      owner.getQuartier(),
      owner.getVille(),
      owner.getTel(),
      owner.getCel()
    );

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CreditCardCreatedEvent event) {
    this.cardCreditId = event.cardCreditId();
    this.cardNumber = event.cardNumber();
    this.validUntilMonth = event.validUntilMonth();
    this.validUntilYear = event.validUntilYear();
    this.cvv = event.cvv();
    this.balance = event.balance();
    this.statut = event.statut();
    this.proprietaire=event.proprietaire();
    this.ownerNationalCardId = event.ownerNationalCardId();
    this.email = event.email();
    this.quartier = event.quartier();
    this.ville = event.ville();
    this.tel = event.tel();
    this.cel = event.cel();
  }

  @CommandHandler
  public void handle(CreditCreditCardCommand cmd) {
    CreditCardCreditedEvent event = new CreditCardCreditedEvent(
      cmd.cardCreditId(),
      cmd.cardNumber(),
      cmd.amount()
    );

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CreditCardCreditedEvent event) {
    this.balance = balance + event.amount();
  }

  @CommandHandler
  public void handle(DebitCreditCardCommand cmd) {
    if (balance >= cmd.amount()) {
      CreditCardDebitedEvent event = new CreditCardDebitedEvent(
        cmd.cardCreditId(),
        cmd.cardNumber(),
        cmd.amount()
      );

      AggregateLifecycle.apply(event);
    } else {
      throw new SoldeInsuffisant(cmd.cardNumber());
    }
  }

  @EventSourcingHandler
  public void on(CreditCardDebitedEvent event) {
    this.balance = balance - event.amount();
  }

  @CommandHandler
  public void handle(ActiveCreditCardCommand cmd) {
    CreditCardActivedEvent event = new CreditCardActivedEvent(
      cmd.cardCreditId(),
      cmd.cardNumber(),
      cmd.statut()
    );

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CreditCardActivedEvent event) {
    this.cardNumber = event.cardNumber();
    this.statut = event.statut();
  }

  @CommandHandler
  public void handle(DesactiveCreditCardCommand cmd) {
    CreditCardDesactivedEvent event = new CreditCardDesactivedEvent(
      cmd.cardCreditId(),
      cmd.cardNumber(),
      cmd.statut()
    );

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CreditCardDesactivedEvent event) {
    this.cardNumber = event.cardNumber();
    this.statut = event.statut();
  }

  // ************* Methodes privees ****************************
  private int generer(int borneInf, int borneSup) {
    Random random = new Random();
    int nb;
    nb = borneInf + random.nextInt(borneSup - borneInf);
    return nb;
  }
}
