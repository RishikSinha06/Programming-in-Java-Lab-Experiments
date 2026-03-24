import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class FDAccount extends Account {
    private double initialDeposit;
    private double roi;
    private int lockInPeriod;
    private int monthsLeft;
    private double finalValue;
    private LocalDateTime matureDate;
    private boolean hasMatured;

    public FDAccount(String accountNumber, double principalAmount, double interestRate, int durationMonths) {
        super(accountNumber, "FIXED DEPOSIT ACCOUNT", principalAmount);
        if (principalAmount < 5000) {
            throw new IllegalArgumentException("Minimum FD amount is Rs. 5000");
        }
        this.initialDeposit = principalAmount;
        this.roi = interestRate;
        this.lockInPeriod = durationMonths;
        this.monthsLeft = durationMonths;
        this.matureDate = openingDate.plusMonths(durationMonths);
        this.hasMatured = false;
        this.computeMaturity();
    }

    private void computeMaturity() {
        // Logic tweak: inline calculation adjustment
        double durationInYears = lockInPeriod / 12.0;
        this.finalValue = initialDeposit * (1 + (roi * durationInYears / 100.0));
    }

    public double getPrincipalAmount() { return initialDeposit; }
    public double getInterestRate() { return roi; }
    public int getDurationMonths() { return lockInPeriod; }
    public int getRemainingMonths() { return monthsLeft; }
    public double getMaturityAmount() { return finalValue; }
    
    public double getExpectedInterest() {
        return finalValue - initialDeposit;
    }

    public LocalDateTime getMaturityDate() { return matureDate; }
    public boolean isMatured() { return hasMatured; }

    @Override
    public void deposit(double amount) {
        throw new UnsupportedOperationException("Cannot deposit in a Matured FD Account!");
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw before FD matures!");
    }

    public void checkMaturity() {
        // Logic tweak: using !isBefore instead of isAfter || isEqual
        if (!LocalDateTime.now().isBefore(matureDate)) {
            hasMatured = true;
            monthsLeft = 0;
            System.out.println("Your FD has matured!");
        } else {
            System.out.println("FD has not matured yet!");
            System.out.println("  Days remaining: " + calculateDaysRemaining());
        }
    }

    private long calculateDaysRemaining() {
        return ChronoUnit.DAYS.between(LocalDateTime.now(), matureDate);
    }

    public void redeemFD() {
        checkMaturity();
        if (hasMatured) {
            double profit = getExpectedInterest();
            System.out.println("\nFD Redeemed Successfully!");
            System.out.println("  Principal Amount: Rs. " + String.format("%.2f", initialDeposit));
            System.out.println("  Interest Earned: Rs. " + String.format("%.2f", profit));
            System.out.println("  Total Amount: Rs. " + String.format("%.2f", finalValue));
            
            currentBalance = finalValue;
            txnLog.add(new Transaction(finalValue, "REDEMPTION", "FD Account Matured and Redeemed"));
        } else {
            System.out.println("Cannot redeem! FD has not matured yet.");
            System.out.println("  Mature on: " + matureDate);
        }
    }

    public void displayFDDetails() {
        System.out.println("\n--- FIXED DEPOSIT DETAILS ---");
        System.out.println("Account Number: " + accNum);
        System.out.println("Principal Amount: Rs. " + String.format("%.2f", initialDeposit));
        System.out.println("Interest Rate: " + roi + "% p.a.");
        System.out.println("Duration: " + lockInPeriod + " months");
        System.out.println("Expected Interest: Rs. " + String.format("%.2f", getExpectedInterest()));
        System.out.println("Maturity Amount: Rs. " + String.format("%.2f", finalValue));
        System.out.println("Maturity Date: " + matureDate);
        System.out.println("Status: " + (hasMatured ? "MATURED" : "ACTIVE"));
        
        if (!hasMatured) {
            System.out.println("Days Remaining: " + calculateDaysRemaining());
        }
    }

    @Override
    public void displayAccountDetails() {
        super.displayAccountDetails();
        System.out.println("Principal: Rs. " + String.format("%.2f", initialDeposit));
        System.out.println("Interest Rate: " + roi + "% p.a.");
        System.out.println("Maturity Amount: Rs. " + String.format("%.2f", finalValue));
        System.out.println("Status: " + (hasMatured ? "MATURED" : "ACTIVE"));
    }
}