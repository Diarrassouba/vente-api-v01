package ci.kossovo.orderqueryservices.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.orderqueryservices.data.entities.Order;
import ci.kossovo.orderqueryservices.data.entities.OrderLineEntity;
import ci.kossovo.orderqueryservices.data.entities.Produit;

public interface OrderLineRepository
  extends JpaRepository<OrderLineEntity, String> {
  List<Order> findByOrder(Order order);
  List<Produit> findByProduit(Produit produit);
  OrderLineEntity findByOrderOrderIdAndProduitIdProduit(
    String orderId,
    String idProduit
  );
  OrderLineEntity findByOrderAndProduit(Order order, Produit produit);
}
