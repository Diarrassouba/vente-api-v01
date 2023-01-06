package ci.kossovo.customerqueryservicce.projections;

import ci.kossovo.customerqueryservicce.data.documents.Adresse;
import ci.kossovo.customerqueryservicce.data.documents.Customer;
import ci.kossovo.customerqueryservicce.services.CustomerService;
import ci.kossovo.ventecoreapi.events.customer.CustomerRegisteredEvent;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CustomerEventHandler {

  private final CustomerService customerService;

  @EventHandler
  public void on(CustomerRegisteredEvent event) {
    log.info("***************************************************");
    log.info("Entrer projectin pour evt: {} ", event.toString());
    Adresse adresse = Adresse
      .builder()
      .id(UUID.randomUUID().toString().split("-")[0])
      .tel(event.getTel())
      .cel(event.getCel())
      .ville(event.getVille())
      .quartier(event.getQuartier())
      .build();

    adresse = customerService.enregistrerAdresse(adresse);
    log.info("***************************************************");
    log.info("Adresse enregistre de ID: {}", adresse.getId());

    Customer customer = Customer
      .builder()
      .id(event.getCustomerId())
      .nom(event.getNom())
      .prenom(event.getPrenom())
      .nationalId(event.getNationalId())
      .email(event.getEmail())
      .adresse(adresse)
      .build();

    customer = customerService.inscrire(customer);
    log.info(" Inscription recu pour a", customer);
  }
}
