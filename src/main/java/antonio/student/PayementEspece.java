package antonio.student;

import java.time.Instant;

public class PayementEspece extends PayementMobile {
    public PayementEspece(int id, double montant, Instant dateHeurePayement) {
        super(id, montant, dateHeurePayement);
    }
}
