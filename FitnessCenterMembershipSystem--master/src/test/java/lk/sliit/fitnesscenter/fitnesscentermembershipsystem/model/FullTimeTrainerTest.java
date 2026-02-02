package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FullTimeTrainerTest {

    @Test
    void salaryGetterAndSetter() {
        FullTimeTrainer ft = new FullTimeTrainer("T1", "Alice", "a@x.com", "123", "Yoga", "p", 2500.0);
        assertEquals(2500.0, ft.getSalary());

        ft.setSalary(3000.0);
        assertEquals(3000.0, ft.getSalary());
    }
}
