package ci.kossovo.cardcommandservices.controller;

import ci.kossovo.cardcommandservices.commands.CreateCreditCardCommand;
import ci.kossovo.cardcommandservices.dtos.CardRequestDto;
import java.time.LocalDate;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cards/")
@Slf4j
public class CardController {

  @Autowired
  private CommandGateway commandGateway;

  @PostMapping
  public ResponseEntity<CompletableFuture<String>> create( @RequestBody @Valid CardRequestDto request) {
    log.info("**************************************************************");
    log.info("Le client de la carte: {}", request.toString());
    
    try {
      String cardId = UUID.randomUUID().toString().split("-")[0];
      LocalDate localDate = LocalDate.now();
      Integer validUntilMonth = localDate.getMonthValue();
      Integer validUntilYear = localDate.getYear() + 3;

      log.info("#############################################");
      log.info("Le client de la carte: {}", request.getUserId());
      CreateCreditCardCommand cmd = new CreateCreditCardCommand(
        cardId,
        request.getUserId(),
        validUntilMonth,
        validUntilYear,
        request.getBalance()
      );

      return new ResponseEntity<>(commandGateway.send(cmd), HttpStatus.OK);
    } catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
  }
}
