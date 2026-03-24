import java.util.*;

public class Customer {
    private String custRefId;
    private String fullName;
    private String emailAddress;
    private String contactNumber;
    private List<Account> accountList;

    public Customer(String customerId, String name, String email, String phone) {
        this.custRefId = customerId;
        this.fullName = name;
        this.emailAddress = email;
        this.contactNumber = phone;
        this.accountList = new ArrayList<>();
    }

    public String getCustomerId() { return custRefId; }
    public String getName() { return fullName; }
    public String getEmail() { return emailAddress; }
    public String getPhone() { return contactNumber; }
    public List<Account> getAccounts() { return accountList; }

    public void addAccount(Account account) {
        accountList.add(account);
    }

    public Account findAccount(String accountNumber) {
        // Logic tweak: Using Java 8 Stream API for cleaner search
        return accountList.stream()
                .filter(acc -> acc.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    public double getTotalBalance() {
        // Logic tweak: Refactored loop to stream reduction
        return accountList.stream()
                .mapToDouble(Account::getBalance)
                .sum();
    }

    public void displayCustomerDetails() {
        System.out.println("\n--- CUSTOMER PROFILE ---");
        System.out.println("Customer ID: " + custRefId);
        System.out.println("Name: " + fullName);
        System.out.println("Email: " + emailAddress);
        System.out.println("Phone: " + contactNumber);
        System.out.println("Total Accounts: " + accountList.size());
        System.out.println("Total Balance: Rs. " + String.format("%.2f", getTotalBalance()));
    }

    public void displayAllAccounts() {
        if (accountList.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        } 
        
        System.out.println("\n--- Accounts for Customer " + fullName + " ---");
        for (Account account : accountList) {
            account.displayAccountDetails();
        }
    }

    public void displayConsolidatedInfo() {
        displayCustomerDetails();
        System.out.println("\n--- ACCOUNT SUMMARY ---");
        
        if (accountList.isEmpty()) {
            System.out.println("No accounts.");
        } else {
            for (Account account : accountList) {
                System.out.println("  " + account.getAccountType() + " | Acc#: " + 
                    account.getAccountNumber() + " | Balance: Rs. " + 
                    String.format("%.2f", account.getBalance()));
            }
        }
    }
}