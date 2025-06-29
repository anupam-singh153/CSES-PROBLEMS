import java.io.*;
import java.util.*;

public class MinimalGridPath {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        char[][] grid = new char[n][n];
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < n; j++) {
                grid[i][j] = line.charAt(j);
            }
        }

        boolean[][] reachable = new boolean[n][n];
        reachable[0][0] = true;
        StringBuilder result = new StringBuilder();

        for (int s = 0; s < 2 * n - 1; s++) {
            // Find the minimal character in the current diagonal
            char minChar = 'Z';
            for (int i = 0; i < n; i++) {
                int j = s - i;
                if (j >= 0 && j < n && reachable[i][j]) {
                    if (grid[i][j] < minChar) {
                        minChar = grid[i][j];
                    }
                }
            }
            result.append(minChar);

            // Mark the next reachable cells
            boolean[][] nextReachable = new boolean[n][n];
            for (int i = 0; i < n; i++) {
                int j = s - i;
                if (j >= 0 && j < n && reachable[i][j] && grid[i][j] == minChar) {
                    if (i + 1 < n) {
                        nextReachable[i + 1][j] = true;
                    }
                    if (j + 1 < n) {
                        nextReachable[i][j + 1] = true;
                    }
                }
            }
            reachable = nextReachable;
        }

        System.out.println(result.toString());
    }
}