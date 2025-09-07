import java.io.*;
import java.util.*;

public class ATM {
    private static Map<String, Account> accounts = new HashMap<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadAccounts();

        while (true) {
            System.out.println("\n===== Welcome to ATM Simulation =====");
            System.out.println("1. Login");
            System.out.println("2. Create New Account");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> login();
                case 2 -> createAccount();
                case 3 -> {
                    saveAccounts();
                    System.out.println("👋 Exiting. Thank you for using the ATM!");
                    return;
                }
                default -> System.out.println("❌ Invalid option. Please try again.");
            }
        }
    }

    private static void loadAccounts() {
        try (BufferedReader br = new BufferedReader(new FileReader("accounts.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length == 3) {
                    accounts.put(data[0], new Account(data[0], data[1], Double.parseDouble(data[2])));
                }
            }
        } catch (IOException e) {
            System.out.println("⚠️ No existing accounts found. Starting fresh.");
        }
    }

    private static void saveAccounts() {
        try (FileWriter fw = new FileWriter("accounts.txt")) {
            for (Account acc : accounts.values()) {
                fw.write(acc.toFileFormat() + "\n");
            }
        } catch (IOException e) {
            System.out.println("⚠️ Error saving accounts: " + e.getMessage());
        }
    }

    private static void createAccount() {
        System.out.print("Enter New Account Number: ");
        String accNo = sc.nextLine().trim();

        if (accounts.containsKey(accNo)) {
            System.out.println("❌ Account already exists. Try logging in.");
            return;
        }

        System.out.print("Enter PIN: ");
        String pin = sc.nextLine().trim();

        System.out.print("Enter Initial Balance: ");
        double balance = sc.nextDouble();
        sc.nextLine();

        if (balance < 0) {
            System.out.println("❌ Invalid initial balance. Setting balance to ₹0.");
            balance = 0;
        }

        Account newAcc = new Account(accNo, pin, balance);
        accounts.put(accNo, newAcc);
        saveAccounts();

        System.out.println("✅ Account created successfully!");
    }

    private static void login() {
        System.out.print("Enter Account Number: ");
        String accNo = sc.nextLine().trim();
        System.out.print("Enter PIN: ");
        String pin = sc.nextLine().trim();

        Account acc = accounts.get(accNo);
        if (acc != null && acc.getPin().equals(pin)) {
            System.out.println("✅ Login successful!");
            atmMenu(acc);
        } else {
            System.out.println("❌ Incorrect Account Number or PIN.");
        }
    }

    private static void atmMenu(Account acc) {
        while (true) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Logout");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> acc.checkBalance();
                case 2 -> {
                    System.out.print("Enter deposit amount: ");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                }
                case 3 -> {
                    System.out.print("Enter withdrawal amount: ");
                    double wd = sc.nextDouble();
                    acc.withdraw(wd);
                }
                case 4 -> {
                    saveAccounts();
                    System.out.println("👋 Logged out successfully.");
                    return;
                }
                default -> System.out.println("❌ Invalid option.");
            }
        }
    }
}
