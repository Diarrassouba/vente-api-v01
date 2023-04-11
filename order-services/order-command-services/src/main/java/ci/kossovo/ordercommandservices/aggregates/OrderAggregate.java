package ci.kossovo.ordercommandservices.aggregates;

import ci.kossovo.ordercommandservices.models.OrderCustomer;
import ci.kossovo.ordercommandservices.models.Produit;
import ci.kossovo.ordercommandservices.reposotories.CustomerRepository;
import ci.kossovo.ordercommandservices.reposotories.ProduitRepository;
import ci.kossovo.ventecoreapi.commands.order.AddProduitOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CancelOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CompleteOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.ConfirmOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CreateOrderCommand;
import ci.kossovo.ventecoreapi.commands.produit.UpdateStockProduitCommand;
import ci.kossovo.ventecoreapi.dtos.customer.CustomerDto;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import ci.kossovo.ventecoreapi.events.order.OrderCanceledEvent;
import ci.kossovo.ventecoreapi.events.order.OrderCompletedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderConfirmedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderCreatedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderTotalAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitOrderAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitRemovedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockUpdatedEvent;
import ci.kossovo.ventecoreapi.exceptions.customers.NotFoundOwnerException;
import ci.kossovo.ventecoreapi.exceptions.order.DuplicateOrderLineException;
import ci.kossovo.ventecoreapi.exceptions.order.NoTfoundProduitException;
import ci.kossovo.ventecoreapi.exceptions.order.OrderAlreadyConfirmedException;
import java.util.HashMap;
import java.util.Map;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.modelling.command.AggregateMember;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class OrderAggregate {

  @AggregateIdentifier
  private String orderId;

  private CustomerDto customer;
  private OrderStatus orderStatus;
  private boolean orderConfirmed;
  private Double totalOrder;

  @AggregateMember
  private Map<String, OrderLine> orderLines;

  public OrderAggregate() {}

  @CommandHandler
  public OrderAggregate( CreateOrderCommand cmd,  CustomerRepository customerRepository ) {
    // On doit verifier la disponibilie du produit ou valider la commande

    OrderCustomer customer = customerRepository
      .findById(cmd.getCustomerId())
      .orElseThrow(() -> new NotFoundOwnerException(cmd.getCustomerId()));

    CustomerDto customerDto = CustomerDto
      .builder()
      .customerId(customer.getId())
      .email(customer.getEmail())
      .nom(customer.getNom())
      .prenom(customer.getPrenom())
      .nationalId(customer.getNationalId())
      .quartier(customer.getQuartier())
      .ville(customer.getVille())
      .tel(customer.getTel())
      .build();

    OrderCreatedEvent evt = OrderCreatedEvent
      .builder()
      .orderId(cmd.getOrderId())
      .codeProduit(cmd.getCodeProduit())
      .customer(customerDto)
      .quantite(cmd.getQuantite())
      .orderStatus(OrderStatus.CREATED)
      .build();
    // BeanUtils.copyProperties(cmd, evt);

    AggregateLifecycle.apply(evt);
  }

  @EventSourcingHandler
  public void on(OrderCreatedEvent evt) {
    this.orderId = evt.getOrderId();
    this.customer = evt.getCustomer();
    this.orderStatus = evt.getOrderStatus();
    this.orderConfirmed = false;
    this.totalOrder = 0.0;
    this.orderLines = new HashMap<>();
  }

  @CommandHandler
  public void handle( AddProduitOrderCommand cmd, ProduitRepository produitRepository) {

    if (orderConfirmed) {
      throw new OrderAlreadyConfirmedException(orderId);
    }

    String codeProduit = cmd.getCodeProduit();
    if (orderLines.containsKey(codeProduit)) {
      throw new DuplicateOrderLineException(codeProduit);
    }
    Produit produit = produitRepository
    .findByCodeProduit(cmd.getCodeProduit())
    .orElseThrow(() -> new NoTfoundProduitException(cmd.getCodeProduit()));

   

    ProduitOrderAddedEvent event = ProduitOrderAddedEvent
      .builder()
      .orderId(cmd.getOrderId())
      .codeProduit(produit.getCodeProduit())
      .description(produit.getTitre())
      .prix(produit.getPrix())
      .quantite(cmd.getQuantite())
      .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(ProduitOrderAddedEvent event) {
    this.orderLines.put(
        event.getCodeProduit(),
        new OrderLine(
          event.getCodeProduit(),
          event.getPrix(),
          event.getQuantite()
        )
      );
    this.totalOrder += event.getPrix() * event.getQuantite();
  }

  @CommandHandler
  public void handle(UpdateStockProduitCommand cmd) {

    ProduitStockUpdatedEvent event = ProduitStockUpdatedEvent.builder()
    .codeProduit(cmd.getCodeProduit())
    .nombre(cmd.getNombre())
    .build();
    AggregateLifecycle.apply(event);
  }
  
  @CommandHandler
  public void handle(CompleteOrderCommand cmd) {
    // Valider la commande

    OrderCompletedEvent event = new OrderCompletedEvent();
    BeanUtils.copyProperties(cmd, event);

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(OrderCompletedEvent event) {
    this.orderStatus = event.getOrderStatus();
  }

  @CommandHandler
  public void handle(CancelOrderCommand cmd) {
    OrderCanceledEvent event = OrderCanceledEvent
      .builder()
      .orderId(cmd.getOrderId())
      .orderStatus(cmd.getOrderStatus())
      .build();

    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(OrderCanceledEvent event) {
    this.orderStatus = event.getOrderStatus();
  }

  @CommandHandler
  public void handle(ConfirmOrderCommand cmd) {
    if (orderConfirmed) {
      return;
    }

    OrderConfirmedEvent event = OrderConfirmedEvent
      .builder()
      .orderId(orderId)
      .userId(cmd.getUserId())
      .build();
    AggregateLifecycle.apply(event);
  }

  @EventSourcingHandler
  public void on(OrderConfirmedEvent event) {
    this.orderConfirmed = true;
  }

  @EventSourcingHandler
  public void on(ProduitRemovedEvent event) {
    this.orderLines.remove(event.getCodeProduit());
    this.totalOrder -= event.getTotal();
  }

  @EventSourcingHandler
  public void on(OrderTotalAddedEvent event) {
    this.orderId = event.getOrderId();
    this.totalOrder -= event.getOldSomme();
    this.totalOrder += event.getNewSomme();
  }
}
