package ci.kossovo.ordercommandservices.controllers;

import ci.kossovo.ventecoreapi.commands.order.AddProduitOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.ConfirmOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CreateOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.DecrementProduitCountCommand;
import ci.kossovo.ventecoreapi.commands.order.IncrementProduitCountCommand;
import ci.kossovo.ventecoreapi.dtos.order.CreateOrderProdRequest;
import ci.kossovo.ventecoreapi.dtos.order.OrderDtos;
import ci.kossovo.ventecoreapi.dtos.order.OrderProduitAddRequest;
import ci.kossovo.ventecoreapi.dtos.order.OrderRequest;
import ci.kossovo.ventecoreapi.dtos.order.RequestConfirmDtos;
import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import ci.kossovo.ventecoreapi.exceptions.order.NotFindOrderConfirmedException;
import ci.kossovo.ventecoreapi.queries.orders.GetOrderDetailsQuery;
import ci.kossovo.ventecoreapi.queries.orders.GetOrderProduitDetailQuery;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
@AllArgsConstructor
@Slf4j
public class OrderCommandController {

  private CommandGateway commandGateway;
  private QueryGateway queryGateway;

  @PostMapping
  public CompletableFuture<String> createOrder(
    @RequestBody OrderRequest orderDtosRequest
  ) {
    GetOrderProduitDetailQuery getOrderProduitDetailQuery = new GetOrderProduitDetailQuery(
      orderDtosRequest.getProduitId()
    );
    OrderProduitDtos produit = null;
    try {
      produit =
        queryGateway
          .query(
            getOrderProduitDetailQuery,
            ResponseTypes.instanceOf(OrderProduitDtos.class)
          )
          .join();

      String orderId = UUID.randomUUID().toString();

      CreateOrderCommand command = CreateOrderCommand
        .builder()
        .orderId(orderId)
        .codeProduit(orderDtosRequest.getProduitId())
        .produit(produit)
        .quantite(orderDtosRequest.getQuantite())
        .userId(orderDtosRequest.getUserId())
        .orderStatus(OrderStatus.CREATED)
        .build();
      //BeanUtils.copyProperties(orderDtos, command);

      return commandGateway.send(command);
    } catch (Exception e) {
      throw new NotFindOrderConfirmedException(orderDtosRequest.getProduitId());
    }
  }

  @PostMapping("/simpleorder/{userId}")
  public CompletableFuture<String> createSimpleOrder(
    @PathVariable("userId") Long userId
  ) {
    String orderId = UUID.randomUUID().toString();

    CreateOrderCommand command = CreateOrderCommand
      .builder()
      .orderId(orderId)
      .produit(null)
      .quantite(null)
      .userId(userId)
      .orderStatus(OrderStatus.CREATED)
      .build();
    //BeanUtils.copyProperties(orderDtos, command);

    return commandGateway.send(command);
  }

  @PostMapping("/orderthroughproduit")
  public CompletableFuture<String> orderethroughproduit(
    @RequestBody CreateOrderProdRequest createOrderProdRequest
  ) {
    GetOrderProduitDetailQuery getOrderProduitDetailQuery = new GetOrderProduitDetailQuery(
      createOrderProdRequest.getProduitId()
    );
    OrderProduitDtos produit = null;
    try {
      produit =
        queryGateway
          .query(
            getOrderProduitDetailQuery,
            ResponseTypes.instanceOf(OrderProduitDtos.class)
          )
          .join();

      String orderId = UUID.randomUUID().toString();

      CreateOrderCommand command = CreateOrderCommand
        .builder()
        .orderId(orderId)
        .codeProduit(createOrderProdRequest.getProduitId())
        .produit(produit)
        .quantite(1)
        .userId(createOrderProdRequest.getUserId())
        .orderStatus(OrderStatus.CREATED)
        .build();

      return commandGateway.send(command);
    } catch (Exception e) {
      throw new NotFindOrderConfirmedException(
        createOrderProdRequest.getProduitId()
      );
    }
  }

  @PostMapping("/confirm")
  public CompletableFuture<Void> confirm(
    @RequestBody RequestConfirmDtos request
  ) {
    GetOrderDetailsQuery getOrderDetailsQuery = new GetOrderDetailsQuery(
      request.getOrderId()
    );
    OrderDtos orderDtos = null;
    try {
      orderDtos =
        queryGateway
          .query(
            getOrderDetailsQuery,
            ResponseTypes.instanceOf(OrderDtos.class)
          )
          .join();

      ConfirmOrderCommand confirmOrderCommand = ConfirmOrderCommand
        .builder()
        .orderId(request.getOrderId())
        .userId(orderDtos.getUserId())
        .build();
      return commandGateway.send(confirmOrderCommand);
    } catch (Exception e) {
      throw new NotFindOrderConfirmedException(request.getOrderId());
    }
  }

  @PostMapping("/produit")
  public CompletableFuture<Void> createOrderProduit(
    @RequestBody OrderProduitAddRequest orderProduitAddRequest
  ) {
    GetOrderDetailsQuery getOrderDetailsQuery = new GetOrderDetailsQuery(
      orderProduitAddRequest.getOrderId()
    );
    GetOrderProduitDetailQuery getOrderProduitDetailQuery = new GetOrderProduitDetailQuery(
      orderProduitAddRequest.getCodeProduit()
    );
    OrderDtos orderDtos = null;
    OrderProduitDtos produitDto = null;
    try {
      orderDtos =
        queryGateway
          .query(
            getOrderDetailsQuery,
            ResponseTypes.instanceOf(OrderDtos.class)
          )
          .join();

      produitDto =
        queryGateway
          .query(
            getOrderProduitDetailQuery,
            ResponseTypes.instanceOf(OrderProduitDtos.class)
          )
          .join();
      AddProduitOrderCommand command = AddProduitOrderCommand
        .builder()
        .orderId(orderProduitAddRequest.getOrderId())
        .codeProduit(orderProduitAddRequest.getCodeProduit())
        .orderDtos(orderDtos)
        .orderProduitDtos(produitDto)
        .quantite(orderProduitAddRequest.getQuantite())
        .build();
      return commandGateway.send(command);
    } catch (Exception e) {
      throw new RuntimeException(e.getMessage());
    }
  }

  @PostMapping("/produit/increment/{orderId}/{produitId}")
  public CompletableFuture<Void> incrementOrderProduit(
    @PathVariable("orderId") String orderId,
    @PathVariable("produitId") String produitId
  ) {
    log.info("incrementOrderProduit receved orderId : {}", orderId);

    IncrementProduitCountCommand command = IncrementProduitCountCommand
      .builder()
      .orderId(orderId)
      .codeProduit(produitId)
      .build();
    return commandGateway.send(command);
  }

  @PostMapping("/produit/decrement/{orderId}/{produitId}")
  public CompletableFuture<Void> decrementOrderProduit(
    @PathVariable("orderId") String orderId,
    @PathVariable("produitId") String produitId
  ) {
    return commandGateway.send(
      DecrementProduitCountCommand
        .builder()
        .orderId(orderId)
        .codeProduit(produitId)
        .build()
    );
  }
}
