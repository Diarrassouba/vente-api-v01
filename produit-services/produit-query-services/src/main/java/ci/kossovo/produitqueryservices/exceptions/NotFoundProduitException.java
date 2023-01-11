package ci.kossovo.produitqueryservices.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundProduitException extends RuntimeException {

  public NotFoundProduitException() {
    super();
  }

  public NotFoundProduitException(String message) {
    super(message);
  }
}
