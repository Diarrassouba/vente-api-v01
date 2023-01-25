package ci.kossovo.ventecoreapi.exceptions.produits;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundProduitException extends RuntimeException {

  public NotFoundProduitException() {
    super();
  }

  public NotFoundProduitException(String idProduit) {
    super(
      "Ce produit  dont l'identifiant est: [" + idProduit + "] n'existe pas"
    );
  }
}
