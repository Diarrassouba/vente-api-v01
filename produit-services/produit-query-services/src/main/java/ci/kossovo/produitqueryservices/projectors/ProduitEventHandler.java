package ci.kossovo.produitqueryservices.projectors;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;
import ci.kossovo.produitqueryservices.data.repository.ProduitRepository;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitUpdatedEvent;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import lombok.AllArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProduitEventHandler {

  private ProduitRepository produitRepository;

  @EventHandler
  public void on(ProduitCreatedEvent event) {
    ProduitEntity produit = new ProduitEntity(
      event.getCodeProduit(),
      event.getTitre(),
      event.getPrix(),
      event.getStock(),
      event.getDescription()
    );

    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitStockUpdatedEvent event) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElse(null);

    if (produit != null) {
      produit.setStock(produit.getStock() - event.getNombre());
      produitRepository.save(produit);
    }
  }

  @EventHandler
  public void on(ProduitStockAddedEvent event) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setStock(produit.getStock() + event.getNombre());
    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitCountUpdatedEvent event) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setStock(event.getCount());
    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitUpdatedEvent event) {
    ProduitEntity produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setCodeProduit(event.getCodeProduit());
    produit.setTitre(event.getTitre());
    produit.setPrix(event.getPrix());
    produit.setDescription(event.getDescription());

    produitRepository.save(produit);
  }
}