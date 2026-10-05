package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Path {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int k = Integer.parseInt(br.readLine().trim());

        boolean[][] obstacle = new boolean[n+1][n+1];

        for (int i=0; i<k; i++) {
            String[] parts = br.readLine().trim().split(" ");
            int x = Integer.parseInt(parts[0]);
            int y = Integer.parseInt(parts[1]);
            obstacle[x][y] = true;
        }

        int[][] dp = new int[n+1][n+1];

        if (!obstacle[1][1]) {
            dp[1][1] = 1;
        }

        for (int x=1; x<=n; x++) {
            for (int y=1; y<=n; y++) {
                if (x==1 && y==1) continue;

                if (obstacle[x][y]) {
                    dp[x][y] = 0;
                } else {
                    dp[x][y] = dp[x-1][y] + dp[x][y-1];
                }
            }
        }

        System.out.println(dp[n][n]);
    }
}
