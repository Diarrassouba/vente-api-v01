package ci.kossovo.ventecoreapi.exceptions.customers;

public class NotFoundOwnerException extends RuntimeException {

  public NotFoundOwnerException(String idOwner) {
    super("Ce proprietaire n'existe pas dont l'identifiant est: [" + idOwner + "]");
  }
}
