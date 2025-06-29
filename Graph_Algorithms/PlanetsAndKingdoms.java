import java.io.*;
import java.util.*;

public class PlanetsAndKingdoms {
    static List<Integer>[] adj, adjRev;
    static boolean[] visited;
    static List<Integer> order;
    static int[] component;
    static int currentComponent;
    
    static void dfs1(int u) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs1(v);
            }
        }
        order.add(u);
    }
    
    static void dfs2(int u) {
        component[u] = currentComponent;
        for (int v : adjRev[u]) {
            if (component[v] == 0) {
                dfs2(v);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        adj = new ArrayList[n+1];
        adjRev = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
            adjRev[i] = new ArrayList<>();
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adjRev[b].add(a);
        }
        
        visited = new boolean[n+1];
        order = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                dfs1(i);
            }
        }
        
        Collections.reverse(order);
        component = new int[n+1];
        currentComponent = 0;
        
        for (int u : order) {
            if (component[u] == 0) {
                currentComponent++;
                dfs2(u);
            }
        }
        
        pw.println(currentComponent);
        for (int i = 1; i <= n; i++) {
            pw.print(component[i] + " ");
        }
        pw.flush();
    }
}
