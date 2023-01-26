package ci.kossovo.ventecoreapi.commands.produit;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Getter
@Setter
@Builder
public class UpdateProduitCommand {

  @TargetAggregateIdentifier
  private String codeProduit;

  private final String titre;
  private final Double prix;
  private String description;
}
