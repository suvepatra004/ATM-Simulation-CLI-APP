import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Account {
    private String accNo;
    private String pin;
    private double balance;

    public Account(String accNo, String pin, double balance) {
        this.accNo = accNo;
        this.pin = pin;
        this.balance = balance;
    }

    public String getAccNo() {
        return accNo;
    }

    public String getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid deposit amount. Please enter a positive value.");
            return;
        }
        balance += amount;
        logTransaction("Deposit", amount);
        System.out.println("✅ Deposited: ₹" + amount + " | Current Balance: ₹" + balance);
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Invalid withdrawal amount. Please enter a positive value.");
            return;
        }
        if (amount > balance) {
            System.out.println("❌ Insufficient balance. Transaction failed.");
            return;
        }
        balance -= amount;
        logTransaction("Withdraw", amount);
        System.out.println("✅ Withdrawn: ₹" + amount + " | Current Balance: ₹" + balance);
    }

    public void checkBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
        logTransaction("Balance Inquiry", 0);
    }

    private void logTransaction(String type, double amount) {
        try (FileWriter fw = new FileWriter("transactions.txt", true)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String timestamp = dtf.format(LocalDateTime.now());
            fw.write(timestamp + " | " + accNo + " | " + type + " | Amount: ₹" + amount + " | Balance: ₹" + balance + "\n");
        } catch (IOException e) {
            System.out.println("⚠️ Error logging transaction: " + e.getMessage());
        }
    }

    public String toFileFormat() {
        return accNo + "," + pin + "," + balance;
    }
}
