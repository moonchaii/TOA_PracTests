package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class MaxSubArray {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int first = Integer.parseInt(br.readLine().trim());
        int currentMax = first;
        int globalMax = first;

        for (int i = 1; i < n; i++) {
            int val = Integer.parseInt(br.readLine().trim());
            currentMax = Math.max(val, currentMax + val);
            globalMax = Math.max(globalMax, currentMax);
        }

        System.out.println(globalMax);
    }
}