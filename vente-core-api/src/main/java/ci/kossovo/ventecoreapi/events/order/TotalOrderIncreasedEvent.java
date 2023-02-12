package ci.kossovo.ventecoreapi.events.order;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TotalOrderIncreasedEvent {
    private final String orderId;

    private final Double montant;
}
