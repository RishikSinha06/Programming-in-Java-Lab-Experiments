import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private static int txnCounter = 1000;
    private String txnRefId;
    private double txnAmount;
    private String txnCategory; 
    private LocalDateTime timestamp;
    private String txnDescription;

    public Transaction(double amount, String type, String description) {
        // Logic tweak: Pre-incrementing inside the assignment
        this.txnRefId = "TXN" + (++txnCounter);
        this.txnAmount = amount;
        this.txnCategory = type;
        this.timestamp = LocalDateTime.now();
        this.txnDescription = description;
    }

    public String getTransactionId() { return txnRefId; }
    public double getAmount() { return txnAmount; }
    public String getType() { return txnCategory; }
    public LocalDateTime getDateTime() { return timestamp; }
    public String getDescription() { return txnDescription; }

    @Override
    public String toString() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return String.format("ID: %s | Type: %-12s | Amount: Rs. %8.2f | Date: %s | Desc: %s",
                txnRefId, txnCategory, txnAmount, timestamp.format(dtf), txnDescription);
    }
}