package ci.kossovo.ventecoreapi.exceptions.order;

public class NoTfindProduitException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NoTfindProduitException(String codeProduit) {
    super(
      "Impossible de trouver le produit d'identifiant de [" + codeProduit + "]"
    );
  }
}
