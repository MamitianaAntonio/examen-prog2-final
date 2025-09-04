package antonio.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
public class Frais {
    private int id;
    private String label;
    private double montant;
    private LocalDate deadline;
    private Etudiant etudiant;
}
