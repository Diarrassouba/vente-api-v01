package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UpdateProduitCountCommand {
    @TargetAggregateIdentifier
    private final String orderId;
  
    private final String codeProduit;
    private final Integer count;
    
}
