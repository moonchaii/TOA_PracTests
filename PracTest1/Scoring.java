import java.util.Scanner;

public class Scoring {
    static int maxScore = 1;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int currentScore = 1;
        int N = scanner.nextInt(); // number of inputs

        int[] inputs = new int[N];
        for (int i = 0; i < N; i++) {
            inputs[i] = scanner.nextInt();
        }

        int T = scanner.nextInt(); // target score

        calcScore(0, currentScore, inputs, N, T);
        System.out.println(maxScore);
    }

    public static void calcScore(int index, int currentScore, int[] inputs, int N, int T) {
        if(index == N)
        {
            if (currentScore < T && currentScore > maxScore) {
                maxScore = currentScore;
            }
            return;
        }

        // try adding
        calcScore(index + 1, currentScore + inputs[index], inputs, N, T);

        // try multiplying
        calcScore(index + 1, currentScore * inputs[index], inputs, N, T);
    }
}
