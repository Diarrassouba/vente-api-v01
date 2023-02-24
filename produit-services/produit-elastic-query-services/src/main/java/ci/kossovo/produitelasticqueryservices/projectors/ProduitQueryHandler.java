package ci.kossovo.produitelasticqueryservices.projectors;

import ci.kossovo.produitelasticqueryservices.data.entities.Produit;
import ci.kossovo.produitelasticqueryservices.data.repository.ProduitRepository;
import ci.kossovo.produitelasticqueryservices.mapperr.ProduitMapper;
import ci.kossovo.produitelasticqueryservices.models.ProduitQueryResponse;
import ci.kossovo.produitelasticqueryservices.querries.ProduitAllQuery;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import ci.kossovo.ventecoreapi.queries.produits.GetProduitDetailsQuery;
import java.util.List;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

import lombok.AllArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ProduitQueryHandler {

  private ProduitRepository produitRepository;
  private ProduitMapper produitMapper;

  @QueryHandler
  public ProduitQueryResponse getAllProduits(ProduitAllQuery query) {
    List<Produit> produits = StreamSupport.stream(
      Spliterators.spliteratorUnknownSize(produitRepository.findAll().iterator(), 0), false)
.toList();

    return new ProduitQueryResponse(produitMapper.produitsToDtos(produits));
  }

  @QueryHandler
  public ProduitDto getProduitById(GetProduitDetailsQuery query) {
    Produit produit = produitRepository
      .findByCodeProduit(query.getCodeProduit())
      .orElseThrow(() ->
        new NotFoundProduitException(
          "Le produit ID : " + query.getCodeProduit() + "n'existe pas."
        )
      );

    return produitMapper.produitToDto(produit);
  }
}
