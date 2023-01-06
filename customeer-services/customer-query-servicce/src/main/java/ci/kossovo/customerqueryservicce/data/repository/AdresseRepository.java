package ci.kossovo.customerqueryservicce.data.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import ci.kossovo.customerqueryservicce.data.documents.Adresse;

public interface AdresseRepository extends MongoRepository<Adresse, String> {
  // Optional<Adresse> findByAdresseId(String adresseId);
}
