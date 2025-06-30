import java.io.*;
import java.util.*;

public class TreeDistancesI {
    static List<Integer>[] adj;
    static int[] maxDist;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	PrintWriter pw = new PrintWriter(System.out);

        n = Integer.parseInt(br.readLine());
        adj = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            adj[a].add(b);
            adj[b].add(a);
        }
        maxDist = new int[n + 1];
        int u = bfs(1);
        int v = bfs(u);
        int[] distU = new int[n + 1];
        int[] distV = new int[n + 1];
        bfs(u, distU);
        bfs(v, distV);

	StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= n; i++) {
            sb.append(Math.max(distU[i], distV[i])).append(" ");
        }

	pw.println(sb);
	pw.flush();
    }

    static int bfs(int start) {
        int[] dist = new int[n + 1];
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;
        int farthestNode = start;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neighbor : adj[current]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[current] + 1;
                    q.add(neighbor);
                    if (dist[neighbor] > dist[farthestNode]) {
                        farthestNode = neighbor;
                    }
                }
            }
        }
        return farthestNode;
    }

    static void bfs(int start, int[] dist) {
        Arrays.fill(dist, -1);
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        dist[start] = 0;
        while (!q.isEmpty()) {
            int current = q.poll();
            for (int neighbor : adj[current]) {
                if (dist[neighbor] == -1) {
                    dist[neighbor] = dist[current] + 1;
                    q.add(neighbor);
                }
            }
        }
    }
}
