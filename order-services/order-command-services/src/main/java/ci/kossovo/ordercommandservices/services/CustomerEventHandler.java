package ci.kossovo.ordercommandservices.services;

import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

import ci.kossovo.ordercommandservices.models.OrderCustomer;
import ci.kossovo.ordercommandservices.reposotories.CustomerRepository;
import ci.kossovo.ventecoreapi.events.customer.CustomerRegisteredEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventHandler {

  private final CustomerRepository customerRepository;

  @EventHandler
  public void on(CustomerRegisteredEvent event) {
    log.info("***************************************************");
    log.info("Entrer projectin pour evt: {} ", event.toString());

    OrderCustomer customer = OrderCustomer
      .builder()
      .id(event.getCustomerId())
      .nom(event.getNom())
      .prenom(event.getPrenom())
      .nationalId(event.getNationalId())
      .email(event.getEmail())
      .tel(event.getTel())
      .ville(event.getVille())
      .quartier(event.getQuartier())
      .build();

    customer = customerRepository.save(customer);
    log.info(" Inscription recu pour a", customer);
  }
}
