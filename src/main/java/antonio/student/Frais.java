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
    public Status status;

    public enum Status {
        IN_PROGRESS,
        PAID,
        LATE,
        OVERPAID
    }

    public void statusFrais(Payement payement) {
        if (payement == null) {
            this.status = Status.IN_PROGRESS;
            return;
        }
        if (payement.getMontant() > this.montant) {
            this.status = Status.OVERPAID;
        } else if (payement.getMontant() == this.montant) {
            if (payement.getDateHeurePayement().isAfter(this.deadline)) {
                this.status = Status.LATE;
            } else {
                this.status = Status.PAID;
            }
        } else {
            this.status = Status.IN_PROGRESS;
        }
    }
}
