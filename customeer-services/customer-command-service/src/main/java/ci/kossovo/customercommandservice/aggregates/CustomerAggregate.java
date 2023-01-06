package ci.kossovo.customercommandservice.aggregates;

import ci.kossovo.customercommandservice.commands.RegisterCustomerCommand;
import ci.kossovo.ventecoreapi.events.customer.CustomerRegisteredEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class CustomerAggregate {

  @AggregateIdentifier
  private String customerId;

  private String nom;
  private String prenom;
  private String nationalId;
  private String tel;
  private String cel;
  private String email;
  private String quartier;
  private String ville;

  public CustomerAggregate() {}

  @CommandHandler
  public CustomerAggregate(RegisterCustomerCommand cmd) {
    CustomerRegisteredEvent event = new CustomerRegisteredEvent();
    BeanUtils.copyProperties(cmd, event);

    // BeanUtils.copyProperties(cmd, event);

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(CustomerRegisteredEvent event) {
    this.customerId = event.getCustomerId();
    this.nom = event.getNom();
    this.prenom = event.getPrenom();
    this.tel = event.getTel();
    this.cel = event.getCel();
    this.email = event.getEmail();
    this.quartier = event.getQuartier();
    this.ville = event.getVille();
    this.nationalId=event.getNationalId();
  }

  public String getValue() {
    return customerId;
  }
}
