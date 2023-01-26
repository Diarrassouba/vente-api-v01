package ci.kossovo.produitqueryservices.services;

import ci.kossovo.produitqueryservices.data.entities.ProduitEntity;
import ci.kossovo.produitqueryservices.data.repository.ProduitRepository;
import ci.kossovo.produitqueryservices.mapperr.ProduitMapper;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class ProduitServices {

  private final ProduitRepository produitRepository;
  private final ProduitMapper produitMapper;

  public void creerProduit(ProduitEntity entity) {
    produitRepository.save(entity);
  }
}
