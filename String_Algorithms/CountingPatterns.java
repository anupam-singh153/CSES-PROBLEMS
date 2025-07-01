import java.io.*;
import java.util.*;

public class CountingPatterns {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        int n = Integer.parseInt(br.readLine());
        String[] patterns = new String[n];
        for (int i = 0; i < n; i++) {
            patterns[i] = br.readLine();
        }
        for (String pattern : patterns) {
            int count = 0;
            int index = text.indexOf(pattern);
            while (index != -1) {
                count++;
                index = text.indexOf(pattern, index + 1);
            }
            System.out.println(count);
        }
    }
}
