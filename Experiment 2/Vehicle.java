public class Vehicle {

    // Public data members
    public String brandName;
    public String modelName;
    public float price;
    public char fuelType;     // P = Petrol, D = Diesel, C = CNG
    public int seats;

    // Private data members
    private String mfgCode;
    private int noOfServices;

    /* ---------- Getter & Setter Methods ---------- */

    public void setMfgCode(String mfgCode) {
        this.mfgCode = mfgCode;
    }

    public String getMfgCode() {
        return mfgCode;
    }

    public void setNoOfServices(int noOfServices) {
        this.noOfServices = noOfServices;
    }

    public int getNoOfServices() {
        return noOfServices;
    }

    /* ---------- Constructors ---------- */

    // 1. Default constructor
    public Vehicle() {
        brandName = "MG";
        modelName = "Hector";
        price = 115000.45f;
        fuelType = 'P';
        seats = 5;
    }

    // 2. Parameterized constructor
    public Vehicle(String brandName, String modelName, float price, char fuelType) {
        this.brandName = brandName;
        this.modelName = modelName;
        this.price = price;
        this.fuelType = fuelType;
        this.seats = 5;
    }

    // 3. Copy constructor
    public Vehicle(Vehicle v) {
        this.brandName = v.brandName;
        this.modelName = v.modelName;
        this.price = v.price;
        this.fuelType = v.fuelType;
        this.seats = v.seats;
    }

    /* ---------- Methods ---------- */

    public void start() {
        System.out.println("Start Ignition by pressing the button");
    }

    public void stop() {
        System.out.println("Vehicle stopped");
    }

    public void drive() {
        System.out.println("Vehicle is moving");
    }

    public float calcMileage(float fuel, float distance) {
        return distance / fuel;
    }

    public int changeSpeed(int currentSpeed) {
        return currentSpeed + 20;
    }

    public float getMileage() {
        if (fuelType == 'D') {
            return calcMileage(50, 500);
        } else {
            return calcMileage(40, 500);
        }
    }
}
