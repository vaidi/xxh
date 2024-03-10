package sort;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 关于二叉树的算法
 */
public class TreeSort {

    public static void main(String[] args) {
        TreeNode diameter = new TreeNode(4, new TreeNode(2, new TreeNode(1, null, null), new TreeNode(3, null, null)), null);
        diameterOfBinaryTree(diameter);


        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        IntStream.rangeClosed(0, 100).forEach(e -> {
            priorityQueue.offer(e);
        });
        while (!priorityQueue.isEmpty()) {
            Integer poll = priorityQueue.poll();
            System.out.println("poll:" + poll);
        }

        HashMap<Object, Object> objectObjectHashMap = new HashMap<>();
        LinkedList linkedList = new LinkedList();

        TreeNode left = new TreeNode(2, new TreeNode(3, null, null), new TreeNode(4, null, null));
        TreeNode right = new TreeNode(6, new TreeNode(7, null, null), new TreeNode(8, null, null));
        TreeNode root = new TreeNode(5, left, right);

        System.out.println(JSONObject.toJSON(sameHeighTree(root, 2)));
        System.out.println(minDepth1(root));
        System.out.println(minDepth1(new TreeNode(5, new TreeNode(7, null, null), null)));
        bianli(root);
        zigzagLevelOrder(root);
        ArrayList<Object> objects = Lists.newArrayList();
        preOrder(root, objects);
        System.out.println("前序遍历:" + JSONObject.toJSON(objects));
        ArrayList<Object> inOrders = Lists.newArrayList();
        inOrder(root, inOrders);
        System.out.println("中序遍历:" + JSONObject.toJSON(inOrders));
        System.out.println("s是否对称：" + isSymmetric(root));
        TreeNode root1 = new TreeNode(5, null, null);
        System.out.println("s是否对称：" + isSymmetric(root1));
    }

    static int ans = 0;

    public TreeNode findFatherTreeNode(TreeNode root, TreeNode q, TreeNode p) {
        if (root == null) {
            return null;
        }
        return findFatherTreeNodeSon(root, q, p);
    }




    private TreeNode findFatherTreeNodeSon(TreeNode node, TreeNode q, TreeNode p) {
        if (node == null || node == q || node == p) {
            return node;
        }
        TreeNode leftTreeNode = findFatherTreeNode(node.left, q, p);
        TreeNode rightTreeNode = findFatherTreeNode(node.left, q, p);
        if (leftTreeNode != null && rightTreeNode != null) {
            return node;
        }
        if (leftTreeNode == null && rightTreeNode != null) {
            return rightTreeNode;
        }
        if (leftTreeNode != null && rightTreeNode == null) {
            return leftTreeNode;
        }
        return null;
    }


    public static int diameterOfBinaryTree1(TreeNode root) {
        int ans = 1;
        dfsTreeNode(root);
        return ans - 1;
    }

    private static int dfsTreeNode(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = dfsTreeNode(root.left);
        int right = dfsTreeNode(root.right);
        ans = Math.max(ans, left + right + 1);
        return Math.max(left, right) + 1;
    }


    public static int diameterOfBinaryTree(TreeNode root) {
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;
        int start = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (start == 0) {
                    if (poll.left != null) {
                        count++;
                        queue.offer(poll.left);
                    }
                    if (poll.right != null) {
                        count++;
                        queue.offer(poll.right);
                    }
                    start = 1;
                } else {
                    if (poll.left != null || poll.right != null) {
                        count++;
                        if (poll.left != null) {
                            queue.offer(poll.left);
                        }
                        if (poll.right != null) {
                            queue.offer(poll.right);
                        }
                    }
                }
            }
        }
        return count;
    }


    public int getMinimumDifference(TreeNode root) {
        int min = Integer.MAX_VALUE;
        PriorityQueue<Integer> priorityQueue = new PriorityQueue<>();
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.offer(root);
        while (!linkedList.isEmpty()) {
            TreeNode poll = linkedList.poll();
            priorityQueue.offer(poll.val);
            if (poll.left != null) {
                linkedList.offer(poll.left);
            }
            if (poll.right != null) {
                linkedList.offer(poll.right);
            }
        }
        while (priorityQueue.isEmpty()) {
            Integer poll = priorityQueue.poll();
            Integer peek = priorityQueue.peek();
            if (peek != null) {
                min = Math.min(min, peek - poll);
            }
        }


        return min;
    }

    public List<Double> averageOfLevels(TreeNode root) {
        LinkedList<TreeNode> treeNodes = new LinkedList<>();
        treeNodes.offer(root);
        List<Double> result = new ArrayList<>();
        while (!treeNodes.isEmpty()) {
            int size = treeNodes.size();
            double sum = 0;
            for (int i = 0; i < size; i++) {
                TreeNode poll = treeNodes.poll();
                sum = sum + poll.val;
                if (poll.left != null) {
                    treeNodes.offer(poll.left);
                }
                if (poll.right != null) {
                    treeNodes.offer(poll.right);
                }
            }
            result.add(sum / size);
        }
        return result;
    }

    public boolean hasPathSumCircle(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        if (root.left == null && root.right == null) {
            return targetSum == root.val;
        }
        return hasPathSumCircle(root.left, targetSum - root.val) || hasPathSumCircle(root.right, targetSum - root.val);


    }

    public boolean hasPathSumGuangdu(TreeNode root, int targetSum) {
        if (root == null) {
            return false;
        }
        LinkedList<TreeNode> queNode = new LinkedList<TreeNode>();
        LinkedList<Integer> queVal = new LinkedList<>();
        queNode.offer(root);
        queVal.offer(root.val);
        while (!queNode.isEmpty()) {
            TreeNode poll = queNode.poll();
            int temp = queVal.poll();
            if (poll.left == null && poll.right == null) {
                if (temp == targetSum) {
                    return true;
                }
                continue;
            }
            if (poll.left != null) {
                queNode.offer(poll.left);
                queVal.offer(poll.left.val + temp);
            }
            if (poll.right != null) {
                queNode.offer(poll.right);
                queVal.offer(poll.right.val + temp);
            }
        }
        return false;
    }

    public TreeNode invertTree(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.right = left;
        root.left = right;
        return root;
    }


    static Map<Integer, TreeNode> parent = new HashMap<>();
    static Set<Integer> visited = new HashSet<>();

    public static void dfs(TreeNode root) {
        if (root.left != null) {
            parent.put(root.left.val, root);
            dfs(root.left);
        }
        if (root.right != null) {
            parent.put(root.right.val, root);
            dfs(root.right);
        }
    }

    public static TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root);
        while (p != null) {
            visited.add(p.val);
            p = parent.get(p.val);
        }
        while (q != null) {
            if (visited.contains(q.val)) {
                return q;
            }
            q = parent.get(q.val);
        }
        return null;
    }


    private static TreeNode ans1;

    /**
     * 最近公共节点
     *
     * @param root
     * @param p
     * @param q
     * @return
     */
    public static TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfsLower(root, p, q);
        return ans1;
    }

    private static boolean dfsLower(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return false;
        }
        boolean lson = dfsLower(root.left, p, q);
        boolean rson = dfsLower(root.right, p, q);
        if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
            ans1 = root;
        }
        return lson || rson || (root.val == p.val || root.val == q.val);
    }


    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        TreeNode node = null;
        LinkedList<TreeNode> linkedList = new LinkedList<>();
        linkedList.add(root);
        LinkedList<TreeNode> lastNode = new LinkedList<>();
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            lastNode.addAll(linkedList);
            if (lastNode.size() > size) {
                lastNode.poll();
            }
            for (int i = 0; i < size; i++) {
                TreeNode pop = linkedList.pop();
                node = pop;
                lastNode.add(pop);
                TreeNode left = pop.left;
                if (left != null) {
                    linkedList.add(left);
                }
                TreeNode right = pop.right;
                if (right != null) {
                    linkedList.add(right);
                }
            }
            //最后一个叶子节点
            if (linkedList.isEmpty()) {
                while (!lastNode.isEmpty()) {
                    TreeNode pop = lastNode.pollLast();
                    if (pop.left == null || pop.right == null) {
                        continue;
                    }
                    if (pop.left == node || pop.right == node) {
                        node = pop;
                        break;
                    }
                }
            }
        }
        return node;
    }


    public static List<TreeNode> sameHeighTree(TreeNode node, int heigh) {
        List<TreeNode> treeNodes = new ArrayList<>();
        int treeHeigh = treeHeigh(node, treeNodes, heigh);
        return treeNodes;

    }

    private static int treeHeigh(TreeNode node, List<TreeNode> treeNodes, int heigh) {
        if (node == null) {
            return 0;
        }
        int height = Math.max(treeHeigh(node.left, treeNodes, heigh), treeHeigh(node.right, treeNodes, heigh)) + 1;
        if (height == heigh) {
            treeNodes.add(node);
        }
        return height;
    }


    public static boolean isSubTree(TreeNode s, TreeNode t) {
        int height = 0;
        LinkedList<TreeNode> queue = new LinkedList<>();
        return isSubTreeq(s, t, height, queue);
    }

    private static boolean isSubTreeq(TreeNode s, TreeNode t, int height, LinkedList<TreeNode> queue) {
        height = heigh(t, height, queue);
        heigh(s, height, queue);
        while (!queue.isEmpty()) {
            TreeNode poll = queue.poll();
            if (isSameTree(poll, t)) {
                return true;
            }
        }
        return false;
    }

    private static int heigh(TreeNode root, int heigh, LinkedList<TreeNode> queue) {
        if (root == null) {
            return 0;
        }
        int h = Math.max(heigh(root.left, heigh, queue), heigh(root.right, heigh, queue)) + 1;
        if (heigh == h) {
            queue.offer(root);
        }
        return h;
    }


    /**
     * 判断t是不是s的子树
     *
     * @param s
     * @param t
     * @return
     */
    public static boolean isSubTreeCirculate(TreeNode s, TreeNode t) {
        if (s == null) {
            return false;
        }
        if (isSameTree(s, t)) {
            return true;
        }
        return isSubTreeCirculate(s.left, t) || isSubTreeCirculate(s.right, t);

    }

    private static boolean isSameTree(TreeNode s, TreeNode t) {
        if (s == null && t == null) {
            return true;
        }
        if (s == null || t == null) {
            return false;
        }
        if (s.val != t.val) {
            return false;
        }
        return isSameTree(s.left, t.left) && isSameTree(s.right, t.right);

    }


    /***
     * 反转二叉树
     * @param root
     * @return
     */
    public void inverTree(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode t = root.left;
        root.left = root.right;
        root.right = t;
        inverTree(root.left);
        inverTree(root.right);
    }


    /**
     * 最小深度
     *
     * @param node
     * @return
     */
    public static int minDepth1(TreeNode node) {
        LinkedList<TreeNode> linkedList = new LinkedList();
        linkedList.add(node);
        int heigh = 0;
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            heigh++;
            for (int i = 0; i < size; i++) {
                TreeNode pop = linkedList.pop();
                if (pop.left == null && pop.right == null) {
                    return heigh;
                }
                if (pop.right != null) {
                    linkedList.add(pop.right);
                }
                if (pop.left != null) {
                    linkedList.add(pop.left);
                }
            }
        }
        return heigh;

    }


    public static int minDepth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        int d1 = minDepth(node.left);
        int d2 = minDepth(node.right);
        if (d2 == 0) {
            return d1 + 1;
        }
        if (d1 == 0) {
            return d2 + 1;
        }
        return Math.min(d1, d2) + 2;
    }


    public static boolean isSymmetric(TreeNode node) {
        return checkIsSym(node.left, node.right);
    }

    private static boolean checkIsSym(TreeNode left, TreeNode right) {
        if (left == null && right == null) {
            return true;
        }
        if (left == null || right == null) {
            return false;
        }
        if (left.val != right.val) {
            return false;
        }
        return checkIsSym(left.right, right.left) && checkIsSym(left.left, right.right);
    }


    /**
     * 前序遍历
     * <p>
     * 1,先访问该节点
     * 2,然后左子树
     * 3，然后右子树
     *
     * @param node
     */
    static void preOrder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        list.add(node.val);
        preOrder(node.left, list);
        preOrder(node.right, list);
    }


    static void preOrderNoStack(TreeNode node) {
        if (node == null) {
            return;
        }
        LinkedList<TreeNode> linkedList = new LinkedList();
        TreeNode cur = node;
        while (cur != null || !linkedList.isEmpty()) {
            if (cur != null) {
                linkedList.push(cur);
                int val = cur.val;
                //这里是谦虚节点
                cur = cur.left;
            } else {
                //这里中序遍历
                TreeNode pop = linkedList.pop();
                cur = pop.right;
            }
        }
    }


    public static void inOrder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        list.add(node.val);
        inOrder(node.right, list);
    }

    public static void postOrder(TreeNode node, List list) {
        if (node == null) {
            return;
        }
        inOrder(node.left, list);
        inOrder(node.right, list);
        list.add(node.val);

    }


    public static void zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        LinkedList<TreeNode> objects = new LinkedList<>();
        objects.offer(root);
        LinkedList<TreeNode> sumList = new LinkedList<>();
        int index = 0;
        LinkedList<Integer> objects1 = new LinkedList<>();
        while (objects.size() != 0) {
            TreeNode poll = objects.poll();
            if (poll != null) {
                if (index % 2 == 0) {
                    objects1.add(poll.val);
                } else {
                    objects1.addFirst(poll.val);
                }
                TreeNode left = poll.left;
                if (left != null) {
                    sumList.addLast(left);
                }
                TreeNode right = poll.right;
                if (right != null) {
                    sumList.addLast(right);
                }
            }
            if (objects.size() == 0) {
                while (sumList.size() != 0) {
                    TreeNode poll1 = sumList.poll();
                    objects.addLast(poll1);
                }
                list.add(objects1);
                objects1 = new LinkedList<>();
                index++;
            }
        }
        System.out.println(JSON.toJSONString(list));


    }


    /**
     *
     */
    public static void bianli(TreeNode root) {
        Map<Integer, List<Integer>> map = new HashMap<>();
        LinkedList<TreeNode> objects = new LinkedList<>();
        objects.offer(root);
        LinkedList<TreeNode> sumList = new LinkedList<>();
        int index = 0;
        while (objects.size() != 0) {
            TreeNode poll = objects.poll();
            if (poll != null) {
                if (map.containsKey(index)) {
                    List<Integer> integers = map.get(index);
                    integers.add(poll.val);
                } else {
                    map.put(index, Lists.newArrayList(poll.val));
                }
                TreeNode left = poll.left;
                if (left != null) {
                    sumList.addLast(left);
                }
                TreeNode right = poll.right;
                if (right != null) {
                    sumList.addLast(right);
                }
            }
            if (objects.size() == 0) {
                while (sumList.size() != 0) {
                    TreeNode poll1 = sumList.poll();
                    objects.addLast(poll1);
                }
                index++;
            }
        }
        System.out.println(JSON.toJSONString(map));
    }


    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }

        public TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return "TreeNode{" +
                    "val=" + val +
                    ", left=" + left +
                    ", right=" + right +
                    '}';
        }
    }

    class SmallestInfiniteSet {

        PriorityQueue<Integer> queue = null;

        public SmallestInfiniteSet() {
            queue = new PriorityQueue<>();
            IntStream.rangeClosed(1, 1000).forEach(e -> queue.offer(e));
        }

        public int popSmallest() {
            return queue.poll();

        }

        public void addBack(int num) {
            if (!queue.contains(num)) {
                queue.offer(num);
            }
        }

    }

}
