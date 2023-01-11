package ci.kossovo.produitqueryservices.controllers;

import java.util.List;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kossovo.produitqueryservices.querries.ProduitAllQuery;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import ci.kossovo.ventecoreapi.queries.produits.GetProduitDetailsQuery;

@RestController
@RequestMapping("/produits")
public class ProduitQueryController {

  @Autowired
  private QueryGateway queryGateway;

  @GetMapping
  public List<ProduitDto> findAll() {
    ProduitAllQuery query = new ProduitAllQuery();

    return queryGateway
      .query(query, ResponseTypes.multipleInstancesOf(ProduitDto.class))
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
