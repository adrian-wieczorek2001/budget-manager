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
                            "\n4. Remove Income" +
                            "\n5. Show summary" +
                            "\n6. Exit");

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

                    printCategories(expenses);

                    String category = getCategoryFromUser(scanner, "Enter a expense category: ");
                    if (!expenses.containsKey(category)) {
                        System.out.println("Category does not exist.");
                        break;
                    }

                    printEntry(expenses, category);

                    int index = -1;

                    while (true) {
                        try {
                            System.out.println("Which expense do you want to delete: ");
                            index = scanner.nextInt();
                            if (index < 1 || index > expenses.get(category).size()) {
                                System.out.println("Index out of range. Please, try again.");
                            } else { break; }
                        } catch (InputMismatchException e) {
                            System.out.println("Please, enter a correct index");
                            scanner.nextLine();
                        }
                    }
                    removeEntry(expenses, category, index);
                    break;
                }

                case 4: {

                    printCategories(income);

                    String category = getCategoryFromUser(scanner, "Enter a income category: ");
                    if (!income.containsKey(category)) {
                        System.out.println("Category does not exist.");
                        break;
                    }

                    printEntry(income, category);

                    int index = -1;

                    while (true) {
                        try {
                            System.out.println("Which income do you want to delete: ");
                            index = scanner.nextInt();
                            if (index < 1 || index > income.get(category).size()) {
                                System.out.println("Index out of range. Please, try again.");
                            } else { break; }
                        } catch (InputMismatchException e) {
                            System.out.println("Please, enter a correct index");
                            scanner.nextLine();
                        }
                    }

                    removeEntry(income, category, index);
                }

                case 5: {

                    System.out.println("All categories: expenses and incomes: ");
                    printAllCategories(income, expenses);
                    System.out.println("Your summary: ");
                    printSummary(income, expenses);
                    break;
                }

                case 6:
                    System.out.println("Ending of program");
                    scanner.close();
                    System.exit(0);

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void printAllCategories(HashMap<String, ArrayList<Double>> map1,
                                              HashMap<String, ArrayList<Double>> map2) {
        HashSet<String> allCategories = new HashSet<>();
        allCategories.addAll(map1.keySet());
        allCategories.addAll(map2.keySet());
        for (String category : allCategories) {
            System.out.println(category);
        }
    }

    public static String getCategoryFromUser(Scanner scanner, String message) {

        while (true) {
            System.out.println(message);

            String category = scanner.next();

            if (!isValidCategory(category)) {
                System.out.println("Invalid category name.");
            } else {
                return category.toUpperCase().strip();
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

    public static void printCategories(HashMap<String, ArrayList<Double>> categories) {

        int i = 1;

        for (String key : categories.keySet()) {
            System.out.println(i++ + " " + key);
        }
    }

    public static void removeEntry(HashMap<String, ArrayList<Double>> entry,
                                      String category, int index) {

        entry.get(category).remove(index - 1);
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

    public static void printEntry(HashMap<String, ArrayList<Double>> entry, String category) {
        System.out.println(category + ":");
        for (int i = 0; i < entry.get(category).size(); i++) {
            System.out.println((i + 1) + ". " + entry.get(category).get(i));
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
