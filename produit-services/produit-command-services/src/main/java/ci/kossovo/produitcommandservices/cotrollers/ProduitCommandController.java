package ci.kossovo.produitcommandservices.cotrollers;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ci.kossovo.produitcommandservices.dtos.ProduitRequestDto;
import ci.kossovo.produitcommandservices.dtos.ProduitStockRequestDtos;
import ci.kossovo.produitcommandservices.dtos.ProduitUpdateRequest;
import ci.kossovo.ventecoreapi.commands.produit.AddStockProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.CreateProduitCommand;
import ci.kossovo.ventecoreapi.commands.produit.UpdateProduitCommand;
import ci.kossovo.ventecoreapi.dtos.produits.ProduitDto;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/product/command")
@AllArgsConstructor
public class ProduitCommandController {

  private CommandGateway commandGateway;

  @PostMapping
  public CompletableFuture<String> createProduct(
    @RequestBody ProduitRequestDto request
  ) {
    CreateProduitCommand command = CreateProduitCommand
      .builder()
      .codeProduit(UUID.randomUUID().toString())
      .titre(request.getTitre())
      .description(request.getDescription())
      .prix(request.getPrix())
      .stock(request.getStock())
      .build();

    return commandGateway.send(command);
  }

  @PutMapping("/{codeProduit}")
  public CompletableFuture<String> updateProduct(
    @PathVariable String codeProduit,
    @RequestBody ProduitUpdateRequest request
  ) {
    UpdateProduitCommand command = UpdateProduitCommand
      .builder()
      .codeProduit(codeProduit)
      .titre(request.getTitre())
      .description(request.getDescription())
      .prix(request.getPrix())
      .build();

    return commandGateway.send(command);
  }

  @PatchMapping("/stock")
  public CompletableFuture<ProduitDto> addStockProduit(
    @RequestBody ProduitStockRequestDtos request
  ) {
    AddStockProduitCommand command = AddStockProduitCommand
      .builder()
      .codeProduit(request.getCodeProduit())
      .nombre(request.getNombre())
      .build();

    return commandGateway.send(command);
  }
}
