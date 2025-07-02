import java.io.*;
import java.util.*;

public class SubstringOrderI {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        int k = Integer.parseInt(br.readLine());

        TreeSet<String> substrings = new TreeSet<>();
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n && j - i <= 100; j++) { // Limit length to prevent excessive memory usage
                substrings.add(s.substring(i, j));
            }
        }

        List<String> sortedSubstrings = new ArrayList<>(substrings);
        if (k <= sortedSubstrings.size()) {
            System.out.println(sortedSubstrings.get(k - 1));
        } else {
            System.out.println(-1); // Should not happen per problem statement
        }
    }
}