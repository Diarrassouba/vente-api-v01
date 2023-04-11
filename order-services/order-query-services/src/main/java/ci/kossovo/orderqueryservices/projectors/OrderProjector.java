package ci.kossovo.orderqueryservices.projectors;

import ci.kossovo.orderqueryservices.data.entities.Order;
import ci.kossovo.orderqueryservices.data.entities.OrderCustomer;
import ci.kossovo.orderqueryservices.data.entities.OrderLineEntity;
import ci.kossovo.orderqueryservices.data.entities.Produit;
import ci.kossovo.orderqueryservices.data.mappers.OrderMapper;
import ci.kossovo.orderqueryservices.data.repository.OrderLineRepository;
import ci.kossovo.orderqueryservices.data.repository.OrderRepository;
import ci.kossovo.orderqueryservices.data.repository.ProduitRepository;
import ci.kossovo.orderqueryservices.data.repository.orderCustomerRepository;
import ci.kossovo.ventecoreapi.dtos.order.OrderDtos;
import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import ci.kossovo.ventecoreapi.events.order.OrderCanceledEvent;
import ci.kossovo.ventecoreapi.events.order.OrderCompletedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderConfirmedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderCreatedEvent;
import ci.kossovo.ventecoreapi.events.order.OrderTotalAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountDecrementedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountIncrementedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitOrderAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitRemovedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitUpdatedEvent;
import ci.kossovo.ventecoreapi.queries.orders.GetOrderDetailsQuery;
import ci.kossovo.ventecoreapi.queries.orders.GetOrderProduitDetailQuery;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.gateway.EventGateway;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class OrderProjector {

  private final OrderRepository orderRepository;
  // private final EventGateway eventGateway;
  private final ProduitRepository produitRepository;
  private final OrderMapper orderMapper;
  private final OrderLineRepository orderLineRepository;
  private final orderCustomerRepository orderCustomerRepository;

  @EventHandler
  public void on(OrderCreatedEvent event) {
    OrderCustomer customer = new OrderCustomer();
    BeanUtils.copyProperties(event.getCustomer(), customer);
orderCustomerRepository.save(customer);
    

    Order order = Order
      .builder()
      .orderId(event.getOrderId())
      .customer(customer)
      .orderStatus(event.getOrderStatus())
      .orderConfirmed(false)
      .orderTotal(0.0)
      .orderLines(new HashMap<>())
      .createdAt(new Date())
      .build();
    //BeanUtils.copyProperties(event, order);

    order = orderRepository.save(order);
    log.info("OrderProjector.on(OrderCreatedEvent) : {}", order);

   

      
   
  }



  @EventHandler
  public void on(OrderCompletedEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId()).orElse(null);
    if (order != null) {
      order.setOrderStatus(event.getOrderStatus());
      orderRepository.save(order);
    }
  }

  @EventHandler
  public void on(OrderConfirmedEvent event) {
    Order order = orderRepository.findById(event.getOrderId()).orElse(null);
    if (order != null) {
      order.setOrderConfirmed(true);
      orderRepository.save(order);
    }
  }

  @EventHandler
  public void on(OrderCanceledEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId()).orElse(null);
    if (order != null) {
      order.setOrderStatus(event.getOrderStatus());
      orderRepository.save(order);
    }
  }

  @EventHandler
  public void on(OrderTotalAddedEvent event) {
    Order order = orderRepository.findByOrderId(event.getOrderId()).orElse(null);
    if (order != null) {
      order.setOrderTotal(
        order.getOrderTotal() - event.getOldSomme() + event.getNewSomme()
      );
      orderRepository.save(order);
    }
  }

  //###################### Action sur OrderLine ##############################
  @EventHandler
  public void on(ProduitOrderAddedEvent event) {
    log.info(
      "OrderProjector.on(ProduitAddedOrderEvent) : {}",
      "produitAddedEvent received"
    );
    Order order = orderRepository.findByOrderId(event.getOrderId()).orElse(null);
    log.info("OrderProjector.on(ProduitAddedOrderEvent) : {}", "order found");

    Produit produit = produitRepository
      .findByIdProduit(event.getCodeProduit())
      .orElse(null);

    log.info("OrderProjector.on(ProduitAddedOrderEvent) : {}", "produit found");
    if (order != null && produit != null) {
      OrderLineEntity orderLine = OrderLineEntity
        .builder()
        .orderLineId(UUID.randomUUID().toString())
        .order(order)
        .produit(produit)
        .count(event.getQuantite())
        .prix(produit.getPrix())
        .montant(event.getQuantite() * produit.getPrix())
        .build();

      orderLine = orderLineRepository.save(orderLine);
      log.info(
        "OrderProjector.on(ProduitAddedOrderEvent) : {}",
        "orderLine saved"
      );
      order.setOrderTotal(order.getOrderTotal() + orderLine.getMontant());
      orderRepository.save(order);

      
    }
  }

  @EventHandler
  public void on(ProduitCountIncrementedEvent event) {
    log.info(
      "OrderProjector.on(ProduitCountIncrementedEvent) : {}",
      "produitCountIncrementedEvent received"
    );

    OrderLineEntity orderLine = orderLineRepository.findByOrderOrderIdAndProduitIdProduit(
      event.getOrderId(),
      event.getCodeProduit()
    );
    log.info(
      "OrderProjector.on(ProduitCountIncrementedEvent) : {}",
      "orderLine found"
    );

    Integer newCount = orderLine.getCount() + 1;
    orderLine.setCount(newCount);
    orderLine.setMontant(newCount * orderLine.getPrix());

    orderLineRepository.save(orderLine);
    log.info(
      "OrderProjector.on(ProduitCountIncrementedEvent) : {}",
      "orderLine saved"
    );
    OrderTotalAddedEvent evt = OrderTotalAddedEvent
      .builder()
      .orderId(event.getOrderId())
      .oldSomme(orderLine.getPrix() * (newCount - 1))
      .newSomme(orderLine.getPrix() * newCount)
      .build();
    eventGateway.publish(evt);
    eventGateway.publish(
      ProduitStockUpdatedEvent
        .builder()
        .codeProduit(event.getCodeProduit())
        .nombre(1)
        .build()
    );
  }

  @EventHandler
  public void on(ProduitCountDecrementedEvent event) {
    OrderLineEntity orderLine = orderLineRepository.findByOrderOrderIdAndProduitIdProduit(
      event.getOrderId(),
      event.getCodeProduit()
    );
    Integer newCount = orderLine.getCount() - 1;
    orderLine.setCount(newCount);
    orderLine.setMontant(newCount * orderLine.getPrix());

    orderLineRepository.save(orderLine);

    OrderTotalAddedEvent evt = OrderTotalAddedEvent
      .builder()
      .orderId(event.getOrderId())
      .oldSomme(orderLine.getPrix() * (newCount + 1))
      .newSomme(orderLine.getPrix() * newCount)
      .build();
    //Ajout de stock
    eventGateway.publish(evt);
    ProduitStockAddedEvent evt2 = ProduitStockAddedEvent
      .builder()
      .codeProduit(event.getCodeProduit())
      .nombre(1)
      .build();
    eventGateway.publish(evt2);
  }

  @EventHandler
  public void on(ProduitCountUpdatedEvent event) {
    OrderLineEntity orderLine = orderLineRepository.findByOrderOrderIdAndProduitIdProduit(
      event.getOrderId(),
      event.getCodeProduit()
    );
    Double oldTotal = orderLine.getMontant();
    Integer newCount = event.getCount();
    orderLine.setCount(newCount);
    orderLine.setMontant(newCount * orderLine.getPrix());

    orderLine = orderLineRepository.save(orderLine);

    OrderTotalAddedEvent evt = OrderTotalAddedEvent
      .builder()
      .orderId(event.getOrderId())
      .oldSomme(oldTotal)
      .newSomme(orderLine.getMontant())
      .build();
    eventGateway.publish(evt);

    eventGateway.publish(
      ProduitStockUpdatedEvent
        .builder()
        .codeProduit(event.getCodeProduit())
        .nombre(orderLine.getCount())
        .build()
    );
  }

  @EventHandler
  public void on(ProduitRemovedEvent event) {
    log.info(
      "OrderProjector.on(ProduitRemovedOrderEvent) : {}",
      "produitRemovedEvent received"
    );
    Order order = orderRepository.findByOrderId(event.getOrderId()).orElse(null);
    log.info("OrderProjector.on(ProduitRemovedOrderEvent) : {}", "order found");
    Produit produit = produitRepository
      .findByIdProduit(event.getCodeProduit())
      .orElse(null);
    log.info(
      "OrderProjector.on(ProduitRemovedOrderEvent) : {}",
      "produit found"
    );
    if (order != null && produit != null) {
      OrderLineEntity orderLine = orderLineRepository.findByOrderOrderIdAndProduitIdProduit(
        event.getOrderId(),
        event.getCodeProduit()
      );
      log.info(
        "OrderProjector.on(ProduitRemovedOrderEvent) : {}",
        "orderLine found"
      );
      orderLineRepository.delete(orderLine);
      log.info(
        "OrderProjector.on(ProduitRemovedOrderEvent) : {}",
        "orderLine deleted"
      );
      order.setOrderTotal(order.getOrderTotal() - event.getTotal());
      orderRepository.save(order);

      eventGateway.publish(
        ProduitStockAddedEvent
          .builder()
          .codeProduit(event.getCodeProduit())
          .nombre(orderLine.getCount())
          .build()
      );
    }
  }

  //Fin Action sur ProductService

  //######################Provient de ProductService ##################################
  @EventHandler
  public void on(ProduitCreatedEvent event) {
    Produit produit = Produit
      .builder()
      .idProduit(event.getCodeProduit())
      .titre(event.getTitre())
      .prix(event.getPrix())
      .build();

    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitUpdatedEvent event) {
    Produit produit = Produit
      .builder()
      .idProduit(event.getCodeProduit())
      .titre(event.getTitre())
      .prix(event.getPrix())
      .build();

    produitRepository.save(produit);
  }

  // fin de ProductService

  //###################### Debut @QueryHandler ##################################
  @QueryHandler
  public OrderDtos getOrderSaga(GetOrderDetailsQuery query) {
    Order order = orderRepository.findByOrderId(query.orderId()).orElse(null);
    if (order != null) {
      return orderMapper.dtoToOrder(order);
    } else {
      return null;
    }
  }

  @QueryHandler
  public OrderProduitDtos getOrderProduit(GetOrderProduitDetailQuery query) {
    Produit produit = produitRepository
      .findByIdProduit(query.codeProduit())
      .orElse(null);
    if (produit != null) {
      return orderMapper.dtoToProduit(produit);
    } else {
      return null;
    }
  }
  //Fin @QueryHandler

  // @QueryHandler
  // public OrderDtos findOrder(GetOrderDetailsQuery query) {
  //   Order order = repository.findById(query.getOrderId()).orElse(null);
  //   if (order != null) {
  //     return orderMapper.orderDtosToOrder(order);
  //   } else {
  //     return null;
  //   }
  // }
}
