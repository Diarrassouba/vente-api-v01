package ci.kossovo.orderqueryservices.data.mappers;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import ci.kossovo.orderqueryservices.data.entities.Order;
import ci.kossovo.orderqueryservices.data.entities.Produit;
import ci.kossovo.ventecoreapi.dtos.order.OrderDtos;
import ci.kossovo.ventecoreapi.dtos.produits.OrderProduitDtos;

@Mapper(componentModel = "spring")
public interface OrderMapper {
  OrderDtos dtoToOrder(Order order);

  @InheritInverseConfiguration
  Order orderToDtos(OrderDtos orderDtos);

  @Mapping(source = "produit.idProduit", target = "code")
  OrderProduitDtos dtoToProduit(Produit produit);

  @InheritInverseConfiguration
  Produit produitToDtos(OrderProduitDtos produitDtos);
  //OrderDtos orderDtosToOrder(Order order);

  // Order orderToOrderDtos(OrderDtos orderDtos);
}
