import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BiggestBlock {

    public static int count(int[][] arr, int x, int y) {
        return 0;
    }

    public static void main(String[] args) {
        BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
        System.out.print("Enter the size : ");
        try {
            int size = Integer.valueOf(buff.readLine().trim());
            System.out.println("Enter the grid : ");
            int count = 0;
            int[][] grid = new int[size][size];
            while (count < size) {
                String[] arr = buff.readLine().split("\\s+");
                for (int idx=0; idx < size; idx++){
                    grid[count][idx] = Integer.valueOf(arr[idx]);
                }
                ++count;
            }
            System.out.println("Largest block size is : " + count(grid, 0, 0));
        } catch (Exception e) {
            System.out.println("ERROR!");
        }
    }
}