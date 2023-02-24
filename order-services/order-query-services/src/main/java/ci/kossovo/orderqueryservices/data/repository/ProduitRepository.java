package ci.kossovo.orderqueryservices.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.orderqueryservices.data.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
  Optional<Produit> findByIdProduit(String idProduit);
}
