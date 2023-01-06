package ci.kossovo.cardqueryservices.data.repositories;

import ci.kossovo.cardqueryservices.data.documents.Card;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CardRepository extends MongoRepository<Card, String> {

    Optional<Card>  findByCardNumber(String cardNumber);
}
