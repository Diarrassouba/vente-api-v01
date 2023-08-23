package ci.kossovo.ordercommandservices.aggregates;

import ci.kossovo.ventecoreapi.commands.order.DecrementProduitCountCommand;
import ci.kossovo.ventecoreapi.commands.order.IncrementProduitCountCommand;
import ci.kossovo.ventecoreapi.commands.order.RemoveOrderLineCommand;
import ci.kossovo.ventecoreapi.commands.order.UpdateProduitCountCommand;
import ci.kossovo.ventecoreapi.events.order.OrderConfirmedEvent;
import ci.kossovo.ventecoreapi.events.order.orderLine.OrderLineCountIncrementedEvent;
import ci.kossovo.ventecoreapi.events.order.orderLine.OrderLineRemovedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountDecrementedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitRemovedEvent;
import ci.kossovo.ventecoreapi.exceptions.order.OrderAlreadyConfirmedException;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.EntityId;

@EqualsAndHashCode
@Slf4j
public class OrderLine {

  // @EntityId(routingKey = "codeProduit") si identifianr OrderLine est
  //private String orderLineId; car dans les commands on a codeProuit
  @EntityId(routingKey = "codeProduit")
  private final String codeProduit;

  private Integer count;
  private Double prix;
  private Double total;
  private boolean orderConfirmed;

  // ************************************************
  // Constructor par arguments
  // ************************************************
  public OrderLine(String codeProduit, Double prix, Integer count) {
    this.codeProduit = codeProduit;
    this.count = count;
    this.prix = prix;
    this.total = prix * count;
  }

  @CommandHandler
  public void handle(RemoveOrderLineCommand cmd) {
    // OrderLine orderLine = orderLines.get(cmd.getCodeProduit());
    OrderLineRemovedEvent event = new OrderLineRemovedEvent(
      cmd.getOrderId(),
      cmd.getCodeProduit(),
      total,
      count
    );

    AggregateLifecycle.apply(event);
  }

  @CommandHandler
  public void handle(IncrementProduitCountCommand cmd) {
    if (orderConfirmed) {
      throw new OrderAlreadyConfirmedException(cmd.getOrderId());
    } else {
      AggregateLifecycle.apply(
        OrderLineCountIncrementedEvent
          .builder()
          .orderId(cmd.getOrderId())
          .codeProduit(codeProduit)
          .build()
      );
    }
  }

  @EventSourcingHandler
  public void on(OrderLineCountIncrementedEvent event) {
    this.count++;
    this.total = prix * count;
  }

  @CommandHandler
  public void handle(DecrementProduitCountCommand cmd) {
    if (orderConfirmed) {
      throw new OrderAlreadyConfirmedException(cmd.getOrderId());
    }

    if (count <= 1) {
      AggregateLifecycle.apply(
        ProduitRemovedEvent
          .builder()
          .orderId(cmd.getOrderId())
          .codeProduit(codeProduit)
          .total(total)
          .build()
      );
    } else {
      AggregateLifecycle.apply(
        ProduitCountDecrementedEvent
          .builder()
          .orderId(cmd.getOrderId())
          .codeProduit(codeProduit)
          .build()
      );
    }
  }

  @EventSourcingHandler
  public void on(ProduitCountDecrementedEvent event) {
    this.count--;
    this.total = prix * count;
    // OrderTotalAddedEvent evt = OrderTotalAddedEvent
    //   .builder()
    //   .orderId(event.getOrderId())
    //   .oldSomme(prix * (count + 1))
    //   .newSomme(prix * count)
    //   .build();
    // AggregateLifecycle.apply(evt);
  }

  @CommandHandler
  public void handle(UpdateProduitCountCommand cmd) {
    if (orderConfirmed) {
      throw new OrderAlreadyConfirmedException(cmd.getOrderId());
    } else {
      AggregateLifecycle.apply(
        ProduitCountUpdatedEvent
          .builder()
          .orderId(cmd.getOrderId())
          .codeProduit(codeProduit)
          .count(cmd.getCount())
          .build()
      );
    }
  }

  @EventSourcingHandler
  public void on(ProduitCountUpdatedEvent event) {
    // Double oldTotal = this.total;
    this.count = event.getCount();
    this.total = prix * count;
    // OrderTotalAddedEvent evt = OrderTotalAddedEvent
    //   .builder()
    //   .orderId(event.getOrderId())
    //   .oldSomme(oldTotal)
    //   .newSomme(total)
    //   .build();
    // AggregateLifecycle.apply(evt);
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    this.orderConfirmed = true;
  }
}
