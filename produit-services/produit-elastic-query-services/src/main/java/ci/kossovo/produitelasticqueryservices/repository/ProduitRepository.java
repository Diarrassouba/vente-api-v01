package ci.kossovo.produitelasticqueryservices.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.produitelasticqueryservices.data.entities.Produit;

public interface ProduitRepository extends JpaRepository<Produit, String> {
  Optional<Produit> findByCodeProduit(String codeProduit);
}
