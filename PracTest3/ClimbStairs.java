package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class ClimbStairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        if (n<=2) {
            System.out.println(n);
        }
        else {
            int[] dp = new int[n+1];
            dp[1] = 1;
            dp[2] = 2;

            for (int i=3; i <=n; i++) {
                dp[i] = dp[i-1] + dp[i-2];
            }
            System.out.println(dp[n]);
        }
    }
}
