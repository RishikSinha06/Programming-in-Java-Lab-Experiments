import java.util.Scanner;

/**
 * MAIN CLASS
 */
public class EmployeePayrollSystem {

    public static void main(String[] args) {

        PayrollManager system = new PayrollManager();
        system.loadSampleData();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n--- EMPLOYEE PAYROLL & PRIVILEGE SYSTEM ---");
            System.out.println("1) Show All Employees");
            System.out.println("2) Filter by Type (Full-Time/Contract/Intern)");
            System.out.println("3) View Detailed Employee Info (by ID)");
            System.out.println("4) Exit");
            System.out.print("Select an option: ");

            String choice = sc.nextLine();

            switch (choice) {

                case "1":
                    system.displayEmployees("ALL");
                    break;

                case "2":
                    System.out.println("\nF - Full-Time | C - Contract | I - Intern");
                    String type = sc.nextLine().toUpperCase();

                    if (type.equals("F")) system.displayEmployees("Full-Time");
                    else if (type.equals("C")) system.displayEmployees("Contract");
                    else if (type.equals("I")) system.displayEmployees("Intern");
                    else System.out.println("Invalid choice");

                    break;

                case "3":
                    System.out.print("Enter Employee ID: ");
                    try {
                        int id = Integer.parseInt(sc.nextLine());
                        system.showEmployeeDetails(id);
                    } catch (Exception e) {
                        System.out.println("Invalid ID format");
                    }
                    break;

                case "4":
                    System.out.println("System shutting down...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid input.");
            }
        }
    }
}