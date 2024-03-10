package sort;

import com.alibaba.fastjson.JSONObject;

import java.util.*;
import java.util.stream.Collectors;

public class SortMain {

    static final ThreadLocal<String> STRING_THREAD_LOCAL = new ThreadLocal<>();


    public static int minTrace (int[][] triangle) {

        int len = triangle.length;
        if (len == 0) {
            return len;
        }
        //System.out.print("len:" + len);
        int[][] dp = new int[len][len];
        for (int i = len - 1; i >= 0; i--) {
            for (int j = 0; j <= i ; j++) {

                if (i == len - 1) {
                    //第一条 i=3 j=0 对应的数字为4
                    dp[i][j] = triangle[i][j];
                    System.out.println("i:"+i+",j:"+j+"::"+dp[i][j]);
                } else {
                    System.out.println("math dp[i + 1][j]:"+dp[i+1][j] +",dp[i+1][j+1]:"+dp[i + 1][j + 1] +",triangle[i][j]:"+triangle[i][j]);
                    dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1] + triangle[i][j]);
                }
            }
        }
        return dp[0][0];
    }



    public int maxDepth(String s) {
        char[] chars = s.toCharArray();
        LinkedList<Character>linkedList = new LinkedList<>();
        int max =0;
        for(char c:chars){
            if(c =='('){
                linkedList.push(c);
                continue;
            }
            if(c ==')'){
                if(linkedList.size()>max){
                    max = linkedList.size();
                }
                linkedList.poll();
            }
        }
        return max;




    }





    /**
     * 跳台阶问题
     * @param number
     * @return
     */
    public static int jumpFloor(int number){

        if(number ==0){
            return 0;
        }
        if(number ==1){
            return 1;
        }
        if(number ==2){
            return 2;
        }
        int first =1,second = 2,third =0;
        for(int i = 3;i <= number ;i++){
            third = first+second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     *
     *
     * @param numbers int整型一维数组
     * @param target int整型
     * @return int整型一维数组
     */
    public int[] twoSum (int[] numbers, int target) {
        int[] result = new int[2];
        Map<Integer,Integer> map = new HashMap<>();
        for(int i =0;i<numbers.length;i++){
            if(map.containsKey(numbers[i])){
                result[0] = map.get(numbers[i]);
                result[1] = i+1;
            }else{
                map.put(target-numbers[i],i);
            }
        }
        return result;
    }


    /**
     * 反转字符串
     *
     * @param s
     * @param k
     * @return
     */
    public String reverseStr(String s, int k) {
        int n = s.length();
        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i = i + 2 * k) {
            reverseStrSon(chars, i, Math.min(i + k, n) - 1);
        }
        return new String(chars);
    }

    private void reverseStrSon(char[] chars, int left, int right) {
        while (left < right) {
            char temp = chars[left];
            chars[left] = chars[right];
            chars[right] = temp;
            left++;
            right--;
        }
    }

    /**
     * 目标和
     *
     * @param nums
     * @param target
     * @return
     */
    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int diff = sum - target;
        if (diff < 0 || diff % 2 != 0) {
            return 0;
        }
        int n = nums.length, neg = diff / 2;
        int[][] dp = new int[n + 1][neg + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j <= neg; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[n][neg];
    }


    static class FindTarget {
        int count = 0;

        public int findTargetSumWays(int[] nums, int target) {
            backtrack(nums, target, 0, 0);
            return count;
        }

        private void backtrack(int[] nums, int target, int index, int sum) {
            if (index == nums.length) {
                if (sum == target) {
                    count++;
                }
            } else {
                backtrack(nums, target, index + 1, sum - nums[index]);
                backtrack(nums, target, index + 1, sum - nums[index]);
            }
        }
    }


    public int fib(int n) {
        List<Integer> newList = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            if (i == 0) {
                newList.add(0);
                continue;
            }
            if (i == 1) {
                newList.add(1);
                continue;
            }
            newList.add(newList.get(i - 1) + newList.get(i - 2));
        }
        return newList.get(n);

    }

    /**
     * 三角形最小路径和
     * (动态规划  动态规划)
     *
     * @param triangle
     * @return
     */
    public int minimumTotal(List<List<Integer>> triangle) {

        return 0;

    }

    /**
     * 杨辉三角
     *
     * @param numRows
     * @return
     */
    public static List<List<Integer>> generate(int numRows) {
        List<List<Integer>> newList = new ArrayList<>();
        for (int i = 1; i <= numRows; i++) {
            newList.add(get(i, i == 1 ? null : newList.get(i - 2)));
        }
        return newList;
    }

    public static List<Integer> get(int i, List<Integer> mm) {
        List<Integer> newList = new ArrayList<>();
        for (int j = 0; j <= i - 1; j++) {
            if (j == 0 || j == i - 1) {
                newList.add(1);
                continue;
            }
            newList.add(mm.get(j) + mm.get(j - 1));
        }
        return newList;
    }


    public int longestConsecutiveSon(int[] nums) {
        Set<Integer> hashSet = new HashSet<>();
        Arrays.stream(nums).forEach(e -> hashSet.add(e));
        int max = 0;
        for (Integer num : hashSet) {
            if (!hashSet.contains(num - 1)) {
                int length = 1;
                int startNum = num;
                while (hashSet.contains(startNum + 1)) {
                    startNum++;
                    length++;
                }
                max = Math.max(max, length);
            }
        }
        return max;
    }


    /**
     * 最长连续序列
     *
     * @param nums
     * @return
     */
    public static int longestConsecutive(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        Arrays.sort(nums);
        int max = 1;
        int length = 1;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] - nums[i - 1] == 1) {
                length++;
            } else if (nums[i] != nums[i - 1]) {
                length = 1;
            }
            max = Math.max(length, max);
        }
        return max;
    }


    /**
     * 括号生成
     *
     * @param n
     * @return
     */
    public List<String> generateParenthesis(int n) {
        List<String> combinations = new ArrayList<>();
        backtrack(combinations, new StringBuilder(), 0, 0, n);
        return combinations;
    }

    private void backtrack(List<String> ans, StringBuilder cur, int open, int close, int max) {
        if (cur.length() == max * 2) {
            ans.add(cur.toString());
            return;
        }
        if (open < max) {
            cur.append("(");
            backtrack(ans, cur, open + 1, close, max);
            cur.deleteCharAt(cur.length() - 1);
        }
        if (close < open) {
            cur.append(")");
            backtrack(ans, cur, open, close + 1, max);
            cur.deleteCharAt(cur.length() - 1);
        }

    }


    /**
     * 最大子数组和
     *
     * @param nums
     * @return
     */
    public int maxSubArray1(int[] nums) {
        int len = nums.length;
        int[] dp = new int[len];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] > 0) {
                dp[i] = dp[i - 1] + nums[i];
            } else {
                dp[i] = nums[i];
            }
        }
        int max = dp[0];
        for (int i : dp) {
            max = Math.max(i, max);
        }
        return max;
    }

    public int maxSubArray2(int[] nums) {
        int pre = 0;
        int res = nums[0];
        for (int i = 0; i < nums.length; i++) {
            pre = Math.max(pre, pre + nums[i]);
            res = Math.max(pre, res);
        }
        return res;
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> ret = new ArrayList<>();
        int start = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 == nums.length || nums[i + 1] != nums[i] + 1) {
                if (start == i) {
                    ret.add(String.valueOf(nums[start]));
                } else {
                    ret.add(String.format("%d->%d", nums[start], nums[i]));
                }
                start = i + 1;
            }
        }
        return ret;
    }


    public static String simplifyPath(String path) {
        String[] names = path.split("/");
        LinkedList<String> stack = new LinkedList<>();
        for (String name : names) {
            if ("..".equals(name)) {
                if (!stack.isEmpty()) {
                    stack.pollLast();
                }
            } else if (name.length() > 0 && !".".equals(name)) {
                stack.offerLast(name);
            }
        }
        StringBuffer ans = new StringBuffer();
        if (stack.isEmpty()) {
            ans.append("/");
        } else {
            while (!stack.isEmpty()) {
                ans.append("/");
                ans.append(stack.pollFirst());
            }
        }
        return ans.toString();
    }

    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        int n = tokens.length;
        for (int i = 0; i < n; i++) {
            String token = tokens[i];
            if (isNumberStr(token)) {
                stack.push(Integer.parseInt(token));
            } else {
                int num2 = stack.poll();
                Integer num1 = stack.poll();
                switch (token) {
                    case "+":
                        stack.push(num1 + num2);
                        break;
                    case "-":
                        stack.push(num1 - num2);
                        break;
                    case "*":
                        stack.push(num1 * num2);
                        break;
                    case "/":
                        stack.push(num1 / num2);
                        break;
                    default:
                }
            }
        }
        return stack.pop();

    }

    public boolean isNumberStr(String token) {
        return !("+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token));
    }


    public List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();
        lists.add(new ArrayList<>());
        for (int i = 0; i < nums.length; i++) {
            int size = lists.size();
            for (int j = 0; j < size; j++) {
                List<Integer> newList = new ArrayList<>(lists.get(j));
                newList.add(nums[i]);
                lists.add(newList);
            }
        }
        return lists;
    }


    List<Integer> t = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < n * 2; ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<>(t));
        }
        return ans;
    }

    public List<List<Integer>> subsetsDfs(int[] nums) {
        dfsNumber(0, nums);
        return ans;
    }

    private void dfsNumber(int cur, int[] nums) {
        if (cur == nums.length) {
            ans.add(new ArrayList<>(t));
            return;
        }
        t.add(nums[cur]);
        dfsNumber(cur + 1, nums);
        t.remove(t.size() - 1);
        dfsNumber(cur + 1, nums);

    }


    public TreeNode sortedArrayToBST(int[] nums) {
        return helper(nums, 0, nums.length - 1);
    }

    private TreeNode helper(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = left + (right - left) / 2;
        TreeNode root = new TreeNode(nums[mid]);
        root.left = helper(nums, left, mid - 1);
        root.right = helper(nums, mid + 1, right);
        return root;
    }


    public int searchInsert(int[] nums, int target) {
        int length = nums.length;
        int left = 0, right = length - 1, ans = length;
        while (left <= right) {
            int middle = (right - left) / 2 + left;
            if (target <= nums[middle]) {
                ans = middle;
                right = middle - 1;
            } else {
                left = middle + 1;
            }
        }
        return ans;

    }


    /**
     * 单词规律
     *
     * @param pattern
     * @param s
     * @return
     */
    public boolean wordPattern(String pattern, String s) {
        Map<Character, String> sMap = new HashMap<>();
        Map<String, Character> tMap = new HashMap<>();
        String[] s1 = s.split(" ");
        if (pattern.length() != s1.length) {
            return false;
        }
        for (int i = 0; i < pattern.length(); i++) {
            char x = pattern.charAt(i);
            String y = s1[i];
            if ((sMap.containsKey(x) && !sMap.get(x).equals(y)) || tMap.containsKey(y) && tMap.get(y) != x) {
                return false;
            }
            sMap.put(x, y);
            tMap.put(y, x);
        }
        return true;

    }


    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> sMap = new HashMap<>();
        Map<Character, Character> tMap = new HashMap<>();
        for (int i = 0; i <= s.length() - 1; i++) {
            char x = s.charAt(i);
            char y = t.charAt(i);
            if ((sMap.containsKey(x) && sMap.get(x) != y) || (tMap.containsKey(y) && tMap.get(y) != x)) {
                return false;
            }
            sMap.put(x, y);
            tMap.put(y, x);
        }
        return true;

    }


    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertedNumber = 0;
        while (x > revertedNumber) {
            revertedNumber = revertedNumber * 10 + x % 10;
            x /= 10;
        }
        return x == revertedNumber || x == revertedNumber / 10;
    }


    public int findPeakElement(int[] nums) {
        if (nums.length == 1) {
            return 0;
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (i == 0 && num > nums[i + 1]) {
                return i;
            }
            if (i > 0 && i + 1 <= nums.length - 1 && num > nums[i - 1] && num > nums[i + 1]) {
                return i;
            }
            if (i == nums.length - 1 && num > nums[i - 1]) {
                return i;
            }
        }
        return -1;
    }


    public String reverseVowels(String s) {
        int n = s.length();
        char[] arr = s.toCharArray();
        int i = 0, j = n - 1;
        while (i < j) {
            while (i < n && !isVowel(arr[i])) {
                ++i;
            }
            while (j > 0 && !isVowel(arr[j])) {
                --j;
            }
            if (i < j) {
                swap(arr, i, j);
                ++i;
                --j;
            }
        }
        return new String(arr);
    }

    public boolean isVowel(char ch) {
        return "aeiouAEIOU".indexOf(ch) >= 0;
    }

    public void swap(char[] arr, int i, int j) {
        char temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public boolean canConstruct(String ransomNote, String magazine) {
        char[] chars = magazine.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char aChar : chars) {
            map.put(aChar, map.getOrDefault(aChar, 0) + 1);
        }
        char[] chars1 = ransomNote.toCharArray();
        for (char c : chars1) {
            if (!map.containsKey(c) || map.get(c) < 1) {
                return false;
            }
            map.put(c, map.getOrDefault(c, 0) - 1);
        }
        return true;

    }


    public static boolean isPalindrome(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        int length = s.length();
        for (int i = 0; i < length; i++) {
            char ch = s.charAt(i);
            if (Character.isLetterOrDigit(ch)) {
                stringBuilder.append(Character.toLowerCase(ch));
            }
        }
        int n = stringBuilder.length();
        int left = 0, right = n - 1;
        while (left < right) {
            if (Character.toLowerCase(stringBuilder.charAt(left)) != Character.toLowerCase(stringBuilder.charAt(right))) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> resut = new ArrayList<>();
        List<Integer> list = null;
        Arrays.sort(nums);
        Set<String> ss = new HashSet<>();
        for (int i = 0; i <= nums.length - 1; i++) {
            kk:
            for (int j = i + 1; j <= nums.length - 1; j++) {
                for (int k = j + 1; k <= nums.length - 1; k++) {
                    if (nums[i] + nums[j] + nums[k] > 0) {
                        continue kk;
                    }
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        String s = nums[i] + ":" + nums[j] + ":" + nums[k];
                        if (ss.contains(s)) {
                            continue;
                        }
                        list = new ArrayList<>();
                        list.add(nums[i]);
                        list.add(nums[j]);
                        list.add(nums[k]);
                        ss.add(s);
                        resut.add(list);
                    }
                }
            }
        }
        return resut;
    }

    public int strStr(String haystack, String needle) {
        char[] chars = needle.toCharArray();
        char[] hayStackArr = haystack.toCharArray();
        if (chars.length > hayStackArr.length) {
            return -1;
        }
        for (int i = 0; i <= hayStackArr.length - 1; i++) {
            if (i + chars.length > hayStackArr.length) {
                return -1;
            }
            String substring = haystack.substring(i, i + chars.length);
            if (needle.equals(substring)) {
                return i;
            }
        }
        return -1;
    }


    public static int[] dailyTemperatures(int[] temperatures) {
        int[] ans = new int[temperatures.length];
        LinkedList<Integer> linkedList = new LinkedList<>();
        for (int i = 0; i < temperatures.length; i++) {
            int tem = temperatures[i];
            while (!linkedList.isEmpty() && tem > temperatures[linkedList.peek()]) {
                int preIndex = linkedList.poll();
                ans[preIndex] = i - preIndex;
            }
            linkedList.push(i);
        }
        return ans;

    }

    public boolean uniqueOccurrences(int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (Integer integer : arr) {
            if (map.containsKey(integer)) {
                map.put(integer, map.get(integer) + 1);
            } else {
                map.put(integer, 1);
            }
        }
        return new HashSet<>(map.values()).size() == map.values().size();
    }

    public static int maxDepthn(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int leftDepth = maxDepthn(root.left) + 1;
        int rightDepth = maxDepthn(root.right) + 1;
        return Math.max(leftDepth, rightDepth);
    }


    public static TreeNode searchBST1(TreeNode root, int val) {
        if (root == null) {
            return null;
        }
        if (val == root.val) {
            return root;
        }
        return searchBST(val < root.val ? root.left : root.right, val);


    }

    public static TreeNode searchBST(TreeNode root, int val) {
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.push(root);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            for (int i = 0; i < size; i++) {
                TreeNode pop = linkedList.pop();
                if (pop.val == val) {
                    return pop;
                }
                if (pop.left != null) {
                    linkedList.push(pop.left);
                }
                if (pop.right != null) {
                    linkedList.push(pop.right);
                }
            }
        }
        return new TreeNode();
    }


    public boolean leafSimilar(TreeNode root1, TreeNode root2) {
        List<Integer> leftList = new LinkedList<>();
        List<Integer> rightList = new LinkedList<>();
        dfs(root1, leftList);
        dfs(root2, rightList);
        if (rightList.size() != leftList.size()) {
            return false;
        }
        for (int i = 0; i < leftList.size(); i++) {
            Integer integer = leftList.get(i);
            if (!integer.equals(rightList.get(i))) {
                return false;
            }
        }
        return true;

    }

    private void dfs(TreeNode root, List<Integer> leftList) {
        if (root == null) {
            return;
        }
        if (root.left == null && root.right == null) {
            leftList.add(root.val);
        }
        dfs(root, leftList);
        dfs(root, leftList);
    }


    public static int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> linked = new LinkedList<>();
        int i = asteroids.length - 1;
        while (i >= 0) {
            Integer peek = linked.peek();
            int asteroid = asteroids[i--];
            if (peek == null) {
                linked.push(asteroid);
                continue;
            }
            if (peek > 0 && asteroid < 0) {
                linked.push(asteroid);
                continue;
            }
            if (peek < 0 && asteroid < 0) {
                linked.push(asteroid);
                continue;
            }
            if (peek > 0 && asteroid > 0) {
                linked.push(asteroid);
                continue;
            }
            if (Math.abs(peek) - Math.abs(asteroid) > 0) {
                continue;
            }
            if (peek + asteroid == 0) {
                linked.pop();
                continue;
            }
            if (Math.abs(peek) - Math.abs(asteroid) < 0) {
                linked.pop();
            }
            linked.push(asteroid);
        }
        int[] ans = new int[linked.size()];
        for (int j = 0; j <= ans.length - 1; j++) {
            ans[j] = linked.pop();
        }
        return ans;
    }

    public static String removeStars1(String s) {
        LinkedList<Character> linkedList = new LinkedList<>();
        int i = 0;
        char[] chars = s.toCharArray();
        Character m = '*';
        while (i < chars.length) {
            if (m.equals(chars[i])) {
                if (!linkedList.isEmpty()) {
                    linkedList.pop();
                }
            } else {
                linkedList.push(chars[i]);
            }
            i++;
        }
        String str = "";
        while (!linkedList.isEmpty()) {
            str = linkedList.pop() + str;
        }
        return str;
    }

    public static String removeStars(String s) {
        StringBuilder stringBuilder = new StringBuilder();
        LinkedList<Character> linkedList = new LinkedList<>();
        char[] chars = s.toCharArray();
        for (char aChar : chars) {
            linkedList.push(aChar);
        }
        Character m = '*';
        int same = 0;
        while (!linkedList.isEmpty()) {
            Character pop = linkedList.pop();
            if (pop.equals(m)) {
                same++;
            } else {
                if (same == 0) {
                    stringBuilder.insert(0, pop.charValue());
                } else {
                    same--;
                }

            }
        }
        return stringBuilder.toString();
    }


    public static int maxArea(int[] height) {
        int i = 0, j = height.length - 1, res = 0;
        while (i < j) {
            res = height[i] < height[j] ? Math.max(res, (j - i) * height[i++]) : Math.max(res, (j - i) * height[j++]);
        }


        int max = 0;
//        for (int i = 0; i <= height.length - 1; i++) {
//            for(int j =i+1; j<= height.length -1;j++){
//                max = Math.max(max, Math.min(height[i],height[j])*(j-i));
//            }
//        }
        return max;
    }


    public static int compress(char[] chars) {
        if (chars.length == 1) {
            return 1;
        }
        int number = 1;
        int last = 0;
        int k = 0;
        List<Character> newlist = new ArrayList<Character>();
        newlist.add(chars[0]);
        for (int i = 1; i <= chars.length - 1; i++) {
            char firstChar = chars[i];
            if (chars[last] == firstChar) {
                k++;
                continue;
            } else {
                if (k != 0) {
                    char[] chars1 = String.valueOf(k + 1).toCharArray();
                    for (char c : chars1) {
                        newlist.add(c);
                    }
                }
                newlist.add(firstChar);
                number = number + String.valueOf(k).toCharArray().length;
                last = ++last + k;
                k = 0;
                number++;

            }
        }
        if (k != 0) {
            char[] chars1 = String.valueOf(k + 1).toCharArray();
            for (char c : chars1) {
                newlist.add(c);
            }
            number = number + String.valueOf(k).toCharArray().length;
        }
        char[] newChars = new char[newlist.toArray().length];
        chars = new char[newlist.toArray().length];
        for (int i = 0; i <= newChars.length - 1; i++) {
            newChars[i] = newlist.get(i);
            chars[i] = newChars[i];
        }
        chars = newChars;
        System.out.println(JSONObject.toJSON(chars));
        return number;
    }

    public static String mergeAlternately(String word1, String word2) {
        StringBuilder stringBuilder = new StringBuilder();
        char[] leftChars = word1.toCharArray();
        char[] rightChars = word2.toCharArray();
        int left = 0;
        int right = 0;
        while (left < leftChars.length && right < rightChars.length) {
            if (left <= right) {
                stringBuilder.append(leftChars[left++]);
            } else {
                stringBuilder.append(rightChars[right++]);
            }
        }
        if (left < leftChars.length) {
            stringBuilder.append(word1.substring(left, leftChars.length));
        }
        if (right < rightChars.length) {
            stringBuilder.append(word2.substring(right, rightChars.length));
        }
        return stringBuilder.toString();
    }


    public static boolean isSubsequence(String s, String t) {
        char[] chars1 = s.toCharArray();
        char[] chars = t.toCharArray();
        int cur = 0;
        for (int i = 0; i < chars.length - 1; i++) {
            char aChar = chars[i];
            if (cur != chars1.length && aChar == chars1[cur]) {
                cur++;
            }
        }
        return cur == chars1.length;
    }


    public static int minNumber(int[] nums1, int[] nums2) {
        TreeSet<Integer> left = new TreeSet<>();
        List<Integer> collect = Arrays.stream(nums1).boxed().collect(Collectors.toList());
        left.addAll(collect);
        TreeSet<Integer> right = new TreeSet<>();
        right.addAll(Arrays.stream(nums2).boxed().collect(Collectors.toList()));
        int min = left.first().compareTo(right.first()) < 0 ? (left.first() * 10 + right.first()) : (right.first() * 10 + left.first());
        while (!left.isEmpty() && !right.isEmpty()) {
            Integer integer = left.pollFirst();
            if (right.contains(integer)) {
                return integer;
            }
        }
        return min;
    }


    public static int findKthLagest(int[] nums, int k) {
        // PriorityQueue<Integer> objects = new PriorityQueue<>();
        // Comparator comparator =
        PriorityQueue<Integer> objects = new PriorityQueue<Integer>((Integer o1, Integer o2) -> {
            return o2 - o1;
        });
        for (int val : nums) {
            objects.add(val);
            if (objects.size() > k) {
                objects.poll();
            }
        }
        System.out.println(JSONObject.toJSON(objects));
        return objects.peek();


    }


    public int[] plusOne1(int[] digits) {
        for (int j = digits.length - 1; j >= 0; j--) {
            digits[j] = digits[j]++;
            digits[j] = digits[j] % 10;
            if (digits[j] != 0) {
                return digits;
            }
        }
        digits = new int[digits.length + 1];
        digits[0] = 1;
        return digits;
    }


    public static int[] plusOne(int[] digits) {
        LinkedList<Integer> list = new LinkedList<>();
        int num = 1;
        for (int i = digits.length - 1; i >= 0; i--) {
            int j = digits[i] + num;
            if (j == 10) {
                list.addFirst(0);
                num = 1;
            } else {
                list.addFirst(digits[i]);
                num = 0;
            }
        }
        if (num == 1) {
            list.addFirst(1);
        }
        return list.stream().mapToInt(Integer::intValue).toArray();
    }


    public int maxDepth1(TreeNode treeNode) {
        if (treeNode == null) {
            return 0;
        }
        return Math.max(maxDepth1(treeNode.right) + 1, maxDepth1(treeNode.left) + 1);
    }

    public int heightWithIterative(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            height++;
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (poll.left != null) {
                    queue.offer(poll.left);
                }
                if (poll.right != null) {
                    queue.offer(poll.right);
                }
            }
        }
        return height;
    }


    public List<Integer> maxDepth(TreeNode treeNode) {
        return new ArrayList<>();


    }


    public void cengci(TreeNode treeNode) {
        Queue<TreeNode> queue = new ArrayDeque<>();
        if (treeNode == null) {
            return;
        }
        queue.offer(treeNode);
        int nextCount = 1;
        int count = 0;
        StringBuffer stringBuffer = new StringBuffer();
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            count++;
            stringBuffer.append(poll.val);
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
            if (count == nextCount) {
                count = 0;
                nextCount = queue.size();
                System.out.println(stringBuffer.toString());
                stringBuffer = new StringBuffer();
            }
        }
    }


    public void checkList() {

        ArrayList list = new ArrayList();
        list.add("");
        LinkedList linkedList = new LinkedList();


    }


    Map<Node, Node> cache = new HashMap<>();

    /**
     * 无向图深copy
     *
     * @param node
     * @return
     */
    public Node cloneGraphs(Node node) {
        if (node == null) {
            return null;
        }
        Map<Node, Node> map = new HashMap<>();
        Queue<Node> oldQueue = new ArrayDeque<>();
        oldQueue.add(node);
        Node newNode = new Node(node.val);
        map.put(node, newNode);
        while (!oldQueue.isEmpty()) {
            Node oldNode = oldQueue.poll();
            Node newNodeValue = map.get(oldNode);
            List<Node> neighbors = oldNode.neighbors;
            List<Node> newNeighbors = new ArrayList<>();
            for (Node node1 : neighbors) {
                if (map.containsKey(node1)) {
                    newNeighbors.add(map.get(node1));
                } else {
                    Node no = new Node(node1.val);
                    map.put(node1, no);
                    newNeighbors.add(no);
                    oldQueue.add(node1);
                }
            }
            newNodeValue.neighbors = newNeighbors;
        }
        return newNode;
    }

    int maxSum = Integer.MIN_VALUE;

    /**
     * 最大路径和
     *
     * @return
     */
    public int deepSum(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int maxLeft = Math.max(deepSum(root.left), 0);
        int maxRight = Math.min(deepSum(root.right), 0);
        int cur = root.val;
        int priceNewpath = cur + maxLeft + maxRight;
        // 更新答案
        maxSum = Math.max(maxSum, priceNewpath);

        return root.val + Math.max(maxLeft, maxRight);
    }


    public Node cloneGraph(Node node) {
        if (cache.get(node) != null) {
            return cache.get(node);
        }
        Node newNode = new Node();
        cache.put(node, newNode);
        newNode.val = node.val;
        List<Node> neighbors = node.neighbors;
        for (Node neighbor : neighbors) {
            newNode.neighbors.add(cloneGraph(neighbor));
        }
        return new Node();

    }


    class Node {
        public int val;

        public Node() {
        }

        public Node(int val) {
            this.val = val;
        }

        public List<Node> neighbors;
    }


    /**
     * 垂直打印一颗二叉树
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        Map<Integer, List<Integer>> map = new TreeMap<>();
        Queue<TreeNode> q = new LinkedList<>();
        Queue<Integer> ids = new LinkedList<>();
        q.add(root);
        ids.add(0);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                Integer id = ids.poll();
                if (!map.containsKey(id)) {
                    map.put(id, new ArrayList<>());
                }
                map.get(i).add(cur.val);
                if (cur.left != null) {
                    q.add(cur.left);
                    ids.add(id - 1);
                }
                if (cur.right != null) {
                    q.add(cur.right);
                    ids.add(id + 1);
                }
            }
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 打印一颗树的平局深度
     *
     * @param root
     * @return
     */
    public List<Double> averageOfLevels(TreeNode root) {
        List<Double> averages = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            double sum = 0;
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                sum += poll.val;
                TreeNode left = poll.left;
                TreeNode right = poll.right;
                if (left != null) {
                    queue.offer(left);
                }
                if (right != null) {
                    queue.offer(right);
                }

            }
            averages.add(sum / size);
        }
        return averages;

    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * @param nums1
     * @param nums2
     * @param k
     * @return
     */
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<int[]> pq = new PriorityQueue<>(k, (o1, o2) -> {
            return nums1[o1[0] + nums2[o1[1] - nums1[o2[0]] - nums2[o2[1]]]];
        });
        List<List<Integer>> ans = new ArrayList<>();
        int m = nums1.length;
        int n = nums2.length;
        for (int i = 0; i < Math.min(m, k); i++) {
            pq.offer(new int[]{i, 0});
        }
        while (k-- > 0 && !pq.isEmpty()) {
            int[] idxPair = pq.poll();
            List<Integer> list = new ArrayList<>();
            list.add(nums1[idxPair[0]]);
            list.add(nums2[idxPair[1]]);
            ans.add(list);
            if (idxPair[1] + 1 < n) {
                pq.offer(new int[]{idxPair[0], idxPair[1] + 1});
            }
        }
        return ans;

    }

    /**
     * 数组中的第k个最大元素
     *
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        for (int num : nums) {
            System.out.println("aa" + num);
        }
        System.out.println("a" + nums);
        for (int i = nums.length - 1; i >= 0; i--) {
            if (--k == 0) {
                return nums[i];
            }
        }
        return 0;

    }

    /**
     * 爬楼梯
     *
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        int a = 1, b = 1, sum;
        for (int i = 0; i < n - 1; i++) {
            sum = a + b;
            a = b;
            b = sum;
        }
        return b;
    }

    /**
     * 最长公共前缀
     *
     * @param strs
     * @return
     */
    public static String longestCommonPrefix(String[] strs) {
        char[] chars = strs[0].toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < chars.length; i++) {
            Character aChar = chars[i];
            for (String str : strs) {
                char[] tempChar = str.toCharArray();
                if (tempChar.length <= i) {
                    aChar = null;
                    break;
                }
                if (aChar != tempChar[i]) {
                    aChar = null;
                    break;
                }
            }
            if (aChar != null) {
                sb.append(aChar);
            } else {
                break;
            }
        }
        return sb.toString();
    }

    public static String reverse(String str) {
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char aChar = chars[i];
            if (isNumber(aChar)) {
                int fast = i;
                int last = i;
                if (i == chars.length - 1 || !isNumber(chars[++i])) {
                    continue;
                }
                for (; i < chars.length; i++) {
                    if (!isNumber(chars[i])) {
                        break;
                    }
                    last = i;
                }
                int middle = (fast + last) / 2;
                for (; fast <= middle; fast++) {
                    swapNum(chars, fast, last);
                    last--;
                }
            }
        }
        return new String(chars);
    }

    private static void swapNum(char[] aChars, int fast, int last) {
        char temp = aChars[fast];
        aChars[fast] = aChars[last];
        aChars[last] = temp;
    }

    private static boolean isNumber(Character aChar) {
        return aChar >= '0' && aChar <= '9';
    }

    public int lengthOfLastWord(String s) {
        String[] str = s.trim().split(" ");
        return str[str.length - 1].toCharArray().length;
    }

    /**
     * 罗马数字转整数
     *
     * @param s
     * @return
     */
    public static int romanToInt(String s) {
        Map<Character, Integer> charMap = new HashMap<>();
        charMap.put('I', 1);
        charMap.put('V', 5);
        charMap.put('X', 10);
        charMap.put('L', 50);
        charMap.put('C', 100);
        charMap.put('D', 500);
        charMap.put('M', 1000);
        char[] chars = s.toCharArray();
        int sum = 0;
        for (int i = 0; i <= chars.length - 1; i++) {
            char aChar = chars[i];
            Integer num1 = charMap.get(aChar);
            if (i + 1 <= chars.length - 1) {
                int num2 = charMap.get(chars[i + 1]);
                if (num2 / num1 == 5 || num2 / num1 == 10) {
                    sum = sum + num2 - num1;
                    i++;
                    continue;
                }
            }
            sum = sum + num1;
        }
        return sum;
    }

    /**
     * @param citations
     * @return
     */
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        int h = 0, i = citations.length - 1;
        while (i >= 0 && citations[i] > h) {
            h++;
            i--;
        }
        return h;
    }


    /**
     * 挑格子
     *
     * @param nums
     * @return
     */
    public static boolean canJump(int[] nums) {
        int k = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > k) {
                return false;
            }
            k = Math.max(k, i + nums[i]);
        }
        return true;
    }




    public int maxProfit(int[] prices) {
        int min = prices[0];
        int max = 0;
        for (int i = 0; i < prices.length; i++) {
            min = Math.min(min, prices[i]);
            if (prices[i] - min > max) {
                max = prices[i] - min;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        testRotate();
    }
    public static void testRotate(){
        int[] arr ={23,2,12,3,123,12,23,12,31,231223,54,5,6,7,7,9};
        romanToInt(arr,1);
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 旋转数组
     *
     * @param nums
     * @param k
     */
    public static void romanToInt(int[] nums, int k) {
        int length = nums.length;
        int[] newArr = new int[length];
        for (int i = 0; i < nums.length; i++) {
            newArr[(i + k) % length] = nums[i];
        }
        System.arraycopy(newArr, 0, nums, 0, length);
    }

    private void swapNum(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }

    /**
     * 利用投票法
     *
     * @param nums
     * @return
     */
    public int majorityElement1(int[] nums) {
        Integer vote = 0;
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (sum == 0) {
                vote = nums[i];
            }
            sum = sum + (vote == nums[i] ? 1 : -1);
        }
        return vote;
    }



    public static void testMajorityElement(){
        int[] arr ={23,2,12,3,123,12,23,12,31,231223,54,5,6,7,7,9};
        int i = majorityElement(arr);
        System.out.println(Arrays.toString(arr)+",i:"+i);
    }

    /**
     * 分治方法
     *todo 这里是干啥用的
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        return majorityElementRec(nums, 0, nums.length - 1);
    }

    private static int majorityElementRec(int[] nums, int lo, int hi) {
        if (lo == hi) {
            return nums[lo];
        }
        int mid = (hi - lo) / 2 + lo;
        int left = majorityElementRec(nums, lo, mid);
        int right = majorityElementRec(nums, mid + 1, hi);
        if (left == right) {
            return left;
        }
        int leftCount = countInRange(nums, left, lo, hi);
        int rightCount = countInRange(nums, right, lo, hi);
        return leftCount > rightCount ? left : right;
    }

    private static int countInRange(int[] nums, int num, int lo, int hi) {
        int count = 0;
        for (int i = lo; i < hi; i++) {
            if (nums[i] == num) {
                count++;
            }
        }
        return count;
    }



    public static void testRemoveDuplicates1(){
        int[] arr0 ={1,1};
        removeDuplicates1(arr0);
        System.out.println(Arrays.toString(arr0));
        int[] arr1 ={1,2,3,4,5,5};
        removeDuplicates1(arr1);
        System.out.println(Arrays.toString(arr1));
        int[] arr2 ={1,2,2,4,5,5};
        removeDuplicates1(arr2);
        System.out.println(Arrays.toString(arr2));
    }

    /**
     * 删除重复两次的  双指针方法
     *todo
     * @param nums
     * @return
     */
    public static int removeDuplicates1(int[] nums) {
        int n = nums.length;
        if (n <= 1) {
            return n;
        }
        int slow = 0, fast =1;
        while (fast < n) {
            if (nums[slow - 2] != nums[fast]) {
                nums[slow] = nums[fast];
                ++slow;
            }
            ++fast;
        }
        return slow;
    }

    /**
     * 删除重复的数组
     *
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Set<Integer> intNum = new HashSet<>();
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            boolean noMatch = intNum.stream().noneMatch(e -> e == num);
            if (noMatch) {
                nums[left++] = nums[i];
                intNum.add(num);
            }
        }
        return intNum.size();
    }

    /***
     * 移除原属
     * @param nums
     * @param val
     * @return
     */
    public int removeElement(int[] nums, int val) {
        int left = 0;
        for (int i = 0; i < nums.length; i++) {
            int m = nums[i];
            if (m != val) {
                nums[left] = nums[i];
                left++;
            }
        }
        return left;
    }



    /**
     * 合并数组
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        HashMap hashMap = new HashMap();
        hashMap.put("", "");
        hashMap.remove("");
        int[] sorted = new int[m + n];
        int p1 = 0;
        int p2 = 0;
        int cur;
        while (p1 < m || p2 < n) {
            if (p1 == m) {
                cur = nums2[p2++];
            } else if (p2 == n) {
                cur = nums1[p1++];
            } else if (nums1[p1] < nums2[p2]) {
                cur = nums1[p1++];
            } else {
                cur = nums2[p2++];
            }
            sorted[p1 + p2 - 1] = cur;
        }
        for (int i = 0; i < sorted.length; i++) {
            nums1[i] = sorted[i];
        }
    }

    class MinStack {
        LinkedList<Integer> xStack;
        LinkedList<Integer> minStack;
        public MinStack() {
            xStack = new LinkedList<>();
            minStack = new LinkedList<>();
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            xStack.push(val);
            minStack.push(Math.min(minStack.peek(), val));
        }

        public void pop() {
            xStack.poll();
            minStack.poll();
        }

        public int top() {
            return xStack.peek();

        }

        public int getMin() {
            return minStack.poll();
        }
    }

    class Trie {

        private Trie[] childeren;
        private boolean isEnd;

        public Trie() {
            childeren = new Trie[26];
            isEnd = false;
        }

        public void insert(String word) {
            Trie node = this;
            for (int i = 0; i < word.length(); i++) {
                char c = word.charAt(i);
                int index = c - 'a';
                if (node.childeren[index] == null) {
                    node.childeren[index] = new Trie();
                }
                node = node.childeren[index];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            Trie node = searchPrefix(word);
            return node != null && node.isEnd;

        }

        public boolean startsWith(String prefix) {
            return searchPrefix(prefix) != null;
        }

        private Trie searchPrefix(String prefix) {
            Trie node = this;
            for (int i = 0; i < prefix.length(); i++) {
                char ch = prefix.charAt(i);
                int index = ch - 'a';
                if (node.childeren[index] == null) {
                    return null;
                }
                node = node.childeren[index];
            }
            return node;
        }
    }


}
