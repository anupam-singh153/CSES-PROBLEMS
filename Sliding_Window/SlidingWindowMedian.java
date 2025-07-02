import java.io.*;
import java.util.*;

public class SlidingWindowMedian {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter pw = new PrintWriter(System.out);

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        TreeMap<Integer, Integer> left = new TreeMap<>(Collections.reverseOrder()); // max-heap
        TreeMap<Integer, Integer> right = new TreeMap<>(); // min-heap
        int leftSize = 0, rightSize = 0;

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int num = arr[i];

            if (leftSize == 0 || num <= left.firstKey()) {
                add(left, num);
                leftSize++;
            } else {
                add(right, num);
                rightSize++;
            }

            // Rebalance
            while (leftSize > rightSize + 1) {
                int move = left.firstKey();
                removeOne(left, move);
                leftSize--;
                add(right, move);
                rightSize++;
            }
            while (leftSize < rightSize) {
                int move = right.firstKey();
                removeOne(right, move);
                rightSize--;
                add(left, move);
                leftSize++;
            }

            // Remove outgoing
            if (i >= k) {
                int out = arr[i - k];
                if (left.containsKey(out)) {
                    removeOne(left, out);
                    leftSize--;
                } else {
                    removeOne(right, out);
                    rightSize--;
                }

                // Rebalance after removal
                while (leftSize > rightSize + 1) {
                    int move = left.firstKey();
                    removeOne(left, move);
                    leftSize--;
                    add(right, move);
                    rightSize++;
                }
                while (leftSize < rightSize) {
                    int move = right.firstKey();
                    removeOne(right, move);
                    rightSize--;
                    add(left, move);
                    leftSize++;
                }
            }

            if (i >= k - 1) {
                sb.append(left.firstKey()).append(" ");
            }
        }

        pw.println(sb.toString().trim());
        pw.flush();
    }

    static void add(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.getOrDefault(key, 0) + 1);
    }

    static void removeOne(TreeMap<Integer, Integer> map, int key) {
        if (map.get(key) == 1) {
            map.remove(key);
        } else {
            map.put(key, map.get(key) - 1);
        }
    }
}
