package ci.kossovo.ventecoreapi.exceptions.order;

public class OrderAlreadyConfirmedException extends IllegalStateException {

  public OrderAlreadyConfirmedException(String orderId) {
    super(
      "Impossible d'effectuer l'opération car la commande [" +
      orderId +
      "] est déjà confirmée."
    );
  }
}
