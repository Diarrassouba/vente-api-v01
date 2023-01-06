package ci.kossovo.customercommandservice.controllers;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import javax.validation.Valid;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kossovo.customercommandservice.commands.RegisterCustomerCommand;
import ci.kossovo.customercommandservice.models.CustomerRequest;

@RestController
//@RequiredArgsConstructor
// @CrossOrigin
@RequestMapping("/customer")
public class CustomerCmdComtroller {

  @Autowired
  private transient CommandGateway commandGateway;

  @PostMapping
  public CompletableFuture<String> register(
    @RequestBody @Valid CustomerRequest request
  ) {
    String customerId = UUID.randomUUID().toString().split("-")[0];

    RegisterCustomerCommand command = RegisterCustomerCommand
      .builder()
      .customerId(customerId)
      .nom(request.getNom())
      .prenom(request.getPrenom())
      .cel(request.getCel())
      .tel(request.getTel())
      .email(request.getEmail())
      .quartier(request.getQuartier())
      .ville(request.getVille())
      .build();

    if (command != null) {
      return commandGateway.send(command);
    } else {
      return null;
    }
  }
  //   @GetMapping
  //   public ResponseEntity<?> findAll() {
  //     try {
  //
  //       return new ResponseEntity<>("GetAll Results", HttpStatus.OK);
  //     } catch (Exception e) {
  //       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //     }
  //   }

  //   @GetMapping("/{id}")
  //   public ResponseEntity<?> find(@PathVariable Integer id) {
  //     try {
  //
  //       return new ResponseEntity<>("GetOne Result", HttpStatus.OK);
  //     } catch (Exception e) {
  //       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //     }
  //   }

  //   @PutMapping
  //   public ResponseEntity<?> update(@RequestBody Dto dto) {
  //     try {
  //
  //       return new ResponseEntity<>("Update Result", HttpStatus.OK);
  //     } catch (Exception e) {
  //       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //     }
  //   }

  //   @DeleteMapping("/{id}")
  //   public ResponseEntity<?> destroy(@PathVariable Integer id) {
  //     try {
  //
  //       return new ResponseEntity<>("Destroy Result", HttpStatus.OK);
  //     } catch (Exception e) {
  //       return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
  //     }
  //   }
}
