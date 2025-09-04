package antonio.student;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.Instant;

@AllArgsConstructor
@Getter
public class Frais {
    private int id;
    private String label;
    private double montant;
    private Instant deadline;
    private Etudiant etudiant;
    private Payement payement;
    private Status status;

    public enum Status {
        IN_PROGRESS,
        PAID,
        LATE,
        OVERPAID
    }

    public void statusFrais() {
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

    public boolean isLate(Instant t) {
        return (payement == null || payement.getDateHeurePayement().isAfter(deadline)) && t.isAfter(deadline);
    }

    public boolean isPaidBefore(Instant t) {
        return payement != null && payement.getDateHeurePayement().isBefore(t);
    }
}