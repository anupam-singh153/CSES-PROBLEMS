import java.io.*;
import java.util.*;

public class StringFunctions {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String s = br.readLine();
        int n = s.length();

        // Compute Z-function
        int[] Z = new int[n];
        int L = 0, R = 0;
        for (int i = 1; i < n; i++) {
            if (i > R) {
                L = R = i;
                while (R < n && s.charAt(R - L) == s.charAt(R)) {
                    R++;
                }
                Z[i] = R - L;
                R--;
            } else {
                int k = i - L;
                if (Z[k] < R - i + 1) {
                    Z[i] = Z[k];
                } else {
                    L = i;
                    while (R < n && s.charAt(R - L) == s.charAt(R)) {
                        R++;
                    }
                    Z[i] = R - L;
                    R--;
                }
            }
        }
        Z[0] = 0; // As per problem statement, z(1) = 0 (assuming 1-based to 0-based conversion)

        // Compute π-function
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

        // Output Z-function
        for (int i = 0; i < n; i++) {
            pw.print(Z[i] + " ");
        }
        pw.println();

        // Output π-function
        for (int i = 0; i < n; i++) {
            pw.print(pi[i] + " ");
        }
        pw.println();

        pw.flush();
    }
}