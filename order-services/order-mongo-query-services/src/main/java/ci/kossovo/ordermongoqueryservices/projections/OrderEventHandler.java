package ci.kossovo.ordermongoqueryservices.projections;

import ci.kossovo.ordermongoqueryservices.data.documents.Article;
import ci.kossovo.ordermongoqueryservices.data.documents.Order;
import ci.kossovo.ordermongoqueryservices.data.documents.OrderCustomer;
import ci.kossovo.ordermongoqueryservices.data.documents.Produit;
import ci.kossovo.ordermongoqueryservices.data.repository.CustomerRepository;
import ci.kossovo.ordermongoqueryservices.data.repository.OrderRepository;
import ci.kossovo.ordermongoqueryservices.data.repository.ProduitRepository;
import ci.kossovo.ventecoreapi.events.order.OrderCreatedEvent;
import ci.kossovo.ventecoreapi.events.order.orderLine.OrderLineCountIncrementedEvent;
import ci.kossovo.ventecoreapi.events.order.orderLine.OrderLineRemovedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitOrderAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitUpdatedEvent;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import java.util.Date;
import java.util.HashMap;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventHandler {

  private final OrderRepository orderRepository;
  // private final EventGateway eventGateway;
  private final ProduitRepository articleRepository;
  // private final OrderMapper orderMapper;
  // private final OrderLineRepository orderLineRepository;
  private final CustomerRepository orderCustomerRepository;

  @EventHandler
  public void on(OrderCreatedEvent event) {
    OrderCustomer customer = new OrderCustomer();
    BeanUtils.copyProperties(event.getCustomer(), customer);
    orderCustomerRepository.save(customer);

    Order order = Order
      .builder()
      .id(event.getOrderId())
      .customer(customer)
      .orderStatus(event.getOrderStatus())
      .orderConfirmed(false)
      .orderTotal(0.0)
      .articles(new HashMap<>())
      .createdAt(new Date())
      .build();
    //BeanUtils.copyProperties(event, order);

    order = orderRepository.save(order);
  }

  @EventHandler
  public void on(OrderLineRemovedEvent event) {
    Order order = orderRepository.findById(event.orderId()).orElse(null);

    Produit produit = articleRepository
      .findById(event.codeProduit())
      .orElse(null);

    Article article = null;

    if (order != null && produit != null) {
      article =
        Article
          .builder()
          .articleId(produit.getId())
          .titre(produit.getTitre())
          .prix(produit.getPrix())
          .description(produit.getDescription())
          .count(event.count())
          .montant(event.total())
          .build();

      order.getArticles().remove(article.getArticleId());
      order.setOrderTotal(order.getOrderTotal() - event.total());

      orderRepository.save(order);
    }
  }

  @EventHandler
  public void on(OrderLineCountIncrementedEvent event) {}

  @EventHandler
  public void on(ProduitOrderAddedEvent event) {
    Order order = orderRepository.findById(event.getOrderId()).orElse(null);

    Produit produit = articleRepository
      .findById(event.getCodeProduit())
      .orElse(null);

    if (order != null && produit != null) {
      Article article = Article
        .builder()
        .articleId(produit.getId())
        .titre(produit.getTitre())
        .prix(produit.getPrix())
        .description(produit.getDescription())
        .count(event.getQuantite())
        .montant(event.getQuantite() * produit.getPrix())
        .build();

      order.getArticles().put(article.getArticleId(), article);

      order.setOrderTotal(order.getOrderTotal() + article.getMontant());
      orderRepository.save(order);
    }
  }

  // ********************Actions sur ProduitServices********

  @EventHandler
  public void on(ProduitCreatedEvent event) {
    Produit produit = new Produit(
      event.getCodeProduit(),
      event.getTitre(),
      event.getPrix(),
      event.getDescription()
    );

    articleRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitUpdatedEvent event) {
    Produit produit = articleRepository
      .findById(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setId(event.getCodeProduit());
    produit.setTitre(event.getTitre());
    produit.setPrix(event.getPrix());
    produit.setDescription(event.getDescription());

    articleRepository.save(produit);
  }
}
