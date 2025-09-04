package antonio.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class Payement {
    private int id;
    private double montant;
    private Instant dateHeurePayement;
}
