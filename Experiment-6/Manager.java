/**
 * MANAGER
 */
class Manager extends FullTimeEmployee {

    private double allowance;

    public Manager(int id, String name, String dept, double baseSalary, double allowance) {
        super(id, name, dept, baseSalary);
        this.allowance = allowance;
        this.accessLevel = "HIGH (Management Access)";
    }

    @Override
    public double calculateCTC() {
        return super.calculateCTC() + allowance;
    }

    @Override
    public String getPermissions() {
        return "Full control over team and budgets.";
    }
}