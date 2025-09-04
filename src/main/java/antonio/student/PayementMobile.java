package antonio.student;

import java.time.Instant;

public class PayementMobile extends Payement{
    public PayementMobile(int id, double montant, Instant dateHeurePayement) {
        super(id, montant, dateHeurePayement);
    }
}
