package PracTest3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MinCostClimbStairs {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] cost = new int[n+1];

        for (int i=0; i<n; i++) {
            cost[i] = Integer.parseInt(br.readLine().trim());
        }

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = 0;

        for (int i = 2; i <= n; i++) {
            dp[i] = Math.min(dp[i-1] + cost[i-1], dp[i-2] + cost[i-2]);
        }

        System.out.println(dp[n]);
    }
}
