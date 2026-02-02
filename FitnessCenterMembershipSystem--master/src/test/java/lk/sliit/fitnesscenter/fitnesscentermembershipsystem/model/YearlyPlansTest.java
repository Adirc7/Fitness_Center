package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class YearlyPlansTest {

    @Test
    void constructorAndGetters_work() {
        yearlyPlans y = new yearlyPlans("Y1", "Gold", "1000", "addons", "900", "100", "1000");

        assertEquals("Y1", y.getPlanId());
        assertEquals("Gold", y.getPlanName());
        assertEquals("1000", y.getPrice());
        assertEquals("addons", y.getAddons());
        assertEquals("900", y.getSubTotal());
        assertEquals("100", y.getDiscont());
        assertEquals("1000", y.getFinalPrice());
    }
}
