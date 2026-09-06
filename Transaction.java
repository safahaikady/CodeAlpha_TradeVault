import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {

    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private double total;
    private String dateTime;

    public Transaction(String type, String symbol,
                       int quantity, double price,
                       double total) {

        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.total = total;

        this.dateTime =
                LocalDateTime.now().format(
                        DateTimeFormatter.ofPattern(
                                "yyyy-MM-dd HH:mm:ss"));
    }

    public Transaction(String type, String symbol,
                       int quantity, double price,
                       double total, String dateTime) {

        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.total = total;
        this.dateTime = dateTime;
    }

    public void display() {

        System.out.printf(
                "%-7s %-8s %-8d Rs.%-10.2f Rs.%-10.2f %s%n",
                type,
                symbol,
                quantity,
                price,
                total,
                dateTime);
    }

    public String saveData() {

        return "TRANSACTION|" +
                type + "|" +
                symbol + "|" +
                quantity + "|" +
                price + "|" +
                total + "|" +
                dateTime;
    }
}