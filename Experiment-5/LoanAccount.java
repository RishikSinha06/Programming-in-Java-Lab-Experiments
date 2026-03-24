public class LoanAccount extends Account {
    private String category; 
    private double borrowedAmount;
    private double rateOfInterest;
    private int totalMonths;
    private int monthsLeft;
    private double monthlyInstalment;
    private double grossInterestPayable;
    private double totalPaid;

    public LoanAccount(String accountNumber, String loanType, double principalAmount, 
                       double interestRate, int tenureMonths) {
        super(accountNumber, "LOAN ACCOUNT (" + loanType + ")", principalAmount);
        this.category = loanType;
        this.borrowedAmount = principalAmount;
        this.rateOfInterest = interestRate;
        this.totalMonths = tenureMonths;
        this.monthsLeft = tenureMonths;
        this.totalPaid = 0;
        this.computeEmi();
    }

    private void computeEmi() {
        // Logic tweak: extracted variables to make the formula cleaner
        double ratePerMonth = (rateOfInterest / 100) / 12;
        double compoundedFactor = Math.pow(1 + ratePerMonth, totalMonths);
        
        this.monthlyInstalment = (borrowedAmount * ratePerMonth * compoundedFactor) / (compoundedFactor - 1);
        this.grossInterestPayable = (monthlyInstalment * totalMonths) - borrowedAmount;
    }

    public String getLoanType() { return category; }
    public double getPrincipalAmount() { return borrowedAmount; }
    public double getInterestRate() { return rateOfInterest; }
    public int getTenureMonths() { return totalMonths; }
    public int getRemainingMonths() { return monthsLeft; }
    public double getEMIAmount() { return monthlyInstalment; }
    public double getTotalInterestPayable() { return grossInterestPayable; }
    public double getAmountPaid() { return totalPaid; }
    
    public double getRemainingAmount() {
        return monthlyInstalment * monthsLeft;
    }

    @Override
    public void deposit(double amount) {
        if (amount <= 0) throw new IllegalArgumentException("Deposit amount must be positive!");
        
        currentBalance += amount;
        txnLog.add(new Transaction(amount, "DEPOSIT", "Payment towards Loan Account"));
        
        System.out.println("Payment received! Amount: Rs. " + String.format("%.2f", amount));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw from a Loan Account!");
    }

    public void payEMI() {
        if (monthsLeft <= 0) {
            System.out.println("Loan already fully paid!");
            return;
        }
        if (currentBalance < monthlyInstalment) {
            System.out.println("Insufficient balance to pay EMI!");
            System.out.println("  Required: Rs. " + String.format("%.2f", monthlyInstalment));
            System.out.println("  Available: Rs. " + String.format("%.2f", currentBalance));
            return;
        }
        
        currentBalance -= monthlyInstalment;
        monthsLeft--;
        totalPaid += monthlyInstalment;
        
        String desc = "EMI Payment - Month " + (totalMonths - monthsLeft) + " of " + totalMonths;
        txnLog.add(new Transaction(monthlyInstalment, "EMI_PAYMENT", desc));
        
        System.out.println("EMI Payment successful!");
        System.out.println("  Amount Paid: Rs. " + String.format("%.2f", monthlyInstalment));
        System.out.println("  Remaining Months: " + monthsLeft);
        System.out.println("  Remaining Amount: Rs. " + String.format("%.2f", getRemainingAmount()));
        System.out.println("  New Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    public void displayLoanDetails() {
        System.out.println("\n--- LOAN ACCOUNT DETAILS ---");
        System.out.println("Account Number: " + accNum);
        System.out.println("Loan Type: " + category);
        System.out.println("Principal Amount: Rs. " + String.format("%.2f", borrowedAmount));
        System.out.println("Interest Rate: " + rateOfInterest + "% p.a.");
        System.out.println("Tenure: " + totalMonths + " months");
        System.out.println("Monthly EMI: Rs. " + String.format("%.2f", monthlyInstalment));
        System.out.println("Total Interest Payable: Rs. " + String.format("%.2f", grossInterestPayable));
        System.out.println("Amount Paid: Rs. " + String.format("%.2f", totalPaid));
        System.out.println("Remaining Amount: Rs. " + String.format("%.2f", getRemainingAmount()));
        System.out.println("Remaining Months: " + monthsLeft);
        System.out.println("Current Balance: Rs. " + String.format("%.2f", currentBalance));
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Loan Type: " + category);
        System.out.println("Principal: Rs. " + String.format("%.2f", borrowedAmount));
        System.out.println("Monthly EMI: Rs. " + String.format("%.2f", monthlyInstalment));
        System.out.println("Remaining Months: " + monthsLeft);
    }
}