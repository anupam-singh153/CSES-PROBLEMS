import java.io.*;
import java.util.*;

public class FindingPeriods {
    static int[] computePrefix(String s) {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j)) {
                j = pi[j - 1];
            }
            if (s.charAt(i) == s.charAt(j)) {
                j++;
            }
            pi[i] = j;
        }
        return pi;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int n = s.length();
        int[] pi = computePrefix(s);

        List<Integer> periods = new ArrayList<>();
        int k = pi[n - 1];
        while (k > 0) {
            periods.add(n - k);
            k = pi[k - 1];
        }
        periods.add(n); // The whole string is always a period of itself

        Collections.sort(periods);

        for (int p : periods) {
            System.out.print(p + " ");
        }
        System.out.println();
    }
}
