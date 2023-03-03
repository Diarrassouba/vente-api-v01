package ci.kossovo.ventecoreapi.exceptions.order;

public class NoTfoundProduitException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public NoTfoundProduitException(String codeProduit) {
    super(
      "Impossible de trouver le produit d'identifiant de [" + codeProduit + "]"
    );
  }
}
