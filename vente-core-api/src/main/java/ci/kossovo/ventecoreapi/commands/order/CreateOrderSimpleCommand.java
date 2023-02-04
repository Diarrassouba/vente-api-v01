package ci.kossovo.ventecoreapi.commands.order;

import org.axonframework.modelling.command.TargetAggregateIdentifier;

import ci.kossovo.ventecoreapi.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderSimpleCommand {
    @TargetAggregateIdentifier
    private String orderId;
    private final Long userId;
    private final OrderStatus orderStatus;
}
