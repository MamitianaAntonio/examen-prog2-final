package antonio.student;

import java.time.LocalDate;

public class PayementMobile extends Payement{
    public PayementMobile(int id, double montant, LocalDate dateHeurePayement) {
        super(id, montant, dateHeurePayement);
    }
}
