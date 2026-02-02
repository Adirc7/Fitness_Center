package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.servlet;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.dao.TrainerDAO;
import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class UpdateTrainerServletTest {
    private Path tempFile;

    @AfterEach
    void cleanup() throws Exception {
        if (tempFile != null && Files.exists(tempFile)) Files.deleteIfExists(tempFile);
    }

    @Test
    void updateTrainer_exists_updatesAndForwards() throws Exception {
        tempFile = Files.createTempFile("trainer-update", ".txt");
        // prepopulate file
        String content = "T1,John,j@e.com,123,Spec,pass\n";
        Files.write(tempFile, content.getBytes());

        UpdateTrainerServlet servlet = new UpdateTrainerServlet() {
            @Override
            protected TrainerDAO createTrainerDAO() {
                return new TrainerDAO(tempFile.toString());
            }
        };

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("trainerId")).thenReturn("T1");
        when(req.getParameter("name")).thenReturn("John Updated");
        when(req.getParameter("email")).thenReturn("johnu@e.com");
        when(req.getParameter("contactNumber")).thenReturn("999");
        when(req.getParameter("specialty")).thenReturn("SpecU");
        when(req.getParameter("password")).thenReturn("newpass");
        when(req.getRequestDispatcher("loginTrainer.jsp")).thenReturn(rd);

        servlet.doPost(req, resp);

        verify(rd, times(1)).forward(req, resp);

        TrainerDAO dao = new TrainerDAO(tempFile.toString());
        List<Trainer> trainers = dao.getAllTrainers();
        assertEquals(1, trainers.size());
        assertEquals("John Updated", trainers.get(0).getName());
        assertEquals("johnu@e.com", trainers.get(0).getEmail());
    }
}
