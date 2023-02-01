package ci.kossovo.ventecoreapi.dtos.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@NoArgsConstructor
@AllArgsConstructor
public class RequestConfirmDtos {
    private String orderId;
    private Long userId;
}
