import java.util.ArrayList;

/**
 * PAYROLL MANAGER
 */
class PayrollManager {

    private ArrayList<Employee> empList = new ArrayList<>();

    public void loadSampleData() {
        empList.add(new Manager(1001, "John Smith", "IT", 85000, 15000));
        empList.add(new Executive(1002, "Sarah Connor", "Operations", 70000, 8000));
        empList.add(new TechnicalLead(2001, "James Bond", "CyberSec", 50, 12, 10000));
        empList.add(new ContractEmployee(2002, "Ethan Hunt", "Logistics", 45, 6));
        empList.add(new Intern(3001, "Peter Parker", "R&D", 2500, "MIT"));
        empList.add(new Intern(3002, "Gwen Stacy", "Biology", 2800, "Empire State"));
    }

    public void displayEmployees(String filter) {

        System.out.println("\n" + "=".repeat(100));
        System.out.printf("| %-5s | %-15s | %-12s | %-15s | %-12s | %-10s |%n",
                "ID", "Name", "Dept", "Role", "Category", "CTC");
        System.out.println("-".repeat(100));

        for (Employee emp : empList) {

            if (filter.equalsIgnoreCase("ALL") ||
                emp.getEmployeeCategory().equalsIgnoreCase(filter)) {

                System.out.printf("| %-5d | %-15s | %-12s | %-15s | %-12s | %-10.2f |%n",
                        emp.getId(),
                        emp.getName(),
                        emp.getDept(),
                        emp.getClass().getSimpleName(),
                        emp.getEmployeeCategory(),
                        emp.calculateCTC());
            }
        }

        System.out.println("=".repeat(100));
    }

    public void showEmployeeDetails(int id) {

        for (Employee emp : empList) {

            if (emp.getId() == id) {

                System.out.println("\n>>> DETAILED VIEW FOR ID: " + id);
                System.out.println("Name:        " + emp.getName());
                System.out.println("Department:  " + emp.getDept());
                System.out.println("Category:    " + emp.getEmployeeCategory());
                System.out.println("Specific Role: " + emp.getClass().getSimpleName());
                System.out.println("Net CTC:     $" + emp.calculateCTC());
                System.out.println("--------------------------------------------------");
                System.out.println("PRIVILEGE INFO:");
                System.out.println("Access Level: " + emp.accessLevel);
                System.out.println("Permissions:  " + emp.getPermissions());
                System.out.println("--------------------------------------------------");
                return;
            }
        }

        System.out.println("Employee not found.");
    }
}