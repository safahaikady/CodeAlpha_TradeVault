public class User {

    private String name;
    private double balance;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getBalance() {
        return balance;
    }

    public void addMoney(double amount) {
        balance += amount;
    }

    public boolean deductMoney(double amount) {

        if (balance >= amount) {
            balance -= amount;
            return true;
        }

        return false;
    }
}