package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.queue;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.monthlyPlans;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PlanQueue {
    private LinkedList<monthlyPlans> queue;

    public PlanQueue() {
        queue = new LinkedList<>();
    }

    public void enqueue(monthlyPlans plan) {
        queue.addLast(plan);
    }

    public monthlyPlans dequeue() {
        if (isEmpty()) {
            return null;
        }
        return queue.removeFirst();
    }

    public monthlyPlans peek() {
        if (isEmpty()) {
            return null;
        }
        return queue.getFirst();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }

    public int size() {
        return queue.size();
    }

    public void displayAll() {
        for (monthlyPlans plan : queue) {
            plan.display();
        }
    }

    public List<monthlyPlans> getAllPlansInQueue() {
        return new ArrayList<>(queue);
    }
}
