package PracTest2;

import java.util.Scanner;

public class Factory {
    
    private static boolean isValid(int[] times, long P, long time) {
        long itemsProduced = 0;
        
        for (int t : times) {
            itemsProduced += time / t;
            
            if (itemsProduced >= P) {
                return true;
            }
        }
        
        return itemsProduced >= P;
    } 

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] times = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = scanner.nextInt();
        }

        long P = scanner.nextLong();
        scanner.close();

        long low = 1;
        long high = 1000000000000000000L;
        long ans = high;

        while (low <= high) {
            long mid = low + (high - low) / 2;

            if (isValid(times, P, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        System.out.println(ans);
    }
}