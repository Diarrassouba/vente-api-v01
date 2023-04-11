package ci.kossovo.ordermongoqueryservices.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.ordermongoqueryservices.data.documents.Produit;

public interface ProduitRepository extends MongoRepository<Produit, String> {
    
}
