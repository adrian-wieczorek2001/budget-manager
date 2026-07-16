import java.util.*;


public class Main {

    public static void addExpenses(HashMap<String, ArrayList<Double>> expenses,
                            String category, Double cost) {

        if (expenses.containsKey(category)) {
            expenses.get(category).add(cost);
        } else {
            ArrayList<Double> newCategory = new ArrayList<>();
            expenses.put(category, newCategory);
            newCategory.add(cost);
        }
    }

    public static void addIncome(HashMap<String, ArrayList<Double>> income,
                          String category, Double proceeds) {

        if (income.containsKey(category)) {
            income.get(category).add(proceeds);
        } else {
            ArrayList<Double> newCategory = new ArrayList<>();
            income.put(category, newCategory);
            newCategory.add(proceeds);
        }
    }

    public static void printSummary(HashMap<String, ArrayList<Double>> income,
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
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("What would like you do? Please, press number: " +
                    "\n1. Add Expenses" +
                    "\n2. Add Income" +
                    "\n3. Show summary" +
                    "\n4. Exit");

            int choice = scanner.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("Enter a category of expense: ");
                    String category = scanner.next().toLowerCase();
                    System.out.println("Enter a amount of expense:  ");
                    while (true) {
                        try {
                            double amount = scanner.nextDouble();
                            addExpenses(expenses, category, amount);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong type! Please enter a number!");
                            scanner.nextLine();
                        }
                    }
                    break;
                }

                case 2: {
                    System.out.println("Enter a category of income: ");
                    String category = scanner.next().toLowerCase();
                    System.out.println("Enter a amount of income: ");
                    while (true) {
                        try {
                            double amount = scanner.nextDouble();
                            addIncome(income, category, amount);
                            break;
                        } catch (InputMismatchException e) {
                            System.out.println("Wrong type! Please enter a number!");
                            scanner.nextLine();
                        }
                    }
                    break;
                }

                case 3: {
                    System.out.println("Your summary: ");
                    printSummary(income, expenses);
                    break;
                }

                default:
                    System.out.println("Ending of program");
                    System.exit(0);
            }

        }

    }
}
