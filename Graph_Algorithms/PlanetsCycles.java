import java.io.*;
import java.util.*;

public class PlanetsCycles {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] next = new int[n+1];
        for (int u = 1; u <= n; u++) {
            next[u] = Integer.parseInt(st.nextToken());
        }
        
        int[] ans = new int[n+1];
        boolean[] visited = new boolean[n+1];
        
        for (int u = 1; u <= n; u++) {
            if (!visited[u]) {
                List<Integer> path = new ArrayList<>();
                int current = u;
                
                while (true) {
                    if (visited[current]) {
                        // Check if current is in our current path (cycle found)
                        int idx = path.indexOf(current);
                        if (idx != -1) {
                            // Nodes from idx to end form a cycle
                            int cycleSize = path.size() - idx;
                            for (int i = idx; i < path.size(); i++) {
                                ans[path.get(i)] = cycleSize;
                            }
                            // Nodes before idx lead to this cycle
                            for (int i = 0; i < idx; i++) {
                                ans[path.get(i)] = cycleSize + (idx - i);
                            }
                        } else {
                            // This path merges into a previously processed cycle
                            int dist = ans[current];
                            for (int i = 0; i < path.size(); i++) {
                                ans[path.get(i)] = dist + path.size() - i;
                            }
                        }
                        break;
                    }
                    
                    visited[current] = true;
                    path.add(current);
                    current = next[current];
                }
            }
        }
        
        for (int u = 1; u <= n; u++) {
            pw.print(ans[u] + " ");
        }
        pw.flush();
    }
}
