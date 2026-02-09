public class MainForVehicle {

    public static void main(String[] args) {

        // Vehicle instances
        Vehicle carA = new Vehicle();

        Vehicle carB = new Vehicle("Hyundai", "Verna", 1350000, "White");
        carB.fuelCategory = 'P';
        carB.setManufacturingCode("TN09XY5678");

        Vehicle carC = new Vehicle('D', 2450000, "DL8CAF9999");

        // Copy constructor
        Vehicle carD = new Vehicle(carB);

        // Vehicle array
        Vehicle[] parkingLot = { carA, carB, carC, carD };

        // Table header
        System.out.println("================================================================================================");
        System.out.printf("%-12s %-12s %-6s %-8s %-6s %-6s %-12s %-8s %-12s %-5s%n",
                "Make", "Variant", "Year", "Color", "Fuel", "Seats", "Price", "Mileage", "MfgCode", "Srv");
        System.out.println("================================================================================================");

        for (Vehicle current : parkingLot) {

            double mileageValue;

            if (current.fuelCategory == 'D')
                mileageValue = current.calcMileage(55, 600);
            else if (current.fuelCategory == 'P' || current.fuelCategory == 'C')
                mileageValue = current.calcMileage(42, 600);
            else
                mileageValue = 0;

            Vehicle.printTabular(current, mileageValue);
        }

        System.out.println("================================================================================================\n");

        // Vehicle actions
        for (Vehicle current : parkingLot) {

            System.out.println(">>> Operating vehicle: " + current.make + " " + current.variant);

            current.start();
            current.drive();
            current.changeSpeed(70);
            current.stop();

            System.out.println();
        }
    }
}
