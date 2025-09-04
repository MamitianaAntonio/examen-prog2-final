package antonio.student;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

public class Statistique {
    public List<Frais> getLateFees(List<Frais> frais, Instant t) {
        return frais.stream()
                .filter(f -> f.isLate(t))
                .collect(Collectors.toList());
    }
    public double getTotalMissingFees(List<Frais> frais, Instant t) {
        return getLateFees(frais, t).stream()
                .mapToDouble(Frais::getMontant)
                .sum();
    }
    public double getTotalPaidByStudent(Etudiant etudiant, List<Frais> frais, Instant t) {
        return frais.stream()
                .filter(f -> f.getEtudiant().equals(etudiant) && f.isPaidBefore(t))
                .mapToDouble(f -> f.getPayement().getMontant())
                .sum();
    }
}
