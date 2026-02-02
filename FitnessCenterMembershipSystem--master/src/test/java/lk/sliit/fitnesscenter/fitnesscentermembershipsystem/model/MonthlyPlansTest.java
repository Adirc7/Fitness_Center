package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MonthlyPlansTest {

    @Test
    void compareTo_sortsByPlanId() {
        monthlyPlans a = new monthlyPlans("A1", "Alpha", "10", "notes");
        monthlyPlans b = new monthlyPlans("B1", "Beta", "20", "notes");

        assertTrue(a.compareTo(b) < 0);
        assertTrue(b.compareTo(a) > 0);
        assertEquals(0, a.compareTo(new monthlyPlans("A1", "X", "5", "n")));
    }
}
