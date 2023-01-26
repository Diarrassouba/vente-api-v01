package ci.kossovo.produitqueryservices.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;

public interface ProduitRepository
  extends JpaRepository<ProduitEntity, String> {
  Optional<ProduitEntity> findByCodeProduit(String codeProduit);
}
