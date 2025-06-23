import java.io.*;
import java.util.*;
 
public class Knight_Moves_Grid {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        
        int[][] moves = new int[n][n];
        for (int[] row : moves) Arrays.fill(row, -1);
        
        // Knight's 8 possible moves
        int[][] directions = {{-2,-1},{-2,1},{-1,-2},{-1,2},{1,-2},{1,2},{2,-1},{2,1}};
        
        Queue<int[]> queue = new ArrayDeque<>();
        queue.offer(new int[]{0, 0});
        moves[0][0] = 0;
        
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0], y = current[1];
            
            for (int[] dir : directions) {
                int nx = x + dir[0];
                int ny = y + dir[1];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < n && moves[nx][ny] == -1) {
                    moves[nx][ny] = moves[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }
        
        // Print output
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(moves[i][j]).append(" ");
            }
            sb.append("\n");
        }
        System.out.print(sb);
    }
}
