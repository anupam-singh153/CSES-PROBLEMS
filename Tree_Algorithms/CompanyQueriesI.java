import java.io.*;
import java.util.*;

public class CompanyQueriesI {
    static int[][] up;
    static int LOG;
    static int n;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        LOG = (int) (Math.log(n) / Math.log(2)) + 1;
        up = new int[n + 1][LOG];
        st = new StringTokenizer(br.readLine());
        for (int i = 2; i <= n; i++) {
            up[i][0] = Integer.parseInt(st.nextToken());
        }
        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= n; i++) {
                up[i][j] = up[up[i][j - 1]][j - 1];
            }
        }
        while (q-- > 0) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());
            for (int j = LOG - 1; j >= 0; j--) {
                if (k >= (1 << j)) {
                    x = up[x][j];
                    k -= (1 << j);
                }
            }
            System.out.println(x == 0 ? -1 : x);
        }
    }
}
