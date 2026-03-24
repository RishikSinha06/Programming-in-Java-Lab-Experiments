package Other;
import java.util.Scanner; 
public class ArithmeticOperation { 
    public int num1, num2; 
    public int addNums(int n1, int n2) { 
        return n1 + n2; 
    } 
    public int subtractNums(int n1, int n2) { 
        return n1 - n2; 
    } 
    public int multiplyNums(int n1, int n2) { 
        return n1 * n2; 
    } 
    public float divideNums(int n1, int n2) { 
        return (float) n1 / n2;
    } 
    public int calcMod(int n1, int n2) { 
        return n1 % n2; 
    } 
    public static void main(String[] args) { 
        ArithmeticOperation op = new ArithmeticOperation(); 
        Scanner scan = new Scanner(System.in); 
        System.out.print("Enter First Number: "); 
        op.num1 = scan.nextInt(); 
        System.out.print("Enter Second Number: "); 
        op.num2 = scan.nextInt(); 
        int sum = op.addNums(op.num1, op.num2); 
        int diff = op.subtractNums(op.num1, op.num2); 
        int product = op.multiplyNums(op.num1, op.num2); 
        float division = op.divideNums(op.num1, op.num2); 
        int remainder = op.calcMod(op.num1, op.num2); 
        System.out.println( 
            "Sum: " + sum + 
            " | Difference: " + diff + 
            " | Product: " + product + 
            " | Division: " + division + 
            " | Remainder: " + remainder 
        ); 
        scan.close(); 
    } 
} 