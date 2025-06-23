import java.io.*;
import java.util.*;
 
public class Grid_Coloring_I {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        char[][] grid = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            String line = br.readLine();
            for (int j = 0; j < m; j++) {
                grid[i][j] = line.charAt(j);
            }
        }
        
        // Check if the grid is 1x1
        if (n == 1 && m == 1) {
            // Only one cell, must change to a different character
            char original = grid[0][0];
            for (char c : new char[]{'A', 'B', 'C', 'D'}) {
                if (c != original) {
                    out.println(c);
                    out.flush();
                    return;
                }
            }
            out.println("IMPOSSIBLE");
            out.flush();
            return;
        }
        
        // For grids larger than 1x1, it's always possible
        char[][] result = new char[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char original = grid[i][j];
                // Try to assign a color different from original and adjacent cells
                for (char c : new char[]{'A', 'B', 'C', 'D'}) {
                    if (c != original) {
                        if (i > 0 && result[i-1][j] == c) continue;
                        if (j > 0 && result[i][j-1] == c) continue;
                        result[i][j] = c;
                        break;
                    }
                }
            }
        }
        
        // Print the result
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                out.print(result[i][j]);
            }
            out.println();
        }
        out.flush();
    }
}
