import java.util.Scanner; 
public class calculator{ 
    public int addNums(int n1, int n2){ 
        return n1 + n2; 
    } 
    public int subtractNums(int n1, int n2){ 
        return n1 - n2; 
    } 
    public int multiplyNums(int n1, int n2){ 
        return n1*n2; 
    } 
    public float divideNums(int n1, int n2){ 
        return (float) n1/n2; 
    } 
    public int calcMod(int n1, int n2){ 
        return n1%n2; 
    } 
    public static void main(String[] args){ 
    calculator c = new calculator(); 
    Scanner scan = new Scanner(System.in); 
    int op; // declare op here 
    do { 
        System.out.println(" Choose Operation: "); 
        System.out.println(" 1. Addition "); 
        System.out.println(" 2. Subtraction "); 
        System.out.println(" 3. Product "); 
        System.out.println(" 4. Division "); 
        System.out.println(" 5. Remainder "); 
        System.out.println(" 0. Exit "); // it's good to show an exit option 
        op = scan.nextInt(); // read the choice 
        if(op == 0){ 
            System.out.println("Exiting Calculator. Goodbye!"); 
            break; 
        } 
        System.out.print("Enter first number: "); 
        int num1 = scan.nextInt(); 
        System.out.print("Enter second number: "); 
        int num2 = scan.nextInt(); 
        switch(op){ 
            case 1: 
                System.out.println("Sum = " + c.addNums(num1, num2)); 
                break; 
            case 2: 
                System.out.println("Diff = " + c.subtractNums(num1, num2)); 
                break; 
            case 3: 
                System.out.println("Product = " + c.multiplyNums(num1, num2)); 
                break; 
            case 4: 
                if(num2 != 0){ 
                    System.out.println("Division = " + c.divideNums(num1, num2)); 
                } else { 
                    System.out.println("Cannot divide by zero!"); 
                } 
                break; 
            case 5: 
                if(num2 != 0){ 
                    System.out.println("Remainder = " + c.calcMod(num1, num2)); 
                } else { 
                    System.out.println("Cannot divide by zero!"); 
                } 
                break; 
            default: 
                System.out.println("Invalid Choice!!"); 
        } 
    } while(op != 0); 
    scan.close();  
}
} 