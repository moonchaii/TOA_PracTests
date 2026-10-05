package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class HouseRobber2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());
        int[] houses = new int[n];

        for (int i = 0; i < n; i++) {
            houses[i] = Integer.parseInt(br.readLine().trim());
        }

        if (n == 1) {
            System.out.println(houses[0]);
            return;
        }

        // option a: rob from index 0 to n - 2
        int optionA = robLinear(houses, 0, n - 2);

        // option b: rob from index 1 to n - 1
        int optionB = robLinear(houses, 1, n - 1);

        System.out.println(Math.max(optionA, optionB));
    }

    private static int robLinear(int[] nums, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int current = Math.max(prev1, prev2 + nums[i]);
            prev2 = prev1;
            prev1 = current;
        }

        return prev1;
    }
}