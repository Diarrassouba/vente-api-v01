package ci.kossovo.ventecoreapi.exceptions.order;

public class NotFindOrderProduitConfirmedException extends RuntimeException {

  public NotFindOrderProduitConfirmedException(String codeProduit) {
    super(
      "Impossible de trouver le produit de la commande confirmee d'identifiant de [" +
      codeProduit +
      "]"
    );
  }
}
