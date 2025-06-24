import java.io.*;
import java.util.*;
 
public class Distinct_Values_SubArrays_II {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        System.out.println(countSubarraysWithAtMostKDistinct(arr, k));
    }
    
    private static long countSubarraysWithAtMostKDistinct(int[] nums, int k) {
        return countAtMostKDistinct(nums, k);
    }
    
    private static long countAtMostKDistinct(int[] nums, int k) {
        long result = 0;
        int left = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();
        
        for (int right = 0; right < nums.length; right++) {
            // Add current element to frequency map
            freqMap.put(nums[right], freqMap.getOrDefault(nums[right], 0) + 1);
            
            // Shrink the window from left if distinct elements exceed k
            while (freqMap.size() > k) {
                freqMap.put(nums[left], freqMap.get(nums[left]) - 1);
                if (freqMap.get(nums[left]) == 0) {
                    freqMap.remove(nums[left]);
                }
                left++;
            }
            
            // All subarrays ending at right with at most k distinct elements
            result += right - left + 1;
        }
        
        return result;
    }
}
