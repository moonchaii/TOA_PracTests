import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Permutation {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // read how many numbers are in the array
        System.out.print("Enter the number of elements: ");
        int n = scanner.nextInt();

        // read the elements into the array
        int[] nums = new int[n];
        System.out.println("Enter " + n + " integers separated by spaces:");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        // generate permutations
        Permutation p = new Permutation();
        List<List<Integer>> result = p.permute(nums);

        // print output
        System.out.println("\nAll Permutations:");
        System.out.println(result);

        scanner.close();
    }

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] used = new boolean[nums.length];
        backtrack(nums, used, new ArrayList<>(), result);
        return result;
    }

    private void backtrack(int[] nums, boolean[] used, List<Integer> current, List<List<Integer>> result) {
        // base case: current permutation is full length
        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current)); // save a snapshot copy
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) continue; // skip numbers already picked in this path

            // choose: pick nums[i]
            used[i] = true;
            current.add(nums[i]);

            // explore: fill the rest of the permutation recursively
            backtrack(nums, used, current, result);

            // un-choose (backtrack): remove nums[i] so other branches can use it
            current.remove(current.size() - 1);
            used[i] = false;
        }
    }
}