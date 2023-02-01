package ci.kossovo.ventecoreapi.exceptions.order;

public class NotFindOrderConfirmedException extends RuntimeException {

  public NotFindOrderConfirmedException(String orderId) {
    super(
      "Impossible de trouver la  commande confirmee d'identifiant de [" +
      orderId +
      "]"
    );
  }
}
