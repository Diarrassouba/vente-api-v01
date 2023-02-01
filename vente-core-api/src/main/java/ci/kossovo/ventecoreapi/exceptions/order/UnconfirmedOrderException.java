package ci.kossovo.ventecoreapi.exceptions.order;

public class UnconfirmedOrderException extends IllegalStateException {

  public UnconfirmedOrderException() {
    super(
     "Impossible d'expédier une commande qui n'a pas encore été confirmée."
    );
  }
}
