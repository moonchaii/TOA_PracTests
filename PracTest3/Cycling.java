package PracTest3;
import java.util.Scanner;

public class Cycling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        int currentMax = 0;
        int globalMax = 0;

        for (int i = 0; i < n; i++) {
            int p = scanner.nextInt();
            currentMax = Math.max(0, currentMax + p);
            globalMax = Math.max(globalMax, currentMax);
        }
        
        System.out.println(globalMax);
        scanner.close();
    }
}
