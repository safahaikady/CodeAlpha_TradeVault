import java.util.ArrayList;

public class Portfolio {

    private ArrayList<Holding> holdings;

    public Portfolio() {
        holdings = new ArrayList<>();
    }

    public ArrayList<Holding> getHoldings() {
        return holdings;
    }

    public Holding findHolding(String symbol) {

        for (Holding holding : holdings) {

            if (holding.getSymbol()
                    .equalsIgnoreCase(symbol)) {

                return holding;
            }
        }

        return null;
    }

    public void buyStock(String symbol,
                         int quantity,
                         double price) {

        Holding holding = findHolding(symbol);

        if (holding == null) {

            holdings.add(
                    new Holding(
                            symbol,
                            quantity,
                            price));

        } else {

            holding.buy(quantity, price);
        }
    }

    public boolean sellStock(String symbol,
                             int quantity) {

        Holding holding = findHolding(symbol);

        if (holding == null ||
                holding.getQuantity() < quantity) {

            return false;
        }

        holding.sell(quantity);

        if (holding.getQuantity() == 0) {
            holdings.remove(holding);
        }

        return true;
    }

    public void displayPortfolio(
            ArrayList<Stock> market) {

        if (holdings.isEmpty()) {

            System.out.println(
                    "\nYour portfolio is empty.");

            return;
        }

        System.out.println(
                "\n========================================================");

        System.out.println(
                "                    MY PORTFOLIO");

        System.out.println(
                "========================================================");

        System.out.printf(
                "%-8s %-10s %-14s %-14s %-14s%n",
                "Symbol",
                "Quantity",
                "Avg Buy",
                "Current",
                "Value");

        System.out.println(
                "--------------------------------------------------------");

        for (Holding holding : holdings) {

            Stock stock =
                    findStock(
                            market,
                            holding.getSymbol());

            double currentPrice =
                    stock.getPrice();

            double currentValue =
                    holding.getQuantity()
                            * currentPrice;

            System.out.printf(
                    "%-8s %-10d Rs.%-10.2f Rs.%-10.2f Rs.%-10.2f%n",
                    holding.getSymbol(),
                    holding.getQuantity(),
                    holding.getAverageBuyPrice(),
                    currentPrice,
                    currentValue);
        }

        System.out.println(
                "========================================================");
    }

    public double getTotalInvested() {

        double total = 0;

        for (Holding holding : holdings) {

            total += holding.getQuantity()
                    * holding.getAverageBuyPrice();
        }

        return total;
    }

    public double getCurrentValue(
            ArrayList<Stock> market) {

        double total = 0;

        for (Holding holding : holdings) {

            Stock stock =
                    findStock(
                            market,
                            holding.getSymbol());

            total += holding.getQuantity()
                    * stock.getPrice();
        }

        return total;
    }

    private Stock findStock(
            ArrayList<Stock> market,
            String symbol) {

        for (Stock stock : market) {

            if (stock.getSymbol()
                    .equalsIgnoreCase(symbol)) {

                return stock;
            }
        }

        return null;
    }
}