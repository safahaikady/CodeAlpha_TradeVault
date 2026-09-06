import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        TradingPlatform platform =
                new TradingPlatform();

        System.out.println(
                "========================================================");

        System.out.println(
                "                      TRADEVAULT");

        System.out.println(
                "              STOCK TRADING PLATFORM");

        System.out.println(
                "========================================================");

        if (platform.getUser()
                .getName()
                .equals("Guest")) {

            System.out.print(
                    "Enter investor name: ");

            String name =
                    sc.nextLine().trim();

            if (!name.isEmpty()) {

                platform.getUser()
                        .setName(name);

                platform.saveData();
            }
        }

        int choice;

        do {

            System.out.println(
                    "\n========================================================");

            System.out.println(
                    "                      TRADEVAULT");

            System.out.println(
                    "========================================================");

            System.out.println(
                    "Investor : " +
                            platform.getUser().getName());

            System.out.printf(
                    "Cash     : Rs.%.2f%n",
                    platform.getUser().getBalance());

            System.out.println(
                    "--------------------------------------------------------");

            System.out.println(
                    "1. Market Dashboard");

            System.out.println(
                    "2. Search Stock");

            System.out.println(
                    "3. Buy Stock");

            System.out.println(
                    "4. Sell Stock");

            System.out.println(
                    "5. My Portfolio");

            System.out.println(
                    "6. Portfolio Performance");

            System.out.println(
                    "7. Transaction History");

            System.out.println(
                    "8. Account Summary");

            System.out.println(
                    "9. Update Market Price");

            System.out.println(
                    "10. Add Money");

            System.out.println(
                    "11. Exit");

            System.out.println(
                    "--------------------------------------------------------");

            choice =
                    readInt(
                            "Enter your choice: ");

            switch (choice) {

                case 1:

                    platform.displayMarket();
                    break;

                case 2:

                    System.out.print(
                            "Enter stock symbol: ");

                    String searchSymbol =
                            sc.nextLine().trim();

                    platform.searchStock(
                            searchSymbol);

                    break;

                case 3:

                    System.out.print(
                            "Enter stock symbol: ");

                    String buySymbol =
                            sc.nextLine().trim();

                    int buyQuantity =
                            readInt(
                                    "Enter quantity: ");

                    platform.buyStock(
                            buySymbol,
                            buyQuantity);

                    break;

                case 4:

                    System.out.print(
                            "Enter stock symbol: ");

                    String sellSymbol =
                            sc.nextLine().trim();

                    int sellQuantity =
                            readInt(
                                    "Enter quantity: ");

                    platform.sellStock(
                            sellSymbol,
                            sellQuantity);

                    break;

                case 5:

                    platform.getPortfolio()
                            .displayPortfolio(
                                    platform.getStocks());

                    break;

                case 6:

                    platform.displayPerformance();
                    break;

                case 7:

                    platform.displayTransactions();
                    break;

                case 8:

                    platform.displayAccountSummary();
                    break;

                case 9:

                    System.out.print(
                            "Enter stock symbol: ");

                    String symbol =
                            sc.nextLine().trim();

                    double price =
                            readDouble(
                                    "Enter new market price: ");

                    platform.updateMarketPrice(
                            symbol,
                            price);

                    break;

                case 10:

                    double amount =
                            readDouble(
                                    "Enter amount to add: ");

                    platform.addMoney(amount);

                    break;

                case 11:

                    platform.saveData();

                    System.out.println(
                            "\nThank you for using TradeVault.");

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Please try again.");
            }

        } while (choice != 11);

        sc.close();
    }

    static int readInt(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Integer.parseInt(
                        sc.nextLine().trim());

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid integer.");
            }
        }
    }

    static double readDouble(String message) {

        while (true) {

            try {

                System.out.print(message);

                return Double.parseDouble(
                        sc.nextLine().trim());

            } catch (Exception e) {

                System.out.println(
                        "Please enter a valid number.");
            }
        }
    }
}