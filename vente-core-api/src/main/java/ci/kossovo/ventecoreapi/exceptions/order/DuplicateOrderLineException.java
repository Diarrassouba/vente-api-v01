package ci.kossovo.ventecoreapi.exceptions.order;

public class DuplicateOrderLineException extends IllegalStateException {

  public DuplicateOrderLineException(String productId) {
    super(
      "Impossible de dupliquer la ligne de commande pour l'identifiant de produit [" +
      productId +
      "]"
    );
  }
}
