package ci.kossovo.ordercommandservices.controllers;

import ci.kossovo.ventecoreapi.commands.order.AddProduitOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.ConfirmOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CreateOrderCommand;
import ci.kossovo.ventecoreapi.commands.order.CreateOrderSimpleCommand;
import ci.kossovo.ventecoreapi.commands.order.DecrementProduitCountCommand;
import ci.kossovo.ventecoreapi.commands.order.IncrementProduitCountCommand;
import ci.kossovo.ventecoreapi.dtos.order.CreateOrderProdRequest;
import ci.kossovo.ventecoreapi.dtos.order.OrderProduitAddRequest;
import ci.kossovo.ventecoreapi.dtos.order.OrderRequest;
import ci.kossovo.ventecoreapi.dtos.order.RequestConfirmDtos;
import ci.kossovo.ventecoreapi.enums.OrderStatus;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
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

  // private QueryGateway queryGateway;

  @PostMapping
  public CompletableFuture<String> createOrder( @RequestBody OrderRequest orderDtosRequest) {
    String orderId = UUID.randomUUID().toString();

    CreateOrderCommand command = CreateOrderCommand
      .builder()
      .orderId(orderId)
      .codeProduit(orderDtosRequest.getProduitId())
      .quantite(orderDtosRequest.getQuantite())
      .customerId(orderDtosRequest.getCustomerId())
      .orderStatus(OrderStatus.CREATED)
      .build();

    if(command!=null){
      return commandGateway.send(command);
    }else{return new CompletableFuture<>();}
  }

  @PostMapping("/simpleorder/{userId}")
  public CompletableFuture<String> createSimpleOrder(
    @PathVariable("userId") Long userId
  ) {
    String orderId = UUID.randomUUID().toString();

    CreateOrderSimpleCommand command = CreateOrderSimpleCommand
      .builder()
      .orderId(orderId)
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
    String orderId = UUID.randomUUID().toString();

    CreateOrderCommand command = CreateOrderCommand
      .builder()
      .orderId(orderId)
      .codeProduit(createOrderProdRequest.getProduitId())
      .quantite(1)
      .customerId(createOrderProdRequest.getUserId())
      .orderStatus(OrderStatus.CREATED)
      .build();

    return commandGateway.send(command);
  }

  @PostMapping("/confirm")
  public CompletableFuture<Void> confirm(
    @RequestBody RequestConfirmDtos request
  ) {
    ConfirmOrderCommand confirmOrderCommand = ConfirmOrderCommand
      .builder()
      .orderId(request.getOrderId())
      .userId(request.getUserId())
      .build();
    return commandGateway.send(confirmOrderCommand);
  }

  @PostMapping("/produit")
  public CompletableFuture<Void> createOrderProduit(
    @RequestBody OrderProduitAddRequest orderProduitAddRequest
  ) {
    AddProduitOrderCommand command = AddProduitOrderCommand
      .builder()
      .orderId(orderProduitAddRequest.getOrderId())
      .codeProduit(orderProduitAddRequest.getCodeProduit())
      .quantite(orderProduitAddRequest.getQuantite())
      .build();
    return commandGateway.send(command);
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
    DecrementProduitCountCommand command = DecrementProduitCountCommand
      .builder()
      .orderId(orderId)
      .codeProduit(produitId)
      .build();

    return commandGateway.send(command);
  }
}
