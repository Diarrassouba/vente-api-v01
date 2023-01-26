package ci.kossovo.produitqueryservices.controllers;

import ci.kossovo.produitqueryservices.models.ProduitQueryResponse;
import ci.kossovo.produitqueryservices.querries.ProduitAllQuery;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import ci.kossovo.ventecoreapi.queries.produits.GetProduitDetailsQuery;
import java.util.List;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/produits")
public class ProduitQueryController {

  @Autowired
  private QueryGateway queryGateway;

  @GetMapping
  public ProduitQueryResponse findAll() {
    ProduitAllQuery query = new ProduitAllQuery();

    return queryGateway
      .query(query, ResponseTypes.instanceOf(ProduitQueryResponse.class))
      .join();
  }

  @GetMapping("/{codeProduit}")
  public ProduitDto getProduitById(@PathVariable String codeProduit) {
    GetProduitDetailsQuery getProduitDetailsQuery = new GetProduitDetailsQuery(
      codeProduit
    );

    return queryGateway
      .query(getProduitDetailsQuery, ResponseTypes.instanceOf(ProduitDto.class))
      .join();
  }
}
