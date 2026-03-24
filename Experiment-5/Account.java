import java.time.LocalDateTime;
import java.util.*;

public abstract class Account {
    protected String accNum;
    protected String accType;
    protected double currentBalance;
    protected List<Transaction> txnLog;
    protected LocalDateTime openingDate;

    public Account(String accNum, String accType, double initialBalance) {
        this.accNum = accNum;
        this.accType = accType;
        this.currentBalance = initialBalance;
        this.txnLog = new ArrayList<>();
        this.openingDate = LocalDateTime.now();
    }

    public String getAccountNumber() { return accNum; }
    public String getAccountType() { return accType; }
    public double getBalance() { return currentBalance; }
    public List<Transaction> getTransactions() { return txnLog; }
    public LocalDateTime getCreatedDate() { return openingDate; }

    public abstract void deposit(double amount);
    public abstract void withdraw(double amount);

    public void displayAccountDetails() {
        System.out.println("\n--- Account Details ---");
        System.out.println("Account Number: " + accNum);
        System.out.println("Account Type: " + accType);
        System.out.println("Balance: Rs. " + String.format("%.2f", currentBalance));
        System.out.println("Created Date: " + openingDate);
    }

    public void displayTransactionHistory() {
        System.out.println("\n--- Transaction History for " + accNum + " ---");
        if (txnLog.isEmpty()) {
            System.out.println("No transactions yet.");
            return; // Logic tweak: early return instead of if-else block
        } 
        
        for (Transaction t : txnLog) {
            System.out.println(t);
        }
    }
}