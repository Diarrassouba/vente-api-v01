package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ConfirmOrderCommand {
    @TargetAggregateIdentifier
    private final String orderId;
    private final Long userId;

}
