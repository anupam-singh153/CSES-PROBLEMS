import java.io.*;
import java.util.*;

public class MinimalRotation {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        String doubled = s + s;
        int n = s.length();
        int[] f = new int[2 * n];
        Arrays.fill(f, -1);
        int k = 0;
        for (int j = 1; j < 2 * n; j++) {
            int i = f[j - k - 1];
            while (i != -1 && doubled.charAt(j) != doubled.charAt(k + i + 1)) {
                if (doubled.charAt(j) < doubled.charAt(k + i + 1)) {
                    k = j - i - 1;
                }
                i = f[i];
            }
            if (doubled.charAt(j) != doubled.charAt(k + i + 1)) {
                if (doubled.charAt(j) < doubled.charAt(k)) {
                    k = j;
                }
                f[j - k] = -1;
            } else {
                f[j - k] = i + 1;
            }
        }
        System.out.println(doubled.substring(k, k + n));
    }
}