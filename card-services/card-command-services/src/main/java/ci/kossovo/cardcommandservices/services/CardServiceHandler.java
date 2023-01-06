package ci.kossovo.cardcommandservices.services;

import ci.kossovo.cardcommandservices.models.Owner;
import ci.kossovo.cardcommandservices.repository.OwnerRepository;
import ci.kossovo.ventecoreapi.events.customer.CustomerCorrectedEvent;
import ci.kossovo.ventecoreapi.events.customer.CustomerRegisteredEvent;
import ci.kossovo.ventecoreapi.exceptions.customers.NotFoundCustomerException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceHandler {

  private final OwnerRepository ownerRepository;

  @EventHandler
  public void on(CustomerRegisteredEvent event) {
    Owner owner = Owner
      .builder()
      .nom(event.getNom())
      .prenom(event.getPrenom())
      .email(event.getEmail())
      .id(event.getCustomerId())
      .cel(event.getCel())
      .ville(event.getVille())
      .quartier(event.getQuartier())
      .tel(event.getTel())
      .build();

    ownerRepository.save(owner);
  }

  @EventHandler
  public void on(CustomerCorrectedEvent event) {
    Owner owner = ownerRepository
      .findById(event.getCustomerId())
      .orElseThrow(() -> new NotFoundCustomerException(event.getCustomerId()));

    owner =
      Owner
        .builder()
        .id(event.getCustomerId())
        .email(event.getEmail())
        .id(event.getCustomerId())
        .cel(event.getCel())
        .ville(event.getVille())
        .quartier(event.getQuartier())
        .tel(event.getTel())
        .build();

    ownerRepository.save(owner);
  }
}

