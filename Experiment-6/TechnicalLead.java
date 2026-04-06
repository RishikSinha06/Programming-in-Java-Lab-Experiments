/**
 * TECHNICAL LEAD
 */
class TechnicalLead extends ContractEmployee {

    private double bonus;

    public TechnicalLead(int id, String name, String dept, double rate, int months, double bonus) {
        super(id, name, dept, rate, months);
        this.bonus = bonus;
        this.accessLevel = "TECHNICAL (Admin Access)";
    }

    @Override
    public double calculateCTC() {
        return super.calculateCTC() + bonus;
    }

    @Override
    public String getPermissions() {
        return "Server access + deployment rights.";
    }
}