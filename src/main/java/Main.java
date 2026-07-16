import java.util.ArrayList;
import java.util.HashMap;


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

    public static void main(String[] args) {

        HashMap<String, ArrayList<Double>> income = new HashMap<>();
        HashMap<String, ArrayList<Double>> expenses = new HashMap<>();


    }
}
