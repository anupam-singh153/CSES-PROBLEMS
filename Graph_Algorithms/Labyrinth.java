import java.io.*;
import java.util.*;

public class Labyrinth {
    static int n, m;
    static char[][] grid;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static char[] dirChars = {'U', 'D', 'L', 'R'};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new char[n][m];
        
        int startX = 0, startY = 0;
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == 'A') {
                    startX = i;
                    startY = j;
                }
            }
        }
        
        bfs(startX, startY);
    }
    
    static void bfs(int x, int y) {
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{x, y});
        char[][] path = new char[n][m];
        int[][] parentX = new int[n][m];
        int[][] parentY = new int[n][m];
        grid[x][y] = '#';
        
        while (!q.isEmpty()) {
            int[] curr = q.poll();
            int cx = curr[0], cy = curr[1];
            
            if (grid[cx][cy] == 'B') {
                StringBuilder sb = new StringBuilder();
                while (cx != x || cy != y) {
                    sb.append(path[cx][cy]);
                    int px = parentX[cx][cy];
                    int py = parentY[cx][cy];
                    cx = px;
                    cy = py;
                }
                System.out.println("YES");
                System.out.println(sb.length());
                System.out.println(sb.reverse());
                return;
            }
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dirs[i][0];
                int ny = cy + dirs[i][1];
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] != '#') {
                    path[nx][ny] = dirChars[i];
                    parentX[nx][ny] = cx;
                    parentY[nx][ny] = cy;
                    grid[nx][ny] = '#';
                    q.add(new int[]{nx, ny});
                }
            }
        }
        
        System.out.println("NO");
    }
}
