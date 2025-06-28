import java.io.*;
import java.util.*;

public class FlightRoutesCheck {
    static List<Integer>[] adj;
    static List<Integer>[] adjRev;
    static boolean[] visited;
    static List<Integer> order;
    static int[] component;
    
    static void dfs1(int u) {
        visited[u] = true;
        for (int v : adj[u]) {
            if (!visited[v]) {
                dfs1(v);
            }
        }
        order.add(u);
    }
    
    static void dfs2(int u, int c) {
        component[u] = c;
        for (int v : adjRev[u]) {
            if (component[v] == 0) {
                dfs2(v, c);
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
        for (int u = 1; u <= n; u++) {
            if (!visited[u]) {
                dfs1(u);
            }
        }
        
        component = new int[n+1];
        int c = 0;
        Collections.reverse(order);
        for (int u : order) {
            if (component[u] == 0) {
                dfs2(u, ++c);
            }
        }
        
        if (c == 1) {
            pw.println("YES");
        } else {
            pw.println("NO");
            // Find a missing edge between components
            int[] inDegree = new int[c+1];
            for (int u = 1; u <= n; u++) {
                for (int v : adj[u]) {
                    if (component[u] != component[v]) {
                        inDegree[component[v]]++;
                    }
                }
            }
            
            int source = -1, sink = -1;
            for (int i = 1; i <= c; i++) {
                if (inDegree[i] == 0) source = i;
            }
            
            if (source == -1) source = 1;
            for (int u = 1; u <= n; u++) {
                if (component[u] == source) {
                    for (int v = 1; v <= n; v++) {
                        if (component[v] != source) {
                            pw.println(v + " " + u);
                            pw.flush();
                            return;
                        }
                    }
                }
            }
        }
        pw.flush();
    }
}
