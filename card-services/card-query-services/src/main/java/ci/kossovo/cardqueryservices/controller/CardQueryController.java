package ci.kossovo.cardqueryservices.controller;

import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kossovo.cardqueryservices.models.GetCardQueryResponse;
import ci.kossovo.ventecoreapi.dtos.cards.CardDetailDto;
import ci.kossovo.ventecoreapi.dtos.cards.CardDto;
import ci.kossovo.ventecoreapi.queries.cards.FindCardByNumberQuery;
import ci.kossovo.ventecoreapi.queries.cards.GetCreditCardDetailByIdQuery;
import ci.kossovo.ventecoreapi.queries.cards.GetCreditCardDetailByNumberQuery;
import ci.kossovo.ventecoreapi.queries.customers.GetAllCustomerQuery;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cards")
@RequiredArgsConstructor
public class CardQueryController {

  private final QueryGateway queryGateway;

  @GetMapping("/{id}")
  public ResponseEntity<CardDto> findById(@PathVariable String id) {
    try {
      GetCreditCardDetailByIdQuery query = new GetCreditCardDetailByIdQuery(id);
      return new ResponseEntity<>(
        
        queryGateway
          .query(query, ResponseTypes.instanceOf(CardDto.class))
          .join(),
        HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/card/{number}")
  public ResponseEntity<CardDto> findByNumber(@PathVariable String number) {
    try {
      GetCreditCardDetailByNumberQuery query = new GetCreditCardDetailByNumberQuery(
        number
      );

      return new ResponseEntity<>(
        queryGateway
          .query(query, ResponseTypes.instanceOf(CardDto.class))
          .join(),
        HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping("/detail/{number}")
  public ResponseEntity<CardDetailDto> getByNumber(
    @PathVariable String number
  ) {
    try {
      FindCardByNumberQuery query = new FindCardByNumberQuery(number);

      return new ResponseEntity<>(
        queryGateway
          .query(query, ResponseTypes.instanceOf(CardDetailDto.class))
          .join(),
        HttpStatus.OK
      );
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }

  @GetMapping
  public GetCardQueryResponse getAllCards() {
    try {
      GetAllCustomerQuery query = new GetAllCustomerQuery();

      return queryGateway
        .query(query, ResponseTypes.instanceOf(GetCardQueryResponse.class))
        .get();
    } catch (Exception e) {
      return new GetCardQueryResponse(null);
    }
  }
}
