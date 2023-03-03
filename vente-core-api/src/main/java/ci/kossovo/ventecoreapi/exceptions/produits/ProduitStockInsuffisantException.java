package ci.kossovo.ventecoreapi.exceptions.produits;

public class ProduitStockInsuffisantException extends RuntimeException {

  public ProduitStockInsuffisantException() {
    super();
  }

  public ProduitStockInsuffisantException(String code) {
    super(
      "Le stock du produit  dont le code est: [" + code + "] est insuffisant."
    );
  }
}
