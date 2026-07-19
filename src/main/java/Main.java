import java.util.*;


public class Main {

    public static void runMenu(HashMap<String, ArrayList<Double>> income,
                               HashMap<String, ArrayList<Double>> expenses, Scanner scanner) {
        while (true) {

            int choice = -1;

            while (choice <= 0 || choice > 5) {
                try {
                    System.out.println("What would like you do? Please, press number: " +
                            "\n1. Add Expense" +
                            "\n2. Add Income" +
                            "\n3. Remove Expense" +
                            "\n4. Show summary" +
                            "\n5. Exit");

                    choice = scanner.nextInt();
                    scanner.nextLine();

                    if (choice > 5 || choice <= 0) {
                        System.out.println("Invalid option. Please, enter action (1-5): ");
                    }

                } catch (InputMismatchException e) {
                    System.out.println("Incorrect number of option! Please, try again.");
                    scanner.nextLine();
                }
            }

            switch (choice) {

                case 1: {
                    String category = getCategoryFromUser(scanner, "Enter a category of expense:");
                    double amount = getAmountFromUser(scanner, "Enter a amount of expense:  ");
                    addExpenses(expenses, category, amount);
                    break;
                }

                case 2: {
                    String category = getCategoryFromUser(scanner, "Enter a income category: ");
                    double amount = getAmountFromUser(scanner, "Enter a amount of income:  ");
                    addIncome(income, category, amount);
                    break;
                }

                case 3: {

                    String category = getCategoryFromUser(scanner, "Enter a expense category: ");
                    if (!expenses.containsKey(category)) {
                        System.out.println("Category does not exist.");
                        break;
                    }

                    printExpenses(expenses, category);

                    System.out.println("Which expense do you want to delete: ");
                    int index = scanner.nextInt();

                    removeExpenses(expenses, category, index);
                    break;
                }

                case 4: {
                    System.out.println("Your summary: ");
                    printSummary(income, expenses);
                    break;
                }

                case 5:
                    System.out.println("Ending of program");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static String getCategoryFromUser(Scanner scanner, String message) {

        while (true) {
            System.out.println(message);

            String category = scanner.next();

            if (!isValidCategory(category)) {
                System.out.println("Invalid category name.");
            } else {
                return category.toUpperCase();
            }
        }
    }

    public static boolean isValidCategory(String category) {
        return category.matches("[a-zA-Z .]+");
    }

    public static double getAmountFromUser(Scanner scanner, String message) {

        double amount = 0d;

        System.out.println(message);
        while (amount == 0) {
            try {
                amount = scanner.nextDouble();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Wrong type! Please enter a number!");
                scanner.nextLine();
            }
        }
        return amount;
    }

    public static void removeExpenses(HashMap<String, ArrayList<Double>> expenses,
                                      String category, int index) {
        expenses.get(category).remove(index - 1);
    }

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

    public static void printExpenses(HashMap<String, ArrayList<Double>> expenses, String category) {
        System.out.println(category + ":");
        for (int i = 0; i < expenses.get(category).size(); i++) {
            System.out.println((i + 1) + ". " + expenses.get(category).get(i));
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

        runMenu(income, expenses, scanner);

    }
}
