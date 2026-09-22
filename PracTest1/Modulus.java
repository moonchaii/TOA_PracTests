import java.util.Scanner;

public class Modulus {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n, z;
        n = scanner.nextInt();
        z = scanner.nextInt();

        int count = 0;

        for (int x = 1; x < n; x++) {
            for (int y = 1; y < n; y++) {
                if ((x * y) % n == z) {
                    count++;
                }
            }
        }
        scanner.close();
        System.out.println(count);
    }
}