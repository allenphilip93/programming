import java.io.InputStreamReader;
import java.io.BufferedReader;

public class Hanoi {

    public static int stepCalculator(int height) {
        if (height <= 1)
            return 1;
        else 
            return 2 * stepCalculator(height-1) + stepCalculator(height-2);
    }

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        try {
            System.out.print("Enter tower height : ");
            int height = Integer.valueOf(buff.readLine());
            System.out.printf("No of steps : %d", stepCalculator(height));
        } catch(Exception e){
            System.out.println("ERROR!");
        }
    }
}