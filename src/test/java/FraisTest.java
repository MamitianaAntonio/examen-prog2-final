import antonio.student.Etudiant;
import antonio.student.Frais;
import antonio.student.Payement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class FraisTest {

    private Etudiant e1;
    private Frais fraisInProgress;
    private Frais fraisPaidOnTime;
    private Frais fraisLate;
    private Frais fraisOverpaid;

    private Payement payOnTime;
    private Payement payLate;
    private Payement payOver;

    @BeforeEach
    void setup() {
        e1 = new Etudiant(1, "Antonio", "RATOLOJANAHARY", Instant.parse("2025-01-01T00:00:00Z"), null);
        payOnTime = new Payement(1, 100.0, Instant.parse("2025-09-05T00:00:00Z"));
        payLate   = new Payement(2, 100.0, Instant.parse("2025-09-12T00:00:00Z"));
        payOver   = new Payement(3, 150.0, Instant.parse("2025-09-05T00:00:00Z"));

        fraisInProgress = new Frais(1, "Inscription", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, null, null);
        fraisPaidOnTime = new Frais(2, "Reinscription", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, payOnTime, null);
        fraisLate       = new Frais(3, "Journee d'integration", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, payLate, null);
        fraisOverpaid   = new Frais(4, "club HEI", 100.0, Instant.parse("2025-09-10T00:00:00Z"), e1, payOver, null);
    }

    @Test
    void testStatusFrais() {
        fraisInProgress.statusFrais();
        assertEquals(Frais.Status.IN_PROGRESS, fraisInProgress.getStatus());

        fraisPaidOnTime.statusFrais();
        assertEquals(Frais.Status.PAID, fraisPaidOnTime.getStatus());

        fraisLate.statusFrais();
        assertEquals(Frais.Status.LATE, fraisLate.getStatus());

        fraisOverpaid.statusFrais();
        assertEquals(Frais.Status.OVERPAID, fraisOverpaid.getStatus());
    }

    @Test
    void testIsLate() {
        Instant afterDeadline = Instant.parse("2025-09-15T00:00:00Z");
        Instant beforeDeadline = Instant.parse("2025-09-05T00:00:00Z");

        assertTrue(fraisInProgress.isLate(afterDeadline));
        assertFalse(fraisInProgress.isLate(beforeDeadline));
        assertFalse(fraisPaidOnTime.isLate(afterDeadline));
        assertTrue(fraisLate.isLate(afterDeadline));
        assertFalse(fraisOverpaid.isLate(afterDeadline));
    }

    @Test
    void testIsPaidBefore() {
        Instant checkDate = Instant.parse("2025-09-15T00:00:00Z");

        assertFalse(fraisInProgress.isPaidBefore(checkDate));
        assertTrue(fraisPaidOnTime.isPaidBefore(checkDate));
        assertTrue(fraisLate.isPaidBefore(checkDate));
        assertTrue(fraisOverpaid.isPaidBefore(checkDate));

        Instant beforePayment = Instant.parse("2025-09-01T00:00:00Z");
        assertFalse(fraisPaidOnTime.isPaidBefore(beforePayment));
    }
}
