package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.dao;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.yearlyPlans;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class YearlyPlanDAOTest {
    private Path tempFile;

    @AfterEach
    void cleanup() throws Exception {
        if (tempFile != null && Files.exists(tempFile)) Files.deleteIfExists(tempFile);
    }

    @Test
    void addAndLoadYearlyPlan() throws Exception {
        tempFile = Files.createTempFile("yearly-plan-test", ".txt");
        yearlyPlanDAO dao = new yearlyPlanDAO(tempFile.toString());

        yearlyPlans p = new yearlyPlans("Y1", "Gold", "1000", "addons", "900", "100", "1000");
        dao.addYearlyplan(p);

        List<yearlyPlans> list = dao.getMonthlyPlans();
        assertEquals(1, list.size());
        yearlyPlans r = list.get(0);
        assertEquals("Y1", r.getPlanId());
        assertEquals("addons", r.getAddons());
        assertEquals("900", r.getSubTotal());
    }
}