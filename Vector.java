// Custom exception class to handle vector dimension mismatches
class VectorException extends Exception {
    public VectorException(String message) {
        super(message);
    }
}

public class Vector {
    public double[] components;

    // Constructor
    public Vector(double[] newComponents) throws VectorException {
        if (newComponents == null || newComponents.length == 0) {
            throw new VectorException("Vector cannot be null or empty.");
        }
        // STRICT DIMENSION CHECK: Only allow 2D or 3D vectors
        if (newComponents.length != 2 && newComponents.length != 3) {
            throw new VectorException("Vector must be strictly 2D or 3D. Invalid dimension provided: " + newComponents.length);
        }
        
        // Make a copy of the array so it cannot be modified outside the class
        this.components = new double[newComponents.length];
        System.arraycopy(newComponents, 0, this.components, 0, newComponents.length);
    }

    // Adds another vector to this vector and returns the result as a new Vector
    public Vector add(Vector other) throws VectorException {
        checkLength(other);
        double[] result = new double[this.components.length];
        
        for (int i = 0; i < this.components.length; i++) {
            result[i] = this.components[i] + other.components[i];
        }
        return new Vector(result);
    }

    // Subtracts another vector from this vector and returns the result as a new Vector
    public Vector subtract(Vector other) throws VectorException {
        checkLength(other);
        double[] result = new double[this.components.length];
        
        for (int i = 0; i < this.components.length; i++) {
            result[i] = this.components[i] - other.components[i];
        }
        return new Vector(result);
    }

    // Computes the dot product of this vector and another vector
    public double dotProduct(Vector v) throws VectorException {
        checkLength(v);
        double result = 0.0;
        
        for (int i = 0; i < this.components.length; i++) {
            result += this.components[i] * v.components[i];
        }
        return result;
    }

    // Helper method to ensure vectors are of the same size before doing math
    private void checkLength(Vector other) throws VectorException {
        if (other == null) {
            throw new VectorException("The vector to operate on cannot be null.");
        }
        if (this.components.length != other.components.length) {
            throw new VectorException("Dimension Mismatch: Cannot perform operation between a " 
                + this.components.length + "D vector and a " + other.components.length + "D vector.");
        }
    }

    // Prints the components of a given vector
    public static void printVector(Vector v) {
        if (v == null || v.components == null) {
            System.out.println("null");
            return;
        }
        System.out.print("[");
        for (int i = 0; i < v.components.length; i++) {
            System.out.print(v.components[i]);
            if (i < v.components.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}