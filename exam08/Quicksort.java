import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class Quicksort {

    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner sc = new Scanner(System.in);

        // read in input
        System.out.println("Enter your input array (seperated by spaces):");
        String sArr = sc.nextLine();
        int[] input = Arrays.stream(sArr.split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
        System.out.println("Input array: " + Utils.stringify(input));
        sort(input);
        System.out.println("Sorted array: " + Utils.stringify(input));
    }

    static void sort(int[] arr) {
        sort(arr, 0, arr.length-1);
    }

    static void sort(int[] arr, int left, int right) {
        if (left < right) {
            int m = arr[left];
            int i = left, j = right;
            while (i <= j) {
                while (arr[i] < m) i++;
                while (arr[j] > m) j--;
                if (i <= j) {
                    if (i != j) {
                        // swap arr[i] and arr[j] in place
                        arr[i] = arr[i] + arr[j];
                        arr[j] = arr[i] - arr[j];
                        arr[i] = arr[i] - arr[j];
                    }

                    i++;
                    j--;
                }
            }
            sort(arr, left, j);
            sort(arr, i, right);
        }
    }
    
    public final class Utils {
        private Utils() {  }

        public static String stringify(int[] arr) {
            String out = "[";
            for (int i = 0; i < arr.length; i++) {
                out += arr[i];
                if (i < arr.length - 1) out += ", ";
            }
            out += "]";
            return out;
        }
    }

}
