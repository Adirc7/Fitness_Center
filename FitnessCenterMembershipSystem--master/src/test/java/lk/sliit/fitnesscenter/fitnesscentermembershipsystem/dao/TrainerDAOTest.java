package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.dao;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.FullTimeTrainer;
import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.PartTimeTrainer;
import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.Trainer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TrainerDAOTest {
    private Path tempFile;

    @AfterEach
    void cleanup() throws Exception {
        if (tempFile != null && Files.exists(tempFile)) Files.deleteIfExists(tempFile);
    }

    @Test
    void loadTrainers_parsesFullAndPartTime() throws Exception {
        tempFile = Files.createTempFile("trainer-test", ".txt");
        String content = "T1,John,j@e.com,123,Spec,pass,FULLTIME,2500,40\n" +
                "T2,Jane,j2@e.com,456,Spec2,pass2,PARTTIME,25,20\n" +
                "T3,Bob,b@e.com,789,Spec3,pass3\n";
        Files.write(tempFile, content.getBytes());

        TrainerDAO dao = new TrainerDAO(tempFile.toString());
        List<Trainer> trainers = dao.getAllTrainers();

        assertEquals(3, trainers.size());
        assertTrue(trainers.get(0) instanceof FullTimeTrainer);
        assertTrue(trainers.get(1) instanceof PartTimeTrainer);
        assertEquals("T3", trainers.get(2).getTrainerId());
    }
}