import java.util.*;

public class BankingApplication {
    private static List<Customer> bankCustomers = new ArrayList<>();
    private static int globalAccCounter = 1000;
    private static int globalCustCounter = 100;

    public static void main(String[] args) {
        Scanner inputScanner = new Scanner(System.in);
        boolean isRunning = true;

        loadSampleData();

        while (isRunning) {
            System.out.println("\n========== BANKING APPLICATION ==========");
            System.out.println("1. Create New Customer");
            System.out.println("2. View Customer Details");
            System.out.println("3. Manage Accounts");
            System.out.println("4. View All Customers");
            System.out.println("5. Exit");
            System.out.print("Choose option: ");

            int choice = getIntInput(inputScanner);

            // Logic tweak: switched to standard switch but organized logic slightly differently
            switch (choice) {
                case 1: createCustomer(inputScanner); break;
                case 2: viewCustomerDetails(inputScanner); break;
                case 3: manageAccounts(inputScanner); break;
                case 4: viewAllCustomers(); break;
                case 5:
                    System.out.println("\nThank you for using our banking services!");
                    isRunning = false;
                    break;
                default:
                    System.out.println(" Invalid choice! Please try again.");
            }
        }
        inputScanner.close();
    }

    private static void loadSampleData() {
        Customer cust1 = new Customer("CUST001", "Raj Kumar", "raj@email.com", "9876543210");
        Customer cust2 = new Customer("CUST002", "Priya Singh", "priya@email.com", "9876543211");
        Customer cust3 = new Customer("CUST003", "Amit Patel", "amit@email.com", "9876543212");

        cust1.addAccount(new SavingsAccount("SAV001", 25000));
        cust1.addAccount(new CurrentAccount("CUR001", 50000));
        cust1.addAccount(new LoanAccount("LOAN001", "Personal", 200000, 12.5, 60));

        cust2.addAccount(new SavingsAccount("SAV002", 15000));
        cust2.addAccount(new FDAccount("FD001", 100000, 7.5, 12));

        cust3.addAccount(new CurrentAccount("CUR002", 75000));
        cust3.addAccount(new LoanAccount("LOAN002", "Home", 5000000, 8.5, 240));

        bankCustomers.add(cust1);
        bankCustomers.add(cust2);
        bankCustomers.add(cust3);

        System.out.println("Sample data loaded successfully!");
    }

    private static void createCustomer(Scanner sc) {
        System.out.println("\n--- CREATE NEW CUSTOMER ---");

        String newCustId = "CUST" + (++globalCustCounter);
        System.out.print("Enter Name: ");
        String name = sc.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = sc.nextLine().trim();
        System.out.print("Enter Phone: ");
        String phone = sc.nextLine().trim();

        bankCustomers.add(new Customer(newCustId, name, email, phone));

        System.out.println("\nCustomer created successfully!");
        System.out.println("Customer ID: " + newCustId);
    }

    private static void viewCustomerDetails(Scanner sc) {
        System.out.println("\n--- VIEW CUSTOMER DETAILS ---");
        Customer target = selectCustomer(sc);
        if (target != null) {
            target.displayConsolidatedInfo();
        }
    }

    private static void manageAccounts(Scanner sc) {
        System.out.println("\n--- MANAGE ACCOUNTS ---");
        Customer activeCust = selectCustomer(sc);
        if (activeCust == null) return;

        boolean activeMenu = true;
        while (activeMenu) {
            System.out.println("\n--- Account Management ---");
            System.out.println("1. Create Account");
            System.out.println("2. View Account Details");
            System.out.println("3. Deposit Money");
            System.out.println("4. Withdraw Money");
            System.out.println("5. Pay EMI (Loan Account)");
            System.out.println("6. Calculate Interest (Savings)");
            System.out.println("7. Redeem FD");
            System.out.println("8. View Transaction History");
            System.out.println("9. Back to Main Menu");
            System.out.print("Choose option: ");

            int action = getIntInput(sc);

            switch (action) {
                case 1: createAccount(sc, activeCust); break;
                case 2: viewAccountDetails(sc, activeCust); break;
                case 3: depositMoney(sc, activeCust); break;
                case 4: withdrawMoney(sc, activeCust); break;
                case 5: payEMI(sc, activeCust); break;
                case 6: calculateInterest(sc, activeCust); break;
                case 7: redeemFD(sc, activeCust); break;
                case 8: viewTransactionHistory(sc, activeCust); break;
                case 9: activeMenu = false; break;
                default: System.out.println(" Invalid choice!");
            }
        }
    }

    private static void createAccount(Scanner sc, Customer c) {
        System.out.println("\n--- Create New Account ---");
        System.out.println("1. Savings Account");
        System.out.println("2. Current Account");
        System.out.println("3. Loan Account");
        System.out.println("4. Fixed Deposit Account");
        System.out.print("Select account type: ");

        int type = getIntInput(sc);
        String generatedAccNo = "ACC" + (++globalAccCounter);

        try {
            if (type == 1) {
                System.out.print("Enter initial balance (min Rs. 1000): ");
                c.addAccount(new SavingsAccount(generatedAccNo, getDoubleInput(sc)));
                System.out.println("Savings Account created: " + generatedAccNo);
            } else if (type == 2) {
                System.out.print("Enter initial balance: ");
                c.addAccount(new CurrentAccount(generatedAccNo, getDoubleInput(sc)));
                System.out.println("Current Account created: " + generatedAccNo);
            } else if (type == 3) {
                System.out.print("Enter loan type (Personal/Home/Auto/Education): ");
                String lType = sc.nextLine().trim();
                System.out.print("Enter principal amount: ");
                double p = getDoubleInput(sc);
                System.out.print("Enter interest rate (% p.a.): ");
                double r = getDoubleInput(sc);
                System.out.print("Enter tenure (months): ");
                int t = getIntInput(sc);
                c.addAccount(new LoanAccount(generatedAccNo, lType, p, r, t));
                System.out.println("Loan Account created: " + generatedAccNo);
            } else if (type == 4) {
                System.out.print("Enter FD amount (min Rs. 5000): ");
                double amt = getDoubleInput(sc);
                System.out.print("Enter interest rate (% p.a.): ");
                double rate = getDoubleInput(sc);
                System.out.print("Enter duration (months): ");
                int dur = getIntInput(sc);
                c.addAccount(new FDAccount(generatedAccNo, amt, rate, dur));
                System.out.println("Fixed Deposit Account created: " + generatedAccNo);
            } else {
                System.out.println(" Invalid account type!");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private static void viewAccountDetails(Scanner sc, Customer c) {
        System.out.println("\n--- View Account Details ---");
        Account acc = selectAccount(sc, c);
        if (acc == null) return;

        acc.displayAccountDetails();
        if (acc instanceof LoanAccount) ((LoanAccount) acc).displayLoanDetails();
        else if (acc instanceof FDAccount) ((FDAccount) acc).displayFDDetails();
    }

    private static void depositMoney(Scanner sc, Customer c) {
        System.out.println("\n--- Deposit Money ---");
        Account acc = selectAccount(sc, c);
        if (acc == null) return;

        System.out.print("Enter amount to deposit: ");
        try {
            acc.deposit(getDoubleInput(sc));
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private static void withdrawMoney(Scanner sc, Customer c) {
        System.out.println("\n--- Withdraw Money ---");
        Account acc = selectAccount(sc, c);
        if (acc == null) return;

        System.out.print("Enter amount to withdraw: ");
        try {
            acc.withdraw(getDoubleInput(sc));
        } catch (Exception e) {
            System.out.println(" Error: " + e.getMessage());
        }
    }

    private static void payEMI(Scanner sc, Customer c) {
        System.out.println("\n--- Pay EMI ---");
        // Logic tweak: Stream to filter
        List<Account> loans = new ArrayList<>();
        c.getAccounts().stream().filter(a -> a instanceof LoanAccount).forEach(loans::add);

        if (loans.isEmpty()) {
            System.out.println(" No loan accounts found!");
            return;
        }

        System.out.println("Available Loan Accounts:");
        for (int i = 0; i < loans.size(); i++) {
            System.out.println((i + 1) + ". " + loans.get(i).getAccountNumber() + " (" + loans.get(i).getAccountType() + ")");
        }
        System.out.print("Select account: ");
        int sel = getIntInput(sc) - 1;

        if (sel >= 0 && sel < loans.size()) {
            ((LoanAccount) loans.get(sel)).payEMI();
        } else {
            System.out.println(" Invalid selection!");
        }
    }

    private static void calculateInterest(Scanner sc, Customer c) {
        System.out.println("\n--- Calculate Interest ---");
        List<Account> savings = new ArrayList<>();
        for (Account a : c.getAccounts()) {
            if (a instanceof SavingsAccount) savings.add(a);
        }

        if (savings.isEmpty()) {
            System.out.println(" No savings accounts found!");
            return;
        }

        System.out.println("Available Savings Accounts:");
        for (int i = 0; i < savings.size(); i++) {
            System.out.println((i + 1) + ". " + savings.get(i).getAccountNumber());
        }
        System.out.print("Select account: ");
        int sel = getIntInput(sc) - 1;

        if (sel >= 0 && sel < savings.size()) {
            ((SavingsAccount) savings.get(sel)).calculateInterest();
        } else {
            System.out.println(" Invalid selection!");
        }
    }

    private static void redeemFD(Scanner sc, Customer c) {
        System.out.println("\n--- Redeem Fixed Deposit ---");
        List<Account> fds = new ArrayList<>();
        for (Account a : c.getAccounts()) {
            if (a instanceof FDAccount) fds.add(a);
        }

        if (fds.isEmpty()) {
            System.out.println(" No FD accounts found!");
            return;
        }

        System.out.println("Available FD Accounts:");
        for (int i = 0; i < fds.size(); i++) {
            System.out.println((i + 1) + ". " + fds.get(i).getAccountNumber());
        }
        System.out.print("Select account: ");
        int sel = getIntInput(sc) - 1;

        if (sel >= 0 && sel < fds.size()) {
            ((FDAccount) fds.get(sel)).redeemFD();
        } else {
            System.out.println(" Invalid selection!");
        }
    }

    private static void viewTransactionHistory(Scanner sc, Customer c) {
        System.out.println("\n--- View Transaction History ---");
        Account acc = selectAccount(sc, c);
        if (acc != null) acc.displayTransactionHistory();
    }

    private static void viewAllCustomers() {
        System.out.println("\n--- ALL CUSTOMERS SUMMARY ---");
        if (bankCustomers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        for (Customer cust : bankCustomers) {
            cust.displayConsolidatedInfo();
            System.out.println("=====================================");
        }
    }

    private static Customer selectCustomer(Scanner sc) {
        if (bankCustomers.isEmpty()) {
            System.out.println(" No customers found!");
            return null;
        }
        System.out.println("\nAvailable Customers:");
        for (int i = 0; i < bankCustomers.size(); i++) {
            System.out.println((i + 1) + ". " + bankCustomers.get(i).getName() + " (" + bankCustomers.get(i).getCustomerId() + ")");
        }
        System.out.print("Select customer: ");
        int idx = getIntInput(sc) - 1;
        
        return (idx >= 0 && idx < bankCustomers.size()) ? bankCustomers.get(idx) : null;
    }

    private static Account selectAccount(Scanner sc, Customer c) {
        List<Account> list = c.getAccounts();
        if (list.isEmpty()) {
            System.out.println(" No accounts found for this customer!");
            return null;
        }
        System.out.println("\nAvailable Accounts:");
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i).getAccountNumber() + " (" + list.get(i).getAccountType() + ")");
        }
        System.out.print("Select account: ");
        int idx = getIntInput(sc) - 1;
        
        if (idx >= 0 && idx < list.size()) return list.get(idx);
        
        System.out.println(" Invalid selection!");
        return null;
    }

    private static int getIntInput(Scanner sc) {
        try {
            int val = sc.nextInt();
            sc.nextLine();
            return val;
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println(" Please enter a valid number!");
            return -1;
        }
    }

    private static double getDoubleInput(Scanner sc) {
        try {
            double val = sc.nextDouble();
            sc.nextLine();
            return val;
        } catch (InputMismatchException e) {
            sc.nextLine();
            System.out.println(" Please enter a valid amount!");
            return -1;
        }
    }
}