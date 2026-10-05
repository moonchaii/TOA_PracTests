package PracTest3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class CyclingReader {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int currentMax = 0;
        int globalMax = 0;

        for (int i = 0; i < n; i++) {
            int p = Integer.parseInt(br.readLine().trim());
            currentMax = Math.max(0, currentMax + p);
            globalMax = Math.max(globalMax, currentMax);
        }

        System.out.println(globalMax);
    }
} 
