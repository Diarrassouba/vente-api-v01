package ci.kossovo.ventecoreapi.exceptions.order;

public class NotFindOrderException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NotFindOrderException(String orderId) {
    super(
      "Impossible de trouver la  commande  d'identifiant de [" + orderId + "]"
    );
  }
}
