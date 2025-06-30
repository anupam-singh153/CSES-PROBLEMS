import java.io.*;
import java.util.*;

public class CompanyQueriesII {
    static int[][] up;
    static int[] depth;
    static int LOG;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        up = new int[n + 1][LOG];
        depth = new int[n + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            up[i][0] = Integer.parseInt(st.nextToken());
            depth[i] = depth[up[i][0]] + 1;
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            if (depth[a] < depth[b]) {
                int temp = a;
                a = b;
                b = temp;
            }
            for (int j = LOG - 1; j >= 0; j--) {
                if (depth[a] - (1 << j) >= depth[b]) {
                    a = up[a][j];
                }
            }
            if (a == b) {
                System.out.println(a);
                continue;
            }
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[a][j] != up[b][j]) {
                    a = up[a][j];
                    b = up[b][j];
                }
            }
            System.out.println(up[a][0]);
        }
    }
}
