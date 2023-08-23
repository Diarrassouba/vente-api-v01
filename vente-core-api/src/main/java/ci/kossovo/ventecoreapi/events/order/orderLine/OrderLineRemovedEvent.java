package ci.kossovo.ventecoreapi.events.order.orderLine;

public record OrderLineRemovedEvent(
  String orderId,
  String codeProduit,
  Double total,
  Integer count
) {}
