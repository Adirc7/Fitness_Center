package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {

    @Test
    void toString_includesAllFields() {
        LocalDateTime dt = LocalDateTime.of(2026, 2, 2, 12, 0);
        Payment p = new Payment(1, 42, "PLAN1", "CLASS1", 99.5, dt, "desc", "CASH");
        String s = p.toString();

        assertTrue(s.contains("1"));
        assertTrue(s.contains("42"));
        assertTrue(s.contains("PLAN1"));
        assertTrue(s.contains("CLASS1"));
        assertTrue(s.contains("99.5"));
        assertTrue(s.contains(dt.toString()));
        assertTrue(s.contains("desc"));
        assertTrue(s.contains("CASH"));
    }
}
