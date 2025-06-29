import java.io.*;
import java.util.*;

public class IncreasingSubsequenceII {
    static final int MOD = 1_000_000_007;
    
    static class FenwickTree {
        int size;
        int[] tree;
        
        FenwickTree(int size) {
            this.size = size;
            this.tree = new int[size + 2];
        }
        
        void update(int idx, int val) {
            while (idx <= size) {
                tree[idx] = (tree[idx] + val) % MOD;
                idx += idx & -idx;
            }
        }
        
        int query(int idx) {
            int res = 0;
            while (idx > 0) {
                res = (res + tree[idx]) % MOD;
                idx -= idx & -idx;
            }
            return res;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        // Coordinate compression
        int[] sorted = arr.clone();
        Arrays.sort(sorted);
        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;
        for (int num : sorted) {
            if (!map.containsKey(num)) {
                map.put(num, rank++);
            }
        }
        
        FenwickTree ft = new FenwickTree(rank);
        int result = 0;
        
        for (int num : arr) {
            int r = map.get(num);
            int count = ft.query(r - 1) + 1; // +1 for the single element subsequence
            ft.update(r, count);
            result = (result + count) % MOD;
        }
        
        pw.println(result);
        pw.flush();
    }
}
