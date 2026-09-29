package PracTest2;

import java.util.Scanner;

public class Pricing {

    private static long computeF(long K) {
        long sum = 0;
        for (long j = 1; j < K; j++) {
            sum += j * (K / j);
        }
        return sum;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long N = scanner.nextLong();

        scanner.close();

        long low = 1;
        long high = 1000000;
        long ans = 1;

        while (low <= high) {
            long mid = low + (high - low) / 2;
            long cost = computeF(mid);

            if (cost <= N) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(ans);
    }
}