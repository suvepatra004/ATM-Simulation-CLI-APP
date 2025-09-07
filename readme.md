# 🏧 ATM Simulation CLI (Java)

A simple ATM Simulation built in **Java** using **OOP principles, file handling, and input validation**.

## 🚀 Features

- Login with Account Number + PIN (stored in `accounts.txt`)
- Create new accounts dynamically
- Check Balance, Deposit, Withdraw
- Validations:
  - No negative deposits/withdrawals
  - No withdrawals greater than balance
  - No negative initial balance
- Transactions logged with **timestamps** in `transactions.txt`
- Data persistence via text files

## 📂 Project Structure

```
TM-Simulation/
├── ATM.java
├── Account.java
├── accounts.txt
├── transactions.txt
└── README.md
```

## 🛠️ How to Run

1. Install [Java JDK](https://www.oracle.com/java/technologies/javase-downloads.html) (>= 17).
2. Open the project in **VS Code** (or any IDE).
3. Compile the files:

   ```sh
    javac ATM.java Account.java

    -- RUN Program --

    java ATM

    -- EXIT Forcefully --

    CTRL + C
   ```

## DEMO

```
===== Welcome to ATM Simulation =====
1. Login
2. Create New Account
3. Exit
Choose an option: 1
Enter Account Number: Suvendu
Enter PIN: 121212
✅ Login successful!

===== ATM Menu =====
1. Check Balance
2. Deposit
3. Withdraw
4. Logout
Choose an option: 2
Enter deposit amount: 4000
✅ Deposited: ₹4000.0 | Current Balance: ₹21500.0

===== ATM Menu =====
1. Check Balance
2. Deposit
3. Withdraw
4. Logout
Choose an option: 3
Enter withdrawal amount: -9000
❌ Invalid withdrawal amount. Please enter a positive value.

```
