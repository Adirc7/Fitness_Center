package lk.sliit.fitnesscenter.fitnesscentermembershipsystem.dao;

import lk.sliit.fitnesscenter.fitnesscentermembershipsystem.model.yearlyPlans;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class yearlyPlanDAO {
        private List<yearlyPlans> yearlyPlanList;
        private static final String DEFAULT_DIRECTORY = "C:\\Users\\DELL\\Desktop\\oopProjText";
        private static final String DEFAULT_DATA_FILE = DEFAULT_DIRECTORY + "\\yearly-plan.txt";
        private final String dataFile;

        public yearlyPlanDAO() {
            this(DEFAULT_DATA_FILE);
        }

        // Test-friendly constructor
        public yearlyPlanDAO(String dataFile) {
            this.dataFile = dataFile;
            yearlyPlanList = new ArrayList<>();
            ensureDirectoryExists();
            loadYearlyPlans();
        }

        private void ensureDirectoryExists() {
            File dir = new File(dataFile).getParentFile();
            if (dir != null && !dir.exists()) {
                dir.mkdirs();
            }
        }

        public void addYearlyplan(yearlyPlans yearlyPlan) {
            yearlyPlanList.add(yearlyPlan);
            saveyearlyPlanFile(yearlyPlan);
        }

        private void saveyearlyPlanFile(yearlyPlans yearlyPlan) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile, true))) {
                writer.write(yearlyPlan.getPlanId() + "," + yearlyPlan.getPlanName() + "," +
                        yearlyPlan.getPrice() + "," +yearlyPlan.getAddons()+","+yearlyPlan.getSubTotal()+","+yearlyPlan.getDiscont()+","+yearlyPlan.getFinalPrice());
                writer.newLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private void  loadYearlyPlans() {
            File file = new File(dataFile);
            if (!file.exists()) return;

            try(BufferedReader reader = new BufferedReader(new FileReader(dataFile))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] data = line.split(",");
                    if(data.length == 7) {
                        yearlyPlanList.add(new yearlyPlans(data[0], data[1], data[2], data[3],data[4],data[5],data[6]));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public List<yearlyPlans> getMonthlyPlans() {
            return yearlyPlanList;
        }

        public void updateMonthlyplan(String originalID, yearlyPlans UpdatedMonthlyPlan) {
            for (int i = 0; i < yearlyPlanList.size(); i++) {
                if(yearlyPlanList.get(i).getPlanId().equals(originalID)) {
                    yearlyPlanList.set(i, UpdatedMonthlyPlan);
                    saveAllyearlyPlans();

                }
            }
        }

        private void saveAllyearlyPlans() {
            try(BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile))) {
                for (yearlyPlans yearlyPlan : yearlyPlanList) {
                    writer.write(yearlyPlan.getPlanId() + "," + yearlyPlan.getPlanName() + "," +
                            yearlyPlan.getPrice() + "," +yearlyPlan.getAddons()+","+yearlyPlan.getSubTotal()+","+yearlyPlan.getDiscont()+","+yearlyPlan.getFinalPrice());
                    writer.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
}
