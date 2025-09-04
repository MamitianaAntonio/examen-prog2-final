package antonio.student;

import lombok.AllArgsConstructor;

import java.time.LocalDate;

public class PayementEspece extends PayementMobile {
    public PayementEspece(int id, double montant, LocalDate dateHeurePayement) {
        super(id, montant, dateHeurePayement);
    }
}
