import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Recursion {

    static double fact(int n) {
        if (n == 0)
            return 1;
        else 
            return n * fact(n-1);
    }
    public static void main(String[] args){
        InputStreamReader reader = new InputStreamReader(System.in);
        BufferedReader buffReader = new BufferedReader(reader);
        System.out.print("Enter number : ");
        try {
            int n = Integer.valueOf(buffReader.readLine());
            System.out.println("Number : " + n);
            System.out.printf("Factorial of %d is %f", n, fact(n));
        } catch (Exception e) {
            System.out.println("ERROR!!");
        }
    }
}