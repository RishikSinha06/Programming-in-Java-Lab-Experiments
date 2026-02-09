public class Vehicle {

    // Public data members
    public String brand;
    public String model;
    public java.time.Year yearOfMfg;
    public String color;
    public char fuelType;   // E, P, D, C
    public double price;
    public int seatingCapacity;

    // Private members
    private String mfgCode;
    private int noOfServices;

    // Default constructor
    public Vehicle() {
        brand = "Toyota";
        model = "Camry";
        yearOfMfg = java.time.Year.of(2025);
        color = "White";
        fuelType = 'P';
        seatingCapacity = 5;
        price = 2400000;
    }

    // Parameterized constructor 
    public Vehicle(String brand, String model, double price, String color) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.color = color;
        this.yearOfMfg = java.time.Year.of(2024);
        this.fuelType = 'P';
        this.seatingCapacity = 5;
    }

    // Parameterized constructor (fuel + manufacturing)
    public Vehicle(char fuelType, double price, String mfgCode) {
        this.brand = "Generic";
        this.model = "ModelX";
        this.yearOfMfg = java.time.Year.of(2023);
        this.color = "Gray";
        this.fuelType = fuelType;
        this.price = price;
        this.seatingCapacity = 4;
        this.mfgCode = mfgCode;
    }

    // Copy constructor
    public Vehicle(Vehicle v) {
        this.brand = v.brand;
        this.model = v.model;
        this.yearOfMfg = v.yearOfMfg;
        this.color = v.color;
        this.fuelType = v.fuelType;
        this.price = v.price;
        this.seatingCapacity = v.seatingCapacity;
        this.mfgCode = v.mfgCode;
        this.noOfServices = v.noOfServices;
    }

    // Getters & setters
    public void setMfgCode(String code) { mfgCode = code; }
    public String getMfgCode() { return mfgCode; }

    public void setNoOfServices(int n) { noOfServices = n; }
    public int getNoOfServices() { return noOfServices; }

    // Required methods
    public void start() {
        System.out.println("Vehicle started.");
    }

    public void drive() {
        System.out.println("Vehicle is moving...");
    }

    public void stop() {
        System.out.println("Vehicle stopped.");
    }

    public void changeSpeed(int newSpeed) {
        System.out.println("Speed changed to " + newSpeed + " kmph");
    }

    public double calcMileage(double fuelAmt, double distance) {
        return distance / fuelAmt;
    }

    // Tabular printer
    public static void printTabular(Vehicle v, double mileage) {
        System.out.printf("%-10s %-10s %-6s %-8s %-5s %-5d %-10.2f %-8.2f %-10s %-5d\n",
                v.brand,
                v.model,
                v.yearOfMfg,
                v.color,
                v.fuelType,
                v.seatingCapacity,
                v.price,
                mileage,
                v.getMfgCode(),
                v.getNoOfServices());
    }
}