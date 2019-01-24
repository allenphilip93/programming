import java.util.ArrayList;
import java.util.List;

public class Lambda {

    public static void main(String[] args) {
        // Operation<Integer> sub = (a,b) -> a-b;
        Operation<Double> sum = new Operation<>((a,b) -> (a+b));
        double a = 10;
        double b = 5;
        System.out.println("Sum of the two nos is : " + sum.run(a, b));
        // System.out.println("Difference of the two nos is : " + sub.run(10, 5));

        // List arr = new ArrayList();
        // arr.add(1);
        // arr.add(3);
        // arr.add(5);
        // arr.add(7);
        // arr.forEach(n -> System.out.println(n));
    }
}

abstract class Operation<T> {
    abstract T run(T a, T b);
}