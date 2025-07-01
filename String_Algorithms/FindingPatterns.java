import java.io.*;
import java.util.*;

public class FindingPatterns {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String[] patterns = new String[n];
        for (int i = 0; i < n; i++) {
            patterns[i] = br.readLine();
        }
        for (String pattern : patterns) {
            if (text.contains(pattern)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        }
    }
}
