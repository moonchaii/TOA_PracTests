package PracTest2;

import java.util.Scanner;

public class Dividing {

    private static boolean canFormKPlanks(long[] lengths, long M, long K) {
        long totalPlanks = 0;
        for (long len : lengths) {
            totalPlanks += len / M;
            if (totalPlanks >= K) {
                return true;
            }
        }
        return totalPlanks >= K;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        long[] lengths = new long[N];
        for (int i = 0; i < N; i++) {
            lengths[i] = scanner.nextLong();
        }
        long K = scanner.nextLong();

        scanner.close();

        long low = 1;
        long high = 10000000;
        long maxM = 0;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (canFormKPlanks(lengths, mid, K)) {
                maxM = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(maxM);
    }
}
