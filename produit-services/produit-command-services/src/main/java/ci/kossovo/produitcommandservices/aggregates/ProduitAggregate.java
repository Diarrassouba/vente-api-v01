package ci.kossovo.produitcommandservices.aggregates;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import ci.kossovo.ventecoreapi.commands.produit.AddStockProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.CreateProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.UpdateProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.UpdateStockProduitCommand;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitUpdatedEvent;

@Aggregate
public class ProduitAggregate {

  @AggregateIdentifier
  private String codeProduit;

  private String titre;
  private Double prix;
  private Integer stock;
  private String description;

  public ProduitAggregate() {}

  @CommandHandler
  public ProduitAggregate(CreateProduitCommand cmd) {
    ProduitCreatedEvent event = new ProduitCreatedEvent();

    BeanUtils.copyProperties(cmd, event);
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(ProduitCreatedEvent event) {
    this.codeProduit = event.getCodeProduit();
    this.titre = event.getTitre();
    this.prix = event.getPrix();
    this.stock = event.getStock();
    this.description = event.getDescription();
  }

  @CommandHandler
  public void handle(UpdateStockProduitCommand cmd) {
    ProduitStockUpdatedEvent event = ProduitStockUpdatedEvent
      .builder()
      .codeProduit(cmd.getCodeProduit())
      .nombre(cmd.getNombre())
      .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(ProduitStockUpdatedEvent event) {
    this.stock = this.stock - event.getNombre();
  }

  @CommandHandler
  public void handle(AddStockProduitCommand cmd) {
    ProduitStockAddedEvent event = ProduitStockAddedEvent
      .builder()
      .codeProduit(cmd.getCodeProduit())
      .nombre(cmd.getNombre())
      .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(ProduitStockAddedEvent event) {
    this.stock = this.stock + event.getNombre();
  }

  @CommandHandler
  public void handle(UpdateProduitCommand cmd) {
    ProduitUpdatedEvent event = ProduitUpdatedEvent
      .builder()
      .codeProduit(cmd.getCodeProduit())
      .titre(cmd.getTitre())
      .prix(cmd.getPrix())
      .description(cmd.getDescription())
      .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(ProduitUpdatedEvent event) {
    this.codeProduit = event.getCodeProduit();
    this.titre = event.getTitre();
    this.prix = event.getPrix();
    this.description = event.getDescription();
  }

  @EventSourcingHandler
  public void on(ProduitCountUpdatedEvent event) {
    this.codeProduit = event.getCodeProduit();
    this.stock = event.getCount();
  }
}
