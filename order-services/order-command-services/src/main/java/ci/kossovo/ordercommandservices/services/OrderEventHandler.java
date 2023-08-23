package ci.kossovo.ordercommandservices.services;

import ci.kossovo.ordercommandservices.models.Produit;
import ci.kossovo.ordercommandservices.reposotories.ProduitRepository;
import ci.kossovo.ventecoreapi.commands.order.AddProduitOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CancelOrderCommand;
import ci.kossovo.ventecoreapi.commands.produit.AddStockProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.UpdateStockProduitCommand;
import ci.kossovo.ventecoreapi.events.order.OrderCreatedEvent;
import ci.kossovo.ventecoreapi.events.order.orderLine.OrderLineRemovedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitOrderAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockAddedEvent;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import ci.kossovo.ventecoreapi.exceptions.produits.ProduitStockInsuffisantException;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  private final CommandGateway commandGateway;
  private final ProduitRepository produitRepository;

  @EventHandler
  public void on(OrderCreatedEvent event) {
    if (event.getCodeProduit() != null) {
      Optional<Produit> produitOpt = produitRepository.findByCodeProduit(
        event.getCodeProduit()
      );

      if (produitOpt.isEmpty()) {
        CancelOrderCommand cmd = new CancelOrderCommand(event.getOrderId());
        commandGateway.send(cmd);
        throw new NotFoundProduitException(event.getCodeProduit());
      }

      if (produitOpt.get().getStock().intValue() < event.getQuantite()) {
        CancelOrderCommand cmd = new CancelOrderCommand(event.getOrderId());
        commandGateway.send(cmd);

        throw new ProduitStockInsuffisantException(
          produitOpt.get().getCodeProduit()
        );
      }

      AddProduitOrderCommand command = AddProduitOrderCommand
        .builder()
        .orderId(event.getOrderId())
        .codeProduit(event.getCodeProduit())
        .quantite(event.getQuantite())
        .build();
      if (command != null) commandGateway.send(command);
    }
  }

  @EventHandler
  public void on(OrderLineRemovedEvent evt) {
    AddStockProduitCommand cmd = AddStockProduitCommand
      .builder()
      .codeProduit(evt.codeProduit())
      .nombre(evt.count())
      .build();
  }

  // Lancer la commande de la reduction du stock du produit
  @EventHandler
  public void on(ProduitOrderAddedEvent event) {
    UpdateStockProduitCommand command = UpdateStockProduitCommand
      .builder()
      .codeProduit(event.getCodeProduit())
      .nombre(event.getQuantite())
      .build();
    if (command != null) commandGateway.send(command);
  }

  @EventHandler
  public void on(ProduitStockAddedEvent event) {
    Produit produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setStock(produit.getStock() + event.getNombre());
    produitRepository.save(produit);
  }
}
