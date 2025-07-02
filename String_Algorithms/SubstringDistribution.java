import java.io.*;
import java.util.*;

public class SubstringDistribution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        String s = br.readLine();
        int n = s.length();

        // To store the number of distinct substrings for each length
        int[] distinctCounts = new int[n + 1];

        // To store all distinct substrings for each length
        Map<Integer, Set<String>> substringsByLength = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            substringsByLength.put(i, new HashSet<>());
        }

        // Generate all possible substrings
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n && j - i <= n; j++) {
                String substring = s.substring(i, j);
                int length = substring.length();
                substringsByLength.get(length).add(substring);
            }
        }

        // Count distinct substrings for each length
        for (int i = 1; i <= n; i++) {
            distinctCounts[i] = substringsByLength.get(i).size();
        }

        // Print the results
        for (int i = 1; i <= n; i++) {
            pw.print(distinctCounts[i] + " ");
        }
        pw.flush();
    }
}