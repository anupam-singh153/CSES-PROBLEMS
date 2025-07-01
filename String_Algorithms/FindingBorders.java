import java.io.*;
import java.util.*;

public class FindingBorders {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        String s = br.readLine();
        int[] pi = prefix(s);

        List<Integer> borders = new ArrayList<>();
        int j = pi[s.length() - 1];
        while (j > 0) {
            borders.add(j);
            j = pi[j - 1];
        }

        Collections.reverse(borders);
        for (int len : borders) {
            pw.print(len + " ");
        }
        pw.println();
        pw.flush();
    }

    static int[] prefix(String s) {
        int n = s.length();
        int[] pi = new int[n];
        for (int i = 1; i < n; i++) {
            int j = pi[i - 1];
            while (j > 0 && s.charAt(i) != s.charAt(j))
                j = pi[j - 1];
            if (s.charAt(i) == s.charAt(j)) j++;
            pi[i] = j;
        }
        return pi;
    }
}
