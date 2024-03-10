package sort;

import com.alibaba.fastjson.JSONObject;

import java.util.*;

public class ProveOffer {


    public static void main(String[] args) {

        String mm ="123";
        System.out.println("公共长串:"+mm.substring(0,0));

        int m = 9;
        m /= 3;
        System.out.println(JSONObject.toJSON(dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73})));
        System.out.println(16 & (16 - 1));

        System.out.println(numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));

        System.out.println(m >>> 1);
        int[] array = {5, 7, 7, 8, 8, 10};
        int[] ints = searchRange(array, 6);
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
    }


    /**
     * 子数组 的最小值之后
     *
     * @param arr
     * @return
     */
    public static int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int[] left = new int[n];
        int[] right = new int[n];
        Arrays.fill(left, -1);
        Arrays.fill(right, n);
        Deque<Integer> stk = new ArrayDeque<>();
        for (int i = 0; i < n - 1; i++) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.poll();
            }
            if (!stk.isEmpty()) {
                left[i] = stk.peek();
            }
            stk.push(i);
        }
        stk.clear();
        for (int i = n - 1; i >= 0; --i) {
            while(!stk.isEmpty() && arr[stk.peek()] > arr[i]){
                stk.pop();
            }
            if(!stk.isEmpty()){
                right[i] = stk.peek();
            }
            stk.push(i);
        }
        int mod =(int)1e9 +7;
        long ans =0;
        for(int i =0;i < n ;++i){
            ans += (long)(i-left[i])*(right[i] -i)% mod * arr[i]%mod;
            ans %= mod;
        }
        return (int)ans;


    }


    /**
     * 天气算法
     *
     * @param temperatures
     * @return
     */
    public static int[] dailyTemperatures(int[] temperatures) {
        Deque<Integer> deque = new ArrayDeque<>();
        int[] ans = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; --i) {
            while (!deque.isEmpty() && temperatures[deque.peek()] <= temperatures[i]) {
                deque.poll();
            }
            if (!deque.isEmpty()) {
                ans[i] = deque.peek() - i;
            }
            deque.push(i);
        }
        return ans;
    }


    /**
     * 下一个更大元素
     *
     * @param nums1
     * @param nums2
     * @return
     */
    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Deque<Integer> stk = new ArrayDeque<>();
        Map<Integer, Integer> m = new HashMap<>();
        for (Integer n2 : nums2) {
            while (!stk.isEmpty() && stk.peek() < n2) {
                m.put(stk.pop(), n2);
            }
            stk.push(n2);
        }
        int i = 0;
        int[] ans = new int[nums1.length];
        for (Integer integer : nums1) {
            ans[i++] = m.getOrDefault(integer, -1);
        }
        return ans;
    }

    /**
     * n-(n&-n)可以消除二进制形式的最后一位1
     *
     * @param n
     * @return
     */
    public static int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n = n & (n - 1);
            ++ans;
        }
        return ans;
    }


    /**
     * 成绩小于k的子数组
     * 不懂  不懂  不懂  深奥啊
     *
     * @param nums
     * @param k
     * @return
     */
    public static int numSubarrayProductLessThanK(int[] nums, int k) {
        int ans = 0;
        for (int i = 0, j = 0, s = 1; i < nums.length - 1; i++) {
            s = s * nums[i];
            while (j <= i && s >= k) {
                s = s / nums[j++];
            }
            ans += i - j + 1;

        }
        return ans;
    }


    /**
     * 求两个数组的和
     *
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        char[] leftChars = num1.toCharArray();
        char[] rightChars = num2.toCharArray();
        int left = leftChars.length - 1;
        int right = rightChars.length - 1;
        StringBuilder sb = new StringBuilder();
        int leftNum = 0;
        int rightNum = 0;
        int sum = 0;
        while (left >= 0 || right >= 0) {
            if (left >= 0) {
                leftNum = Character.getNumericValue(leftChars[left--]);
            } else {
                leftNum = 0;
            }
            if (right >= 0) {
                rightNum = Character.getNumericValue(rightChars[right--]);
            } else {
                rightNum = 0;
            }
            int m = rightNum + leftNum + sum;
            if (m >= 10) {
                sum = 1;
            } else {
                sum = 0;
            }
            sb.insert(0, m % 10);
        }
        if (sum == 1) {
            sb.insert(0, 1);
        }
        return sb.toString();
    }


    /**
     * 无重复字符串的最长子串
     *
     * @param s
     * @return
     */
    public static int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        char[] chars = s.toCharArray();
        int last = 0;
        int ans = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            while (set.contains(chars[i])) {
                set.remove(chars[last++]);
            }
            set.add(chars[i]);
            ans = Math.max(ans, i - last + 1);
        }
        return ans;

    }

    public static int minSpeedOnTime(int[] dist, double hour) {
        int left = 1, right = (int) 1e7;
        while (left < right) {
            int middle = (left + right) >> 1;
            if (check(dist, middle, hour)) {
                right = middle;
            } else {
                left = middle + 1;
            }
        }
        return check(dist, left, hour) ? left : -1;


    }

    private static boolean check(int[] dist, int speed, double hour) {
        double res = 0;
        for (int i = 0; i < dist.length; ++i) {
            double cost = dist[i] * 1 / speed;
            res += (i == dist.length - 1 ? cost : Math.ceil(cost));
            ;
        }
        return res <= hour;

    }


    /**
     * 求数组出现的位置
     *
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int l = search(nums, target);
        int r = search(nums, target + 1);
        return l == r ? new int[]{-1, -1} : new int[]{l, r - 1};

    }


    private static int search(int[] nums, int x) {
        int left = 0, right = nums.length;
        while (left < right) {
            int mid = (left + right) >>> 1;
            if (nums[mid] >= x) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


}
