package ci.kossovo.cardqueryservices.projections;

import ci.kossovo.cardqueryservices.models.GetCardQueryResponse;
import ci.kossovo.cardqueryservices.servicees.CardService;
import ci.kossovo.ventecoreapi.dtos.cards.CardDetailDto;
import ci.kossovo.ventecoreapi.dtos.cards.CardDto;
import ci.kossovo.ventecoreapi.queries.cards.FindCardByNumberQuery;
import ci.kossovo.ventecoreapi.queries.cards.GetCreditCardDetailByIdQuery;
import ci.kossovo.ventecoreapi.queries.cards.GetCreditCardDetailByNumberQuery;
import ci.kossovo.ventecoreapi.queries.customers.GetAllCustomerQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardQueryHandler {

  private final CardService cardService;

  @QueryHandler
  public CardDto findByIdCard(GetCreditCardDetailByIdQuery query) {
    return cardService.findByIdCard(query.id());
  }

  @QueryHandler
  public CardDto findByNumber(GetCreditCardDetailByNumberQuery query) {
    return cardService.findByNumberCard(query.cardNumber());
  }

  @QueryHandler
  public CardDetailDto getByNumber(FindCardByNumberQuery query) {
    return cardService.getByNumberCard(query.number());
  }

  @QueryHandler
  public GetCardQueryResponse findAll(GetAllCustomerQuery query) {
    return new GetCardQueryResponse(cardService.findAllCrditCard());
  }
}
