package PracTest3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Tribonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        if (n==0) {
            System.out.println(0);
            return;
        }

        if (n==1 || n==2) {
            System.out.println(1);
            return;
        }

        int[] t = new int[n+1];

        t[0] = 0;
        t[1] = 1;
        t[2] = 1;

        for (int i=3; i<=n; i++) {
            t[i] = t[i-1] + t[i-2] + t[i-3];
        }

        System.out.println(t[n]);
    }
}
