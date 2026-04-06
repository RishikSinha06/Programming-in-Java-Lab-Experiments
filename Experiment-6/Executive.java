/**
 * EXECUTIVE
 */
class Executive extends FullTimeEmployee {

    private double bonus;

    public Executive(int id, String name, String dept, double baseSalary, double bonus) {
        super(id, name, dept, baseSalary);
        this.bonus = bonus;
        this.accessLevel = "MID (Operational Access)";
    }

    @Override
    public double calculateCTC() {
        return super.calculateCTC() + bonus;
    }

    @Override
    public String getPermissions() {
        return "Access to reports and planning tools.";
    }
}