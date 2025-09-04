import antonio.student.Etudiant;
import antonio.student.Frais;
import antonio.student.Payement;
import antonio.student.Statistique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Arrays;
import java.util.List;


public class StatistiqueTest {
    private Etudiant e1;
    private Etudiant e2;
    private Frais f1, f2, f3, f4;
    private Payement p1, p2, p3;

    private Statistique stats;

    @BeforeEach
    void setup() {
        e1 = new Etudiant(1, "Antonio", "Mamitiana", Instant.parse("2025-01-01T00:00:00Z"), null);
        e2 = new Etudiant(2, "Mamitiana", "RATOLOJANAHARY", Instant.parse("2025-01-01T00:00:00Z"), null);

        p1 = new Payement(1, 100.0, Instant.parse("2025-09-02T00:00:00Z"));
        p2 = new Payement(2, 100.0, Instant.parse("2025-09-12T00:00:00Z"));
        p3 = new Payement(3, 150.0, Instant.parse("2025-09-05T00:00:00Z"));

        f1 = new Frais(1, "Inscription", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, p1, null);
        f2 = new Frais(2, "Bibliothèque", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, p2, null);
        f3 = new Frais(3, "Cantine", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, null, null);
        f4 = new Frais(4, "Laboratoire", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e2, p3, null);

        stats = new Statistique();
    }

    @Test
    void testGetLateFees() {
        Instant now = Instant.parse("2025-09-15T00:00:00Z");
        List<Frais> lateFees = stats.getLateFees(Arrays.asList(f1, f2, f3, f4), now);

        Assertions.assertEquals(2, lateFees.size());
        Assertions.assertEquals(true, lateFees.contains(f2));
        Assertions.assertEquals(true, lateFees.contains(f3));
    }

    @Test
    void testGetTotalMissingFees() {
        Instant now = Instant.parse("2025-09-15T00:00:00Z");
        double totalMissing = stats.getTotalMissingFees(Arrays.asList(f1, f2, f3, f4), now);

        Assertions.assertEquals(200.0, totalMissing);
    }

    @Test
    void testGetTotalPaidByStudent() {
        Instant now = Instant.parse("2025-09-15T00:00:00Z");
        double totalPaidE1 = stats.getTotalPaidByStudent(e1, Arrays.asList(f1, f2, f3, f4), now);
        double totalPaidE2 = stats.getTotalPaidByStudent(e2, Arrays.asList(f1, f2, f3, f4), now);

        Assertions.assertEquals(200.0, totalPaidE1);

        Assertions.assertEquals(150.0, totalPaidE2);
    }

    @Test
    void testGetLateFeesBeforeDeadline() {
        Instant now = Instant.parse("2025-09-05T00:00:00Z");
        List<Frais> lateFees = stats.getLateFees(Arrays.asList(f1, f2, f3, f4), now);

        Assertions.assertEquals(0, lateFees.size());
    }

    @Test
    void testGetTotalPaidByStudentBeforePayment() {
        Instant now = Instant.parse("2025-09-01T00:00:00Z");
        double totalPaidE1 = stats.getTotalPaidByStudent(e1, Arrays.asList(f1, f2, f3, f4), now);

        Assertions.assertEquals(0.0, totalPaidE1);
    }
}
