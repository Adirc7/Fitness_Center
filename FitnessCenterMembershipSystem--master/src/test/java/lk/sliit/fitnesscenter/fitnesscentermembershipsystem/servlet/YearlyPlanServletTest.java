package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.servlet;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class YearlyPlanServletTest {
    private Path tempDir;

    @AfterEach
    void cleanup() throws Exception {
        if (tempDir != null && Files.exists(tempDir)) {
            // delete files inside
            Files.list(tempDir).forEach(p -> p.toFile().delete());
            Files.deleteIfExists(tempDir);
        }
    }

    @Test
    void addDeleteUpdate_flow() throws Exception {
        tempDir = Files.createTempDirectory("yearly-servlet");
        YearlyPlanServlet.setDataDirectory(tempDir.toString());

        YearlyPlanServlet servlet = new YearlyPlanServlet();

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);

        // Add
        when(req.getParameter("action")).thenReturn("addYearlyplan");
        when(req.getParameter("planId")).thenReturn("Y1");
        when(req.getParameter("planName")).thenReturn("Gold");
        when(req.getParameter("price")).thenReturn("1000");
        when(req.getParameter("addons")).thenReturn("addons");
        when(req.getParameter("subTotal")).thenReturn("900");
        when(req.getParameter("discount")).thenReturn("100");
        when(req.getParameter("finalPrice")).thenReturn("1000");

        servlet.doPost(req, resp);
        verify(resp, times(1)).sendRedirect("Yearly-plan-admin.jsp");

        File dataFile = new File(tempDir.toString(), "yearly-plan.txt");
        assertTrue(dataFile.exists());
        List<String> lines = Files.readAllLines(dataFile.toPath());
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).startsWith("Y1,"));

        // Add second line for delete/update tests
        Files.write(dataFile.toPath(), "Y2,A,200,addons,180,20,200\n".getBytes(), java.nio.file.StandardOpenOption.APPEND);
        assertEquals(2, Files.readAllLines(dataFile.toPath()).size());

        // Delete Y1
        when(req.getParameter("action")).thenReturn("deleteYearlyplan");
        when(req.getParameter("planId")).thenReturn("Y1");
        servlet.doPost(req, resp);
        List<String> afterDelete = Files.readAllLines(dataFile.toPath());
        assertEquals(1, afterDelete.size());
        assertTrue(afterDelete.get(0).startsWith("Y2,"));

        // Update Y2 -> change name
        when(req.getParameter("action")).thenReturn("updateYearlyplan");
        when(req.getParameter("originalID")).thenReturn("Y2");
        when(req.getParameter("planId")).thenReturn("Y2");
        when(req.getParameter("planName")).thenReturn("Alpha");
        when(req.getParameter("price")).thenReturn("300");
        when(req.getParameter("addons")).thenReturn("addons");
        when(req.getParameter("subTotal")).thenReturn("280");
        when(req.getParameter("discount")).thenReturn("20");
        when(req.getParameter("finalPrice")).thenReturn("300");
        servlet.doPost(req, resp);
        List<String> afterUpdate = Files.readAllLines(dataFile.toPath());
        assertEquals(1, afterUpdate.size());
        assertTrue(afterUpdate.get(0).contains("Alpha"));
    }
}