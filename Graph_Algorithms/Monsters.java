import java.io.*;
import java.util.*;

public class Monsters {
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static char[] dir = {'U', 'D', 'L', 'R'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        char[][] grid = new char[n][m];
        int[][] monsterDist = new int[n][m];
        for (int[] row : monsterDist) Arrays.fill(row, Integer.MAX_VALUE);
        
        Queue<int[]> q = new LinkedList<>();
        int startX = -1, startY = -1;
        
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'M') {
                    q.add(new int[]{i, j});
                    monsterDist[i][j] = 0;
                } else if (grid[i][j] == 'A') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        // Multi-source BFS for monsters
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && 
                    grid[nx][ny] != '#' && monsterDist[nx][ny] == Integer.MAX_VALUE) {
                    monsterDist[nx][ny] = monsterDist[x][y] + 1;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        
        // BFS for player with path reconstruction
        int[][] dist = new int[n][m];
        for (int[] row : dist) Arrays.fill(row, -1);
        int[][] parentDir = new int[n][m];
        dist[startX][startY] = 0;
        q.add(new int[]{startX, startY});
        boolean escaped = false;
        int endX = -1, endY = -1;
        
        while (!q.isEmpty() && !escaped) {
            int[] curr = q.poll();
            int x = curr[0], y = curr[1];
            
            if (x == 0 || x == n-1 || y == 0 || y == m-1) {
                escaped = true;
                endX = x;
                endY = y;
                break;
            }
            
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];
                
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && 
                    grid[nx][ny] != '#' && dist[nx][ny] == -1 && 
                    (dist[x][y] + 1 < monsterDist[nx][ny] || monsterDist[nx][ny] == Integer.MAX_VALUE)) {
                    dist[nx][ny] = dist[x][y] + 1;
                    parentDir[nx][ny] = k;
                    q.add(new int[]{nx, ny});
                }
            }
        }
        
        if (escaped) {
            pw.println("YES");
            pw.println(dist[endX][endY]);
            
            StringBuilder path = new StringBuilder();
            int x = endX, y = endY;
            while (x != startX || y != startY) {
                int k = parentDir[x][y];
                path.append(dir[k]);
                x -= dx[k];
                y -= dy[k];
            }
            pw.println(path.reverse());
        } else {
            pw.println("NO");
        }
        pw.flush();
    }
}
