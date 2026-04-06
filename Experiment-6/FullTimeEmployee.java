/**
 * FULL TIME EMPLOYEE
 */
class FullTimeEmployee extends Employee {

    protected double insurance = 2000;
    protected double providentFund = 3000;

    public FullTimeEmployee(int id, String name, String dept, double baseSalary) {
        super(id, name, dept, baseSalary, "Full-Time");
        this.accessLevel = "STANDARD (Internal Access)";
    }

    @Override
    public double calculateCTC() {
        return salary + insurance + providentFund;
    }

    @Override
    public String getPermissions() {
        return "Internal access + knowledge base.";
    }
}