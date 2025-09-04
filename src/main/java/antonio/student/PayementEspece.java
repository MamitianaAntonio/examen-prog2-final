package antonio.student;

import lombok.Getter;

import java.time.Instant;

@Getter
public class PayementEspece extends Payement {
    private String nomDeCompte;

    public PayementEspece(int id, double montant, Instant dateHeurePayement,  String nomDeCompte) {
        super(id, montant, dateHeurePayement);
        this.nomDeCompte = nomDeCompte;
    }
}
