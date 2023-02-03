package ci.kossovo.ordercommandservices.reposotories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.ordercommandservices.models.Produit;

public interface ProduitRepository
  extends JpaRepository<Produit, String> {
  Optional<Produit> findByCodeProduit(String codeProduit);
}
