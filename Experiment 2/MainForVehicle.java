public class MainForVehicle {

    public static void main(String[] args) {

        // Create objects
        Vehicle v = new Vehicle();

        Vehicle v1 = new Vehicle("Honda", "City", 1200000, "Silver");
        v1.fuelType = 'D';
        v1.setMfgCode("MH12AB1234");

        Vehicle v2 = new Vehicle('P', 2000000, "H12QWE23");

        // Copy constructor object
        Vehicle v3 = new Vehicle(v1);

        // Array of vehicles
        Vehicle[] garage = { v, v1, v2, v3 };

        // TABLE OUTPUT 
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.printf("%-10s %-10s %-6s %-8s %-5s %-5s %-10s %-8s %-10s %-5s\n",
                "Brand", "Model", "Year", "Color", "Fuel", "Seats", "Price", "Mileage", "MfgCode", "Srv");
        System.out.println("---------------------------------------------------------------------------------------------");

        for (Vehicle veh : garage) {

            double mileage;

            if (veh.fuelType == 'D')
                mileage = veh.calcMileage(50, 500);
            else if (veh.fuelType == 'P' || veh.fuelType == 'C')
                mileage = veh.calcMileage(40, 500);
            else
                mileage = 0;

            Vehicle.printTabular(veh, mileage);
        }

        System.out.println("---------------------------------------------------------------------------------------------\n");

        // ACTIONS WITH VEHICLE NAME
        for (Vehicle veh : garage) {

            System.out.println("=== Actions for " + veh.brand + " " + veh.model + " ===");

            veh.start();
            veh.drive();
            veh.changeSpeed(60);
            veh.stop();

            System.out.println();
        }
    }
}