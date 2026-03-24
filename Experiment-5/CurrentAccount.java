public class CurrentAccount extends Account {
    private double odLimit;
    private double cbqFee;
    private boolean isOdActive;

    public CurrentAccount(String accountNumber, double initialBalance) {
        super(accountNumber, "CURRENT ACCOUNT", initialBalance);
        this.odLimit = initialBalance * 0.25; 
        this.cbqFee = 100;
        this.isOdActive = true;
    }

    public double getOverdraftLimit() { return odLimit; }

    public double getAvailableBalance() {
        // Logic tweak: Used ternary operator
        return isOdActive ? (currentBalance + odLimit) : currentBalance;
    }

    public boolean isOverdraftFacilityEnabled() { return isOdActive; }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive!");
        
        currentBalance += amount;
        txnLog.add(new Transaction(amount, "DEPOSIT", "Deposit to Current Account"));
        
        System.out.println("Deposit successful! Amount: Rs. " + String.format("%.2f", amount));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
        System.out.println("  Available Balance (incl. Overdraft): Rs. " + String.format("%.2f", getAvailableBalance()));
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Withdrawal amount must be positive!");
        
        // Logic tweak: simplified conditional logic using the helper method
        double maxAllowed = getAvailableBalance();
        if (amount > maxAllowed) {
            throw new IllegalArgumentException("Insufficient balance! Available: Rs. " + String.format("%.2f", maxAllowed));
        }
        
        currentBalance -= amount;
        txnLog.add(new Transaction(amount, "WITHDRAWAL", "Withdrawal from Current Account"));
        
        System.out.println("Withdrawal successful! Amount: Rs. " + String.format("%.2f", amount));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
        
        if (currentBalance < 0) {
            System.out.println("  Overdraft Used: Rs. " + String.format("%.2f", Math.abs(currentBalance)));
        }
    }

    public void requestChequeBook() {
        if (currentBalance >= cbqFee) {
            currentBalance -= cbqFee;
            txnLog.add(new Transaction(cbqFee, "FEE", "Cheque Book Fee"));
            System.out.println("Cheque book requested! Fee: Rs. " + String.format("%.2f", cbqFee));
            System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
        } else {
            System.out.println("Insufficient balance to request cheque book!");
        }
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Overdraft Limit: Rs. " + String.format("%.2f", odLimit));
        System.out.println("Available Balance (incl. Overdraft): Rs. " + String.format("%.2f", getAvailableBalance()));
        System.out.println("Overdraft Facility: " + (isOdActive ? "Enabled" : "Disabled"));
    }
}