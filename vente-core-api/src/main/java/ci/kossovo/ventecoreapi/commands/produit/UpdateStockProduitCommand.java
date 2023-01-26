package ci.kossovo.ventecoreapi.commands.produit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@AllArgsConstructor
@Builder
public class UpdateStockProduitCommand {

  @TargetAggregateIdentifier
  private String codeProduit;

  private final int nombre;
}
