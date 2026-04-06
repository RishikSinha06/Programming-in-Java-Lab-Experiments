/**
 * INTERN
 */
class Intern extends Employee {

    private String college;

    public Intern(int id, String name, String dept, double stipend, String college) {
        super(id, name, dept, stipend, "Intern");
        this.college = college;
        this.accessLevel = "GUEST (Restricted Access)";
    }

    @Override
    public String getPermissions() {
        return "Read-only training access.";
    }
}