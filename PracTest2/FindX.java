package PracTest2;
/* Question 1 used doubles. 
They give you f(x) as input and you need to output x. 
The formula is f(x) = 1^x + 3^x + 3^x + 7^x. 
You basically have to use a lower and upper bound until you get a value of x that gets f(x) (100 marks) 
bounds = 4 < f(x) < 1000000*/

import java.util.Scanner;

public class FindX {

    // Function f(x) = 1^x + 3^x + 3^x + 7^x = 1 + 2 * (3^x) + 7^x
    public static double computeFx(double x) {
        return Math.pow(1, x) + 2 * Math.pow(3, x) + Math.pow(7, x);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double targetFx = scanner.nextDouble();
        scanner.close();

        double low = 0.0;
        double high = 10.0;

        for (int i = 0; i < 100; i++) {
            double mid = low + (high - low) / 2.0;
            double currentFx = computeFx(mid);

            if (currentFx < targetFx) {
                low = mid;
            } else {
                high = mid;
            }
        }

        System.out.println(low);
    }
}