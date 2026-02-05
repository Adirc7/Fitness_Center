package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.dao;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.Payment;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PaymentDAOTest {

    private Path tempFile;

    @AfterEach
    void cleanup() throws Exception {
        if (tempFile != null && Files.exists(tempFile)) Files.deleteIfExists(tempFile);
    }

    @Test
    void createAndReadPayment_usingTempFile() throws Exception {
        tempFile = Files.createTempFile("payment-test", ".txt");
        PaymentDAO dao = new PaymentDAO(tempFile.toString());
        dao.clearQueueForTests();

        LocalDateTime dt = LocalDateTime.of(2026, 2, 2, 12, 0);
        Payment p = new Payment(1, 42, "PLAN1", "CLASS1", 99.5, dt, "desc", "CASH");

        assertTrue(dao.createPayment(p));

        List<Payment> loaded = dao.getAllPayments();
        assertFalse(loaded.isEmpty());
        Payment r = loaded.get(0);
        assertEquals(1, r.getPaymentId());
        assertEquals(42, r.getMemberId());
        assertEquals("PLAN1", r.getPlanId());
        assertEquals("CLASS1", r.getClassId());
        assertEquals(99.5, r.getAmount());
        assertEquals(dt, r.getPaymentDate());
        assertEquals("desc", r.getDescription());
        assertEquals("CASH", r.getPaymentMethod());
    }
}