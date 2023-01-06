package ci.kossovo.ventecoreapi.exceptions.card;

public class NotFoundCardException extends RuntimeException {

  public NotFoundCardException(String idCard) {
    super("Cette carte de credit n'existe pas dont l'identifiant est: [" + idCard + "]");
  }
}
