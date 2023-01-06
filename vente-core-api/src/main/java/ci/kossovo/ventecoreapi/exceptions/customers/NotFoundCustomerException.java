package ci.kossovo.ventecoreapi.exceptions.customers;

import java.util.NoSuchElementException;

public class NotFoundCustomerException extends NoSuchElementException {

  public NotFoundCustomerException(String id) {
    super("Ce customer n'existe pas dont l'identifiant est: [" + id + "]");
  }
}
