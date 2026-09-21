import java.util.Scanner;

public class NeedleHaystack {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String haystack = scanner.nextLine();
        String needle = scanner.nextLine();

        int result = findNeedle(haystack, needle);
        System.out.println(result);
        
        scanner.close();
    }

    public static int findNeedle(String haystack, String needle) {
        // edge cases
        if (needle.isEmpty()) return 0;
        if (haystack.length() < needle.length()) return -1;

        // outer loop stops where needle can no longer fit in haystack
        for (int i = 0; i <= haystack.length() - needle.length(); i++) {
            for (int j = 0; j < needle.length(); j++) {
                // compare characters from haystack and needle
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    break; // mismatch found
                }
                
                // if every char in needle matches, return i
                if (j == needle.length() - 1) {
                    return i;
                }
            }
        }
        
        return -1;
    }
}
