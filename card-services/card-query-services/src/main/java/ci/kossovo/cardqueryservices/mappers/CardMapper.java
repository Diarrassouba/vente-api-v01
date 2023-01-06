package ci.kossovo.cardqueryservices.mappers;

import ci.kossovo.cardqueryservices.data.documents.Card;
import ci.kossovo.ventecoreapi.dtos.cards.CardDetailDto;
import ci.kossovo.ventecoreapi.dtos.cards.CardDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CardMapper {
  
  CardDto cardDtoToCard(Card card);

  List<CardDto> cardDtoListToCardList(List<Card> cards);

  @Mapping(source = "card.id", target = "cardCreditId")
  CardDetailDto fromCard(Card card);
}
