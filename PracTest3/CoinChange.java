package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

public class CoinChange {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numCoins = Integer.parseInt(br.readLine().trim());
        int[] coins = new int[numCoins];

        for (int i = 0; i < numCoins; i++) {
            coins[i] = Integer.parseInt(br.readLine().trim());
        }

        int amount = Integer.parseInt(br.readLine().trim());

        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);

        dp[0] = 0;

        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {
                if (i - coin >= 0) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }

        if (dp[amount] > amount) {
            System.out.println(-1);
        } else {
            System.out.println(dp[amount]);
        }
    }
}