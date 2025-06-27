import java.io.*;
import java.util.*;

public class CountingRooms {
    static int n, m;
    static char[][] grid;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        grid = new char[n][m];
        
        for (int i = 0; i < n; i++) {
            grid[i] = br.readLine().toCharArray();
        }
        
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (grid[i][j] == '.') {
                    dfs(i, j);
                    count++;
                }
            }
        }
        
        System.out.println(count);
    }
    
    static void dfs(int x, int y) {
        grid[x][y] = '#';
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && grid[nx][ny] == '.') {
                dfs(nx, ny);
            }
        }
    }
}
