package ci.kossovo.produitqueryservices.projectors;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;
import ci.kossovo.produitqueryservices.data.repository.ProduitRepository;
import ci.kossovo.produitqueryservices.mapperr.ProduitMapper;
import ci.kossovo.produitqueryservices.models.ProduitQueryResponse;
import ci.kossovo.produitqueryservices.querries.ProduitAllQuery;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import ci.kossovo.ventecoreapi.queries.produits.GetProduitDetailsQuery;
import java.util.List;
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
    List<ProduitEntity> produits = produitRepository.findAll();

    return new ProduitQueryResponse(produitMapper.produitsToDtos(produits));
  }

  @QueryHandler
  public ProduitDto getProduitById(GetProduitDetailsQuery query) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(query.getCodeProduit())
      .orElseThrow(() ->
        new NotFoundProduitException(
          "Le produit ID : " + query.getCodeProduit() + "n'existe pas."
        )
      );

    return produitMapper.produitToDto(produit);
  }
}
