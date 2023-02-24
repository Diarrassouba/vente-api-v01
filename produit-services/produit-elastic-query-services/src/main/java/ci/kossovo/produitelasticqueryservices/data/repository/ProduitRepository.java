package ci.kossovo.produitelasticqueryservices.data.repository;

import ci.kossovo.produitelasticqueryservices.data.entities.Produit;
import java.util.Optional;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface ProduitRepository
  extends ElasticsearchRepository<Produit, String> {
  Optional<Produit> findByTitre(String titre);
  Optional<Produit> findByCodeProduit(String code);
}
