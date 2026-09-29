package PracTest2;

import java.util.Scanner;

public class Inversions {

    private static long mergeAndCount(int[] arr, int[] temp, int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int k = low;
        long inversions = 0;

        while (i <= mid && j <= high) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                temp[k++] = arr[j++];
                inversions += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= high) {
            temp[k++] = arr[j++];
        }

        for (i = low; i <= high; i++) {
            arr[i] = temp[i];
        }

        return inversions;
    }

    private static long countInversions(int[] arr, int[] temp, int low, int high) {
        long inversions = 0;

        if (low < high) {
            int mid = low + (high - low) / 2;

            inversions += countInversions(arr, temp, low, mid);
            inversions += countInversions(arr, temp, mid + 1, high);

            inversions += mergeAndCount(arr, temp, low, mid, high);
        }

        return inversions;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int N = scanner.nextInt();
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = scanner.nextInt();
        }
        scanner.close();

        int[] temp = new int[N];
        long totalInversions = countInversions(arr, temp, 0, N - 1);

        System.out.println(totalInversions);
    }
}
