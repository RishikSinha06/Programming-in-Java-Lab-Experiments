/**
 * CONTRACT EMPLOYEE
 */
class ContractEmployee extends Employee {

    protected int durationMonths;

    public ContractEmployee(int id, String name, String dept, double hourlyRate, int months) {
        super(id, name, dept, hourlyRate * 160, "Contract");
        this.durationMonths = months;
        this.accessLevel = "LIMITED (Vendor Access)";
    }

    @Override
    public String getPermissions() {
        return "Limited project-based access.";
    }
}