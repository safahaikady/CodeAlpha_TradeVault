public class Holding {

    private String symbol;
    private int quantity;
    private double averageBuyPrice;

    public Holding(String symbol, int quantity, double averageBuyPrice) {
        this.symbol = symbol;
        this.quantity = quantity;
        this.averageBuyPrice = averageBuyPrice;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getAverageBuyPrice() {
        return averageBuyPrice;
    }

    public void buy(int quantity, double price) {

        double oldValue = this.quantity * this.averageBuyPrice;
        double newValue = quantity * price;

        this.quantity += quantity;

        this.averageBuyPrice =
                (oldValue + newValue) / this.quantity;
    }

    public void sell(int quantity) {
        this.quantity -= quantity;
    }
}