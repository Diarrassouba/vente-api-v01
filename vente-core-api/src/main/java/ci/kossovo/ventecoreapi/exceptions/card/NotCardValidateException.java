package ci.kossovo.ventecoreapi.exceptions.card;

import java.time.LocalDate;

public class NotCardValidateException extends RuntimeException {

  public NotCardValidateException(LocalDate localDate) {
    super("Cette carte est invalide car la de validite est:  " + localDate);
  }
}
