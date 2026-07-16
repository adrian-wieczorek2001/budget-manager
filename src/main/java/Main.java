import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class Main {

    public void addExpenses(HashMap<String, ArrayList<Double>> expenses,
                            String category, Double cost) {

        if (expenses.containsKey(category)) {
            expenses.get(category).add(cost);
        } else {
            ArrayList<Double> newCategory = new ArrayList<>();
            expenses.put(category, newCategory);
            newCategory.add(cost);
        }
    }

    public void addIncome(HashMap<String, ArrayList<Double>> income,
                          String category, Double proceeds) {

        if (income.containsKey(category)) {
            income.get(category).add(proceeds);
        } else {
            ArrayList<Double> newCategory = new ArrayList<>();
            income.put(category, newCategory);
            newCategory.add(proceeds);
        }
    }

    public void printSummary(HashMap<String, ArrayList<Double>> income,
                             HashMap<String, ArrayList<Double>> expenses) {

        double totalIncome = 0;
        double totalExpenses = 0;

        for (Map.Entry<String, ArrayList<Double>> entry : income.entrySet()) {

            double categorySum = 0;

            for (int i = 0; i < entry.getValue().size(); i++) {
                totalIncome += entry.getValue().get(i);
                categorySum += entry.getValue().get(i);
            }

            System.out.println(entry.getKey() + " whole income " + categorySum);

        }

        for (Map.Entry<String, ArrayList<Double>> entry : expenses.entrySet()) {

            double categorySum = 0;

            for (int i = 0; i < entry.getValue().size(); i++) {
                totalExpenses += entry.getValue().get(i);
                categorySum += entry.getValue().get(i);
            }

            System.out.println(entry.getKey() + " whole expenses " + categorySum);

        }

        double total = totalIncome - totalExpenses;

        System.out.println("Total expenses: " + totalExpenses
                + "\nTotal income: " + totalIncome
                + "\nSum: " + total);

    }

    public static void main(String[] args) {

        HashMap<String, ArrayList<Double>> income = new HashMap<>();
        HashMap<String, ArrayList<Double>> expenses = new HashMap<>();


    }
}
