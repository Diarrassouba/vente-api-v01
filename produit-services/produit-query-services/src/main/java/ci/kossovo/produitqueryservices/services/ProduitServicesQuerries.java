package ci.kossovo.produitqueryservices.services;

import java.util.List;

import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;
import ci.kossovo.produitqueryservices.data.repository.ProduitRepository;
import ci.kossovo.produitqueryservices.exceptions.NotFoundProduitException;
import ci.kossovo.produitqueryservices.mapperr.ProduitMapper;
import ci.kossovo.produitqueryservices.querries.ProduitAllQuery;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import ci.kossovo.ventecoreapi.queries.produits.GetProduitDetailsQuery;
import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ProduitServicesQuerries {

  private ProduitRepository produitRepository;
  private ProduitMapper produitMapper;

 

  @QueryHandler
  public List<ProduitDto> getAllProduits(ProduitAllQuery event) {
    List<ProduitEntity> produits = produitRepository.findAll();

    return produitMapper.ProduitsToDtos(produits);
  }

  @QueryHandler
  public ProduitDto getProduitById(GetProduitDetailsQuery query) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(query.getCodeProduit())
      .orElseThrow(
        () ->
          new NotFoundProduitException(
            "Le produit ID : " + query.getCodeProduit() + "n'existe pas."
          )
      );

    return produitMapper.produitToDto(produit);
  }
}
