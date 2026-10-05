package PracTest3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HouseRobber {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] houses = new int[n];

        for (int i=0; i<n; i++) {
            houses[i] = Integer.parseInt(br.readLine().trim());
        }

        int[] dp = new int[n+1];

        dp[0] = 0;
        dp[1] = houses[0];

        for (int i=2; i<=n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + houses[i-1]);
        }

        System.out.println(dp[n]);
    }
}
