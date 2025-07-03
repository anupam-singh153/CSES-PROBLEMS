import java.io.*;
import java.util.*;

public class StringReorder {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        String s = br.readLine();
        int n = s.length();
        int[] freq = new int[26];

        for (char c : s.toCharArray()) {
            freq[c - 'A']++;
        }

        int maxFreq = 0;
        for (int f : freq) {
            maxFreq = Math.max(maxFreq, f);
        }

        if (maxFreq > (n + 1) / 2) {
            out.println(-1);
            out.close();
            return;
        }

        StringBuilder result = new StringBuilder();
        char prev = 0;

        for (int i = 0; i < n; i++) {
            boolean found = false;
            for (int j = 0; j < 26; j++) {
                if (freq[j] == 0) continue;
                char current = (char)(j + 'A');
                if (current == prev) continue;

                // Try picking this character
                freq[j]--;
                int remaining = n - i - 1;

                int maxF = 0;
                for (int k = 0; k < 26; k++) {
                    maxF = Math.max(maxF, freq[k]);
                }

                if (maxF <= (remaining + 1) / 2) {
                    result.append(current);
                    prev = current;
                    found = true;
                    break;
                } else {
                    freq[j]++; // put back, try next
                }
            }

            if (!found) {
                out.println(-1);
                out.close();
                return;
            }
        }

        out.println(result.toString());
        out.close();
    }
}
