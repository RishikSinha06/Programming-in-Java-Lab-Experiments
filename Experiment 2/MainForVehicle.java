public class MainForVehicle {

    public static void printDetails(Vehicle v) {
        System.out.println("-----------------------------------------");
        System.out.println("------------ Vehicle Details ------------");
        System.out.println("-----------------------------------------");
        System.out.println("Brand Name : " + v.brandName);
        System.out.println("Model Name : " + v.modelName);
        System.out.println("Price      : " + v.price);
        System.out.println("Fuel Type  : " + v.fuelType);
        System.out.println("Seats      : " + v.seats);
        System.out.println("-----------------------------------------");
    }

    public static void main(String[] args) {

        // Creating vehicles
        Vehicle v1 = new Vehicle();
        Vehicle v2 = new Vehicle("Toyota", "Camry", 250000f, 'D');
        Vehicle v3 = new Vehicle("Honda", "City", 180000f, 'P');
        Vehicle v4 = new Vehicle("Tata", "Nexon", 160000f, 'C');

        // Copy constructor
        Vehicle v5 = new Vehicle(v2);

        // Array of vehicles
        Vehicle[] vehicles = { v1, v2, v3, v4, v5 };

        for (Vehicle v : vehicles) {
            printDetails(v);

            v.start();
            v.drive();
            v.changeSpeed(40);

            System.out.println("Mileage is : " + v.getMileage() + " km/litre");

            v.stop();
            System.out.println();
        }
    }
}
