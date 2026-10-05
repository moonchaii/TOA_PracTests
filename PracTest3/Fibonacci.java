package PracTest3;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


public class Fibonacci {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine().trim());

        int[] f = new int[n+1];

        if (n==0) {
            System.out.println(0);
            return;
        }

        if (n==1) {
            System.out.println(1);
            return;
        }

        f[0] = 0;
        f[1] = 1;

        for (int i=2; i<=n; i++) {
            f[i] = f[i-1] + f[i-2];
        }

        System.out.println(f[n]);
    }
}
