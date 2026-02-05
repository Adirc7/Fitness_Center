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

class AddTrainerServletTest {
    private Path tempFile;

    @AfterEach
    void cleanup() throws Exception {
        if (tempFile != null && Files.exists(tempFile)) Files.deleteIfExists(tempFile);
    }

    @Test
    void addFullTimeTrainer_forwardsAndPersists() throws Exception {
        tempFile = Files.createTempFile("trainer-servlet", ".txt");

        AddTrainerServlet servlet = new AddTrainerServlet() {
            @Override
            protected TrainerDAO createTrainerDAO() {
                return new TrainerDAO(tempFile.toString());
            }
        };

        HttpServletRequest req = mock(HttpServletRequest.class);
        HttpServletResponse resp = mock(HttpServletResponse.class);
        RequestDispatcher rd = mock(RequestDispatcher.class);

        when(req.getParameter("trainerId")).thenReturn("TF1");
        when(req.getParameter("name")).thenReturn("Alice");
        when(req.getParameter("email")).thenReturn("a@example.com");
        when(req.getParameter("contactNumber")).thenReturn("123");
        when(req.getParameter("specialty")).thenReturn("Yoga");
        when(req.getParameter("password")).thenReturn("p");
        when(req.getParameter("trainerType")).thenReturn("FULLTIME");
        when(req.getRequestDispatcher("addTrainer.jsp")).thenReturn(rd);

        servlet.doPost(req, resp);

        verify(rd, times(1)).forward(req, resp);

        TrainerDAO dao = new TrainerDAO(tempFile.toString());
        List<Trainer> trainers = dao.getAllTrainers();
        assertEquals(1, trainers.size());
        assertEquals("TF1", trainers.get(0).getTrainerId());
        assertEquals("Alice", trainers.get(0).getName());
    }
}
