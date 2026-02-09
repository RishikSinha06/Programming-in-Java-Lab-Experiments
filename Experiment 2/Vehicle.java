public class Vehicle {

    // Public attributes
    public String make;
    public String variant;
    public java.time.Year manufactureYear;
    public String bodyColor;
    public char fuelCategory;   // E = Electric, P = Petrol, D = Diesel, C = CNG
    public double cost;
    public int seatCount;

    // Private attributes
    private String manufacturingCode;
    private int serviceCount;

    // Default constructor
    public Vehicle() {
        make = "Skoda";
        variant = "Octavia";
        manufactureYear = java.time.Year.of(2026);
        bodyColor = "Blue";
        fuelCategory = 'P';
        seatCount = 5;
        cost = 2650000;
    }

    // Parameterized constructor (basic details)
    public Vehicle(String make, String variant, double cost, String bodyColor) {
        this.make = make;
        this.variant = variant;
        this.cost = cost;
        this.bodyColor = bodyColor;
        this.manufactureYear = java.time.Year.of(2025);
        this.fuelCategory = 'P';
        this.seatCount = 5;
    }

    // Parameterized constructor (fuel + manufacturing info)
    public Vehicle(char fuelCategory, double cost, String manufacturingCode) {
        this.make = "Kia";
        this.variant = "Seltos";
        this.manufactureYear = java.time.Year.of(2024);
        this.bodyColor = "Black";
        this.fuelCategory = fuelCategory;
        this.cost = cost;
        this.seatCount = 5;
        this.manufacturingCode = manufacturingCode;
    }

    // Copy constructor
    public Vehicle(Vehicle other) {
        this.make = other.make;
        this.variant = other.variant;
        this.manufactureYear = other.manufactureYear;
        this.bodyColor = other.bodyColor;
        this.fuelCategory = other.fuelCategory;
        this.cost = other.cost;
        this.seatCount = other.seatCount;
        this.manufacturingCode = other.manufacturingCode;
        this.serviceCount = other.serviceCount;
    }

    // Getter & Setter methods
    public void setManufacturingCode(String code) {
        manufacturingCode = code;
    }

    public String getManufacturingCode() {
        return manufacturingCode;
    }

    public void setServiceCount(int count) {
        serviceCount = count;
    }

    public int getServiceCount() {
        return serviceCount;
    }

    // Core vehicle behaviors
    public void start() {
        System.out.println("Ignition turned ON.");
    }

    public void drive() {
        System.out.println("Vehicle in motion.");
    }

    public void stop() {
        System.out.println("Ignition turned OFF.");
    }

    public void changeSpeed(int speed) {
        System.out.println("Current speed set to " + speed + " kmph");
    }

    public double calcMileage(double fuelUsed, double distanceCovered) {
        return distanceCovered / fuelUsed;
    }

    // Static method for tabular output
    public static void printTabular(Vehicle v, double mileage) {
        System.out.printf(
                "%-10s %-10s %-6s %-8s %-5s %-5d %-10.2f %-8.2f %-10s %-5d%n",
                v.make,
                v.variant,
                v.manufactureYear,
                v.bodyColor,
                v.fuelCategory,
                v.seatCount,
                v.cost,
                mileage,
                v.getManufacturingCode(),
                v.getServiceCount()
        );
    }
}
