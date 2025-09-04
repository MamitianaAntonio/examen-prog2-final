package antonio.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Payement {
    private int id;
    private double montant;
    private LocalDate dateHeurePayement;
}
