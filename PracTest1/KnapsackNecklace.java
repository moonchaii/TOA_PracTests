import java.util.Scanner;

public class KnapsackNecklace {
    // initial results
    private static int bestGold = 0;
    private static int bestSilver = 0;
    private static int bestBronze = 0;
    private static double maxTotalLength = 0.0;
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // max cost, must not go over
        double maxAllowedCost =   scanner.nextDouble();
        // max weight, must not go over
        double maxAllowedWeight = scanner.nextDouble();
        
        // lengths: gold, silver, bronze
        double[] lengths = {
            scanner.nextDouble(),
            scanner.nextDouble(),
            scanner.nextDouble()
        };

        // costs: gold, silver, bronze
        double[] costs = {
            scanner.nextDouble(),
            scanner.nextDouble(),
            scanner.nextDouble()
        };

        // weights: gold, silver, bronze
        double[] weights = {
            scanner.nextDouble(),
            scanner.nextDouble(),
            scanner.nextDouble()
        };

        solveOptimalNecklace(maxAllowedCost, maxAllowedWeight, lengths, costs, weights);
        System.out.println("Gold links: " + bestGold);
        System.out.println("Silver links: " + bestSilver);
        System.out.println("Bronze links: " + bestBronze);
        System.out.println("Maximum Length: " + maxTotalLength);

        scanner.close();
    }

    private static void solveOptimalNecklace(double maxAllowedCost, double maxAllowedWeight, double[] lengths, double[] costs, double[] weights) {
        // max possible number links for each material link
        int maxG = (int) Math.min(maxAllowedCost / costs[0], maxAllowedWeight / weights[0]);
        int maxS = (int) Math.min(maxAllowedCost / costs[1], maxAllowedWeight / weights[1]);
        int maxB = (int) Math.min(maxAllowedCost / costs[2], maxAllowedWeight / weights[2]);

        for (int g = 0; g <= maxG; g++) {
            for (int s = 0; s <= maxS; s++) {
                for (int b = 0; b <= maxB; b++) {
                    double currentCost = g * costs[0] + s * costs[1] + b * costs[2];
                    double currentWeight = g * weights[0] + s * weights[1] + b * weights[2];
                    
                    // check cost and weight constraints
                    if (currentCost <= maxAllowedCost && currentWeight <= maxAllowedWeight) {
                        double currentLength = g * lengths[0] + s * lengths[1] + b * lengths[2];
                        
                        // update best combination
                        if (currentLength > maxTotalLength) {
                            maxTotalLength = currentLength;
                            bestGold = g;
                            bestSilver = s;
                            bestBronze = b;
                        }
                    }
                }
            }
        }
    }

}
