public class VectorOperations {

    public static void main(String[] args) {
        System.out.println("=== Starting Vector Operations ===");

        // 1. VALID MATH OPERATIONS (2D & 3D)

        try {
            System.out.println("\n--- 3D Vectors ---");
            Vector v3d_A = new Vector(new double[]{1.0, 2.0, 3.0});
            Vector v3d_B = new Vector(new double[]{4.0, 5.0, 6.0});

            System.out.print("Vector A (3D): "); Vector.printVector(v3d_A);
            System.out.print("Vector B (3D): "); Vector.printVector(v3d_B);

            Vector.printVector(v3d_A.add(v3d_B));      // Addition
            Vector.printVector(v3d_B.subtract(v3d_A)); // Subtraction
            System.out.println("Dot Product: " + v3d_A.dotProduct(v3d_B));


            System.out.println("\n--- 2D Vectors ---");
            Vector v2d_X = new Vector(new double[]{10.0, 20.0});
            Vector v2d_Y = new Vector(new double[]{5.0, 2.0});

            System.out.print("Vector X (2D): "); Vector.printVector(v2d_X);
            System.out.print("Vector Y (2D): "); Vector.printVector(v2d_Y);

            Vector.printVector(v2d_X.add(v2d_Y));      // Addition
            Vector.printVector(v2d_X.subtract(v2d_Y)); // Subtraction
            System.out.println("Dot Product: " + v2d_X.dotProduct(v2d_Y));

        } catch (VectorException e) {
            System.err.println("Unexpected Math Error: " + e.getMessage());
        }

        // 2. TESTING EXCEPTIONS (1D, 4D, and Mismatch)

        System.out.println("\n--- Testing Exception Handling ---");

        // Test 1: Trying to make a 1D Vector
        try {
            System.out.println("Attempting to create a 1D Vector...");
            Vector invalid1D = new Vector(new double[]{5.0});
        } catch (VectorException e) {
            System.err.println("Caught Error -> " + e.getMessage());
        }

        // Test 2: Trying to make a 4D Vector
        try {
            System.out.println("\nAttempting to create a 4D Vector...");
            Vector invalid4D = new Vector(new double[]{1.0, 2.0, 3.0, 4.0});
        } catch (VectorException e) {
            System.err.println("Caught Error -> " + e.getMessage());
        }

        // Test 3: Trying to add 3D and 2D
        try {
            System.out.println("\nAttempting to add a 3D vector to a 2D vector...");
            Vector v3d = new Vector(new double[]{1.0, 2.0, 3.0});
            Vector v2d = new Vector(new double[]{10.0, 20.0});
            
            v3d.add(v2d); // This triggers the mismatch error

        } catch (VectorException e) {
            System.err.println("Caught Error -> " + e.getMessage());
        }
        
        System.out.println("\n=== Program Finished ===");
    }
}