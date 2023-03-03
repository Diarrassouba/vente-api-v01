package ci.kossovo.ordermongoqueryservices.projections;

import ci.kossovo.ordermongoqueryservices.data.repository.OrderRepository;
import ci.kossovo.ventecoreapi.events.order.OrderCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitOrderAddedEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  private final OrderRepository orderRepository;

  // private final ProduitRepository produitRepository;

  @EventHandler
  public void on(OrderCreatedEvent event) {}

  @EventHandler
  public void on(ProduitOrderAddedEvent event) {}
}
