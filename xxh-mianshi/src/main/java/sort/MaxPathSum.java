package sort;

import java.util.HashSet;
import java.util.Set;

public class MaxPathSum {
    /**
     * 获取一个最大路径
     * @param root
     * @param paths
     */
    public static void findMaxPathSum(TreeNode root, Set<Integer> paths) {
        if (root == null) {
            return;
        }
        // 计算以当前节点为起点的最大和路径
        int maxSum = Integer.MIN_VALUE;
        findMaxPathSumUtil(root, root.val, maxSum);
        // 递归处理左右子树，并更新最大和路径
        int leftMaxSum = Integer.MIN_VALUE;
        int rightMaxSum = Integer.MIN_VALUE;
        if (root.left != null) {
            findMaxPathSumUtil(root.left, root.left.val, leftMaxSum);
        }
        if (root.right != null) {
            findMaxPathSumUtil(root.right, root.right.val, rightMaxSum);
        }
        maxSum = Math.max(maxSum, Math.max(leftMaxSum, rightMaxSum) + root.val);
        // 将最大和路径加入集合
        paths.add(maxSum);
        // 递归处理左右子树
        if (root.left != null) {
            findMaxPathSum(root.left, paths);
        }
        if (root.right != null) {
            findMaxPathSum(root.right, paths);
        }
    }

    private static int findMaxPathSumUtil(TreeNode node, int currentSum, int maxSum) {
        if (node == null) {
            return currentSum;
        }

        // 更新当前路径的和，并考虑左右子树的最大和路径
        int leftSum = Math.max(currentSum, node.val);
        int rightSum = Math.max(currentSum + node.val, findMaxPathSumUtil(node.left, currentSum + node.val, maxSum));
        int currentMaxSum = Math.max(maxSum, Math.max(leftSum, rightSum));

        // 返回以当前节点为起点的最大和路径
        return currentMaxSum + node.val;
    }

    public static void main(String[] args) {
        // 创建二叉树示例
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);

        // 初始化集合存储最大和路径
        Set<Integer> paths = new HashSet<>();
        findMaxPathSum(root, paths);
        // 输出最大和路径集合
        System.out.println("Max Path Sums: " + paths);
    }





    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int val) {
            this.val = val;
        }
    }

}
