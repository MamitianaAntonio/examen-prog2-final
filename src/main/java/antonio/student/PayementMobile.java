package antonio.student;

import lombok.Getter;

import java.time.Instant;

@Getter
public class PayementMobile extends Payement{
    private String nomDeCompte;
    private int numeroCompte;

    public PayementMobile(int id, double montant, Instant dateHeurePayement,  String nomDeCompte, int numeroCompte) {
        super(id, montant, dateHeurePayement);
        this.nomDeCompte = nomDeCompte;
        this.numeroCompte = numeroCompte;
    }
}
