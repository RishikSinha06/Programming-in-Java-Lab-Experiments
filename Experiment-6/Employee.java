/**
 * BASE CLASS
 */
class Employee {

    protected int empId;
    protected String empName;
    protected String deptName;
    protected double salary;
    protected String accessLevel;
    protected String category;

    public Employee(int empId, String empName, String deptName, double salary, String category) {
        this.empId = empId;
        this.empName = empName;
        this.deptName = deptName;
        this.salary = salary;
        this.category = category;
        this.accessLevel = "BASIC (Standard Access)";
    }

    public double calculateCTC() {
        return salary;
    }

    public String getPermissions() {
        return "Basic access to profile.";
    }

    public int getId() { return empId; }
    public String getName() { return empName; }
    public String getDept() { return deptName; }

    public String getEmployeeCategory() {
        return category;
    }
}