import java.io.*;
import java.util.*;

public class BuildingTeams {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        List<Integer>[] adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        
        int[] color = new int[n+1];
        Arrays.fill(color, -1);
        boolean isBipartite = true;
        
        for (int i = 1; i <= n && isBipartite; i++) {
            if (color[i] == -1) {
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                color[i] = 0;
                
                while (!q.isEmpty() && isBipartite) {
                    int u = q.poll();
                    for (int v : adj[u]) {
                        if (color[v] == -1) {
                            color[v] = color[u] ^ 1;
                            q.add(v);
                        } else if (color[v] == color[u]) {
                            isBipartite = false;
                            break;
                        }
                    }
                }
            }
        }
        
	StringBuilder sb = new StringBuilder();

        if (!isBipartite) {
            sb.append("IMPOSSIBLE");
        } else {
            for (int i = 1; i <= n; i++) {
                sb.append(color[i] + 1).append(" ");
            }
        }

	pw.println(sb);
        pw.flush();
    }
}
