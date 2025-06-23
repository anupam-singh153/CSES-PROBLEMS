import java.io.*;
import java.util.*;
 
public class Mex_Grid_Construction {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int[][] grid = new int[n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Set<Integer> seen = new HashSet<>();
                // Check left in the same row
                for (int k = 0; k < j; k++) {
                    seen.add(grid[i][k]);
                }
                // Check above in the same column
                for (int k = 0; k < i; k++) {
                    seen.add(grid[k][j]);
                }
                // Find the mex
                int mex = 0;
                while (seen.contains(mex)) {
                    mex++;
                }
                grid[i][j] = mex;
            }
        }
        
        // Print the grid
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                out.print(grid[i][j]);
                if (j < n - 1) {
                    out.print(" ");
                }
            }
            out.println();
        }
        out.flush();
    }
}
