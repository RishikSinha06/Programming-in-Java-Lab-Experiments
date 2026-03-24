public class SavingsAccount extends Account {
    private static final double REQ_MIN_BAL = 1000;
    private static final double ANNUAL_INT_RATE = 4.0; 
    private double minRequired;
    private double baseInterest;

    public SavingsAccount(String accountNumber, double initialBalance) {
        super(accountNumber, "SAVINGS ACCOUNT", initialBalance);
        if (initialBalance < REQ_MIN_BAL) {
            throw new IllegalArgumentException("Minimum balance required: Rs. " + REQ_MIN_BAL);
        }
        this.minRequired = REQ_MIN_BAL;
        this.baseInterest = ANNUAL_INT_RATE;
    }

    public double getMinimumBalance() { return minRequired; }
    public double getInterestRate() { return baseInterest; }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive!");
        
        currentBalance += amount;
        txnLog.add(new Transaction(amount, "DEPOSIT", "Deposit to Savings Account"));
        
        System.out.println("Deposit successful! Amount: Rs. " + String.format("%.2f", amount));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive!");
        
        // Logic tweak: Combined condition for insufficient funds / minimum balance limit
        double projectedBalance = currentBalance - amount;
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Insufficient balance! Available: Rs. " + String.format("%.2f", currentBalance));
        } else if (projectedBalance < minRequired) {
            throw new IllegalArgumentException("Cannot withdraw! Minimum balance of Rs. " + minRequired + " must be maintained.");
        }
        
        currentBalance = projectedBalance;
        txnLog.add(new Transaction(amount, "WITHDRAWAL", "Withdrawal from Savings Account"));
        
        System.out.println("Withdrawal successful! Amount: Rs. " + String.format("%.2f", amount));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    public void calculateInterest() {
        // Logic tweak: rearranged mathematical operations
        double yield = currentBalance * (baseInterest / 100.0);
        currentBalance += yield;
        txnLog.add(new Transaction(yield, "INTEREST_CREDIT", "Annual Interest Credit (" + baseInterest + "%)"));
        
        System.out.println("Interest calculated and credited!");
        System.out.println("  Interest Amount: Rs. " + String.format("%.2f", yield));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Minimum Balance: Rs. " + String.format("%.2f", minRequired));
        System.out.println("Interest Rate: " + baseInterest + "% p.a.");
    }
}