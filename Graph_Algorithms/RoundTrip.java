import java.io.*;
import java.util.*;

public class RoundTrip {
    static List<Integer>[] adj;
    static int[] parent;
    static boolean[] visited;
    static int cycleStart, cycleEnd;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) adj[i] = new ArrayList<>();
        parent = new int[n+1];
        visited = new boolean[n+1];
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i] && dfs(i, -1)) {
                List<Integer> cycle = new ArrayList<>();
                cycle.add(cycleStart);
                for (int v = cycleEnd; v != cycleStart; v = parent[v]) {
                    cycle.add(v);
                }
                cycle.add(cycleStart);
                
                pw.println(cycle.size());
                for (int node : cycle) {
                    pw.print(node + " ");
                }
                pw.flush();
                return;
            }
        }
        
        pw.println("IMPOSSIBLE");
        pw.flush();
    }
    
    static boolean dfs(int u, int p) {
        visited[u] = true;
        parent[u] = p;
        
        for (int v : adj[u]) {
            if (v == p) continue;
            if (visited[v]) {
                cycleStart = v;
                cycleEnd = u;
                return true;
            }
            if (dfs(v, u)) return true;
        }
        return false;
    }
}
