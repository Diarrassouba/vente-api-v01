package ci.kossovo.exceptions;

public class SoldeInsuffisant extends RuntimeException {

    public SoldeInsuffisant(String cardNumber) {
        super("Solde insuffisant pour la carte de numero: "+cardNumber);
    }

}
