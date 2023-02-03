package ci.kossovo.ordercommandservices.services;

import ci.kossovo.ordercommandservices.models.Produit;
import ci.kossovo.ordercommandservices.reposotories.ProduitRepository;
import ci.kossovo.ventecoreapi.events.produit.ProduitCountUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitCreatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockAddedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitStockUpdatedEvent;
import ci.kossovo.ventecoreapi.events.produit.ProduitUpdatedEvent;
import ci.kossovo.ventecoreapi.exceptions.produits.NotFoundProduitException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProduitEventHandler {

  private final ProduitRepository produitRepository;

  @EventHandler
  public void on(ProduitCreatedEvent event) {
    Produit produit = new Produit(
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
    Produit produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));
      
    produit.setStock(produit.getStock() - event.getNombre());

    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitStockAddedEvent event) {
    Produit produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setStock(produit.getStock() + event.getNombre());
    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitCountUpdatedEvent event) {
    Produit produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setStock(event.getCount());
    produitRepository.save(produit);
  }

  @EventHandler
  public void on(ProduitUpdatedEvent event) {
    Produit produit = produitRepository
      .findByCodeProduit(event.getCodeProduit())
      .orElseThrow(() -> new NotFoundProduitException(event.getCodeProduit()));

    produit.setCodeProduit(event.getCodeProduit());
    produit.setTitre(event.getTitre());
    produit.setPrix(event.getPrix());
    produit.setDescription(event.getDescription());

    produitRepository.save(produit);
  }
}
