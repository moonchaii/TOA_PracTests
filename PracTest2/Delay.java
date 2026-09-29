/* Question 2 was they give you earliest and latest flight times for planes. 
You have to work out the biggest possible delay time between flights. 
That one actually took me longer to figure out even though it's only 50 marks. 
You basically make 2 parallel arrays (earliest and latest). 
You simulate the flight times and see if the delay is too big and causes problems or 
if it works but there's a bigger answer that works too 
0 < E, L < 10^(18)*/

package PracTest2;

import java.util.Scanner;

public class Delay {

    private static boolean canScheduleWithDelay(long[] E, long[] L, long D) {
        long lastLandTime = E[0];

        for (int i = 1; i < E.length; i++) {
            long earliestPossible = lastLandTime + D;

            if (earliestPossible > L[i]) {
                return false;
            }

            lastLandTime = Math.max(E[i], earliestPossible);
        }

        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        long[] E = new long[N];
        long[] L = new long[N];

        for (int i = 0; i < N; i++) {
            E[i] = scanner.nextLong();
            L[i] = scanner.nextLong();
        }
        scanner.close();

        long low = 0;
        long high = 1000000000000000000L;
        long maxDelay = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canScheduleWithDelay(E, L, mid)) {
                maxDelay = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(maxDelay);
    }
}