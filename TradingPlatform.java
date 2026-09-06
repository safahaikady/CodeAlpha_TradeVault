import java.io.*;
import java.util.ArrayList;

public class TradingPlatform {

    private ArrayList<Stock> stocks;
    private ArrayList<Transaction> transactions;

    private User user;
    private Portfolio portfolio;

    private final String FILE_NAME =
            "trading_data.txt";

    public TradingPlatform() {

        stocks = new ArrayList<>();
        transactions = new ArrayList<>();
        portfolio = new Portfolio();

        loadData();

        if (stocks.isEmpty()) {

            addDefaultStocks();
        }

        if (user == null) {

            user = new User("Guest", 50000);
        }
    }

    private void addDefaultStocks() {

        stocks.add(
                new Stock(
                        "AAPL",
                        "Apple",
                        2250));

        stocks.add(
                new Stock(
                        "GOOG",
                        "Google",
                        2100));

        stocks.add(
                new Stock(
                        "MSFT",
                        "Microsoft",
                        1850));

        stocks.add(
                new Stock(
                        "AMZN",
                        "Amazon",
                        1750));

        stocks.add(
                new Stock(
                        "TSLA",
                        "Tesla",
                        2300));
    }

    public User getUser() {
        return user;
    }

    public ArrayList<Stock> getStocks() {
        return stocks;
    }

    public Portfolio getPortfolio() {
        return portfolio;
    }

    public Stock findStock(String symbol) {

        for (Stock stock : stocks) {

            if (stock.getSymbol()
                    .equalsIgnoreCase(symbol)) {

                return stock;
            }
        }

        return null;
    }

    public void displayMarket() {

        System.out.println(
                "\n========================================================");

        System.out.println(
                "                    MARKET DASHBOARD");

        System.out.println(
                "========================================================");

        System.out.printf(
                "%-10s %-20s %s%n",
                "Symbol",
                "Company",
                "Price");

        System.out.println(
                "--------------------------------------------------------");

        for (Stock stock : stocks) {
            stock.display();
        }

        System.out.println(
                "========================================================");
    }

    public void searchStock(String symbol) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found.");

        } else {

            System.out.println(
                    "\nStock Found:");

            System.out.println(
                    "------------------------------------------------");

            stock.display();
        }
    }

    public boolean buyStock(
            String symbol,
            int quantity) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found.");

            return false;
        }

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero.");

            return false;
        }

        double total =
                stock.getPrice() * quantity;

        System.out.println(
                "\n---------------- BUY ORDER ----------------");

        System.out.println(
                "Stock        : " +
                        stock.getCompanyName() +
                        " (" +
                        stock.getSymbol() +
                        ")");

        System.out.println(
                "Quantity     : " + quantity);

        System.out.printf(
                "Market Price : Rs.%.2f%n",
                stock.getPrice());

        System.out.printf(
                "Order Value  : Rs.%.2f%n",
                total);

        System.out.println(
                "-------------------------------------------");

        if (!user.deductMoney(total)) {

            System.out.println(
                    "Insufficient balance.");

            return false;
        }

        portfolio.buyStock(
                stock.getSymbol(),
                quantity,
                stock.getPrice());

        transactions.add(
                new Transaction(
                        "BUY",
                        stock.getSymbol(),
                        quantity,
                        stock.getPrice(),
                        total));

        saveData();

        System.out.println(
                "Purchase completed successfully.");

        return true;
    }

    public boolean sellStock(
            String symbol,
            int quantity) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found.");

            return false;
        }

        if (quantity <= 0) {

            System.out.println(
                    "Quantity must be greater than zero.");

            return false;
        }

        Holding holding =
                portfolio.findHolding(symbol);

        if (holding == null ||
                holding.getQuantity() < quantity) {

            System.out.println(
                    "You do not own enough shares.");

            return false;
        }

        double total =
                stock.getPrice() * quantity;

        System.out.println(
                "\n---------------- SELL ORDER ----------------");

        System.out.println(
                "Stock        : " +
                        stock.getCompanyName() +
                        " (" +
                        stock.getSymbol() +
                        ")");

        System.out.println(
                "Quantity     : " + quantity);

        System.out.printf(
                "Market Price : Rs.%.2f%n",
                stock.getPrice());

        System.out.printf(
                "Sale Value   : Rs.%.2f%n",
                total);

        System.out.println(
                "--------------------------------------------");

        portfolio.sellStock(
                symbol,
                quantity);

        user.addMoney(total);

        transactions.add(
                new Transaction(
                        "SELL",
                        stock.getSymbol(),
                        quantity,
                        stock.getPrice(),
                        total));

        saveData();

        System.out.println(
                "Sale completed successfully.");

        return true;
    }

    public void displayPerformance() {

        double invested =
                portfolio.getTotalInvested();

        double currentValue =
                portfolio.getCurrentValue(stocks);

        double profitLoss =
                currentValue - invested;

        double returnPercentage = 0;

        if (invested > 0) {

            returnPercentage =
                    (profitLoss / invested) * 100;
        }

        System.out.println(
                "\n========================================================");

        System.out.println(
                "                PORTFOLIO PERFORMANCE");

        System.out.println(
                "========================================================");

        System.out.printf(
                "Initial Investment : Rs.%.2f%n",
                invested);

        System.out.printf(
                "Current Value      : Rs.%.2f%n",
                currentValue);

        System.out.printf(
                "Profit / Loss      : Rs.%.2f%n",
                profitLoss);

        System.out.printf(
                "Return             : %.2f%%%n",
                returnPercentage);

        if (profitLoss > 0) {

            System.out.println(
                    "Performance        : PROFIT");

        } else if (profitLoss < 0) {

            System.out.println(
                    "Performance        : LOSS");

        } else {

            System.out.println(
                    "Performance        : NO CHANGE");
        }

        System.out.println(
                "========================================================");
    }

    public void displayTransactions() {

        if (transactions.isEmpty()) {

            System.out.println(
                    "\nNo transactions found.");

            return;
        }

        System.out.println(
                "\n==============================================================");

        System.out.println(
                "                    TRANSACTION HISTORY");

        System.out.println(
                "==============================================================");

        System.out.printf(
                "%-7s %-8s %-8s %-12s %-12s %s%n",
                "Type",
                "Symbol",
                "Quantity",
                "Price",
                "Total",
                "Date & Time");

        System.out.println(
                "--------------------------------------------------------------------------");

        for (Transaction transaction :
                transactions) {

            transaction.display();
        }

        System.out.println(
                "==============================================================");
    }

    public void addMoney(double amount) {

        if (amount <= 0) {

            System.out.println(
                    "Amount must be greater than zero.");

            return;
        }

        user.addMoney(amount);

        saveData();

        System.out.printf(
                "Rs.%.2f added successfully.%n",
                amount);
    }

    public void updateMarketPrice(
            String symbol,
            double newPrice) {

        Stock stock = findStock(symbol);

        if (stock == null) {

            System.out.println(
                    "Stock not found.");

            return;
        }

        if (newPrice <= 0) {

            System.out.println(
                    "Price must be greater than zero.");

            return;
        }

        System.out.printf(
                "Old Price: Rs.%.2f%n",
                stock.getPrice());

        stock.setPrice(newPrice);

        saveData();

        System.out.printf(
                "New Price: Rs.%.2f%n",
                newPrice);

        System.out.println(
                "Market price updated successfully.");
    }

    public void displayAccountSummary() {

        double portfolioValue =
                portfolio.getCurrentValue(stocks);

        double totalValue =
                user.getBalance()
                        + portfolioValue;

        System.out.println(
                "\n========================================================");

        System.out.println(
                "                   ACCOUNT SUMMARY");

        System.out.println(
                "========================================================");

        System.out.println(
                "Investor          : " +
                        user.getName());

        System.out.printf(
                "Cash Balance      : Rs.%.2f%n",
                user.getBalance());

        System.out.printf(
                "Portfolio Value   : Rs.%.2f%n",
                portfolioValue);

        System.out.printf(
                "Total Account     : Rs.%.2f%n",
                totalValue);

        System.out.println(
                "========================================================");
    }

    public void saveData() {

        try {

            PrintWriter writer =
                    new PrintWriter(
                            new FileWriter(FILE_NAME));

            writer.println(
                    "USER|" +
                            user.getName() +
                            "|" +
                            user.getBalance());

            for (Stock stock : stocks) {

                writer.println(
                        "STOCK|" +
                                stock.getSymbol() +
                                "|" +
                                stock.getCompanyName() +
                                "|" +
                                stock.getPrice());
            }

            for (Holding holding :
                    portfolio.getHoldings()) {

                writer.println(
                        "HOLDING|" +
                                holding.getSymbol() +
                                "|" +
                                holding.getQuantity() +
                                "|" +
                                holding.getAverageBuyPrice());
            }

            for (Transaction transaction :
                    transactions) {

                writer.println(
                        transaction.saveData());
            }

            writer.close();

        } catch (IOException e) {

            System.out.println(
                    "Unable to save data.");
        }
    }

    private void loadData() {

        File file =
                new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try {

            BufferedReader reader =
                    new BufferedReader(
                            new FileReader(file));

            String line;

            while ((line = reader.readLine())
                    != null) {

                String[] data =
                        line.split("\\|", -1);

                if (data[0].equals("USER")) {

                    user =
                            new User(
                                    data[1],
                                    Double.parseDouble(data[2]));
                }

                else if (data[0].equals("STOCK")) {

                    stocks.add(
                            new Stock(
                                    data[1],
                                    data[2],
                                    Double.parseDouble(data[3])));
                }

                else if (data[0].equals("HOLDING")) {

                    portfolio.getHoldings().add(
                            new Holding(
                                    data[1],
                                    Integer.parseInt(data[2]),
                                    Double.parseDouble(data[3])));
                }

                else if (data[0].equals("TRANSACTION")) {

                    transactions.add(
                            new Transaction(
                                    data[1],
                                    data[2],
                                    Integer.parseInt(data[3]),
                                    Double.parseDouble(data[4]),
                                    Double.parseDouble(data[5]),
                                    data[6]));
                }
            }

            reader.close();

        } catch (Exception e) {

            stocks.clear();
            transactions.clear();
            portfolio.getHoldings().clear();
            user = null;
        }
    }
}