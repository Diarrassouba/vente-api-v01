package ci.kossovo.cardqueryservices.servicees;


import java.util.List;

import org.springframework.stereotype.Service;

import ci.kossovo.cardqueryservices.data.documents.Card;
import ci.kossovo.cardqueryservices.data.repositories.CardRepository;
import ci.kossovo.cardqueryservices.mappers.CardMapper;
import ci.kossovo.ventecoreapi.dtos.cards.CardDetailDto;
import ci.kossovo.ventecoreapi.dtos.cards.CardDto;
import ci.kossovo.ventecoreapi.exceptions.card.NotFoundCardException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CardService {

  private final CardRepository cardRepository;
  private final CardMapper cardMapper;

  public Card saveCard(Card card) {
    return cardRepository.save(card);
  }


  public CardDto findByIdCard(String id) {
    Card card = cardRepository
      .findById(id)
      .orElseThrow(() -> new NotFoundCardException(id));

    return cardMapper.cardDtoToCard(card);
  }

  public CardDto findByNumberCard(String number) {
    Card card = cardRepository
      .findByCardNumber(number)
      .orElseThrow(() -> new NotFoundCardException(number));

    return cardMapper.cardDtoToCard(card);
  }

  public CardDetailDto getByNumberCard(String number) {
    Card card = cardRepository
      .findByCardNumber(number)
      .orElseThrow(() -> new NotFoundCardException(number));

    return cardMapper.fromCard(card);
  }

  public List<CardDto> findAllCrditCard() {
    List<Card> cardList = cardRepository.findAll();
    return cardMapper.cardDtoListToCardList(cardList);
  }
}
