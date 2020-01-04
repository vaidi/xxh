package ali;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node1 = new TreeNode(1);
        //右边的
        TreeNode node8 = new TreeNode(8);
        TreeNode node7 = new TreeNode(7);
        TreeNode node9 = new TreeNode(9);
        rootNode.leftNode =node3;
        rootNode.rightNode =node8;
        node3.leftNode=node2;
        node3.rightNode=node4;
        node8.leftNode=node7;
        node8.rightNode=node9;
        node2.leftNode=node1;
        BinarySearchTree treeSearch = new BinarySearchTree();

        int val = treeSearch.kthSmallest(rootNode,3);
        System.out.println("val:"+val);





    }













    private class ResultType {
        boolean found;
        int val;

        ResultType(boolean found, int val) {
            this.found = found;
            this.val = val;
        }
    }

    public  int kthSmallest(TreeNode root, int k) {
        return kthSmallestHelper(root, k).val;
    }

    private  ResultType kthSmallestHelper(TreeNode root, int k) {
        if (root == null) {
            return new ResultType(false, 0);
        }
        ResultType left = kthSmallestHelper(root.leftNode, k);
        //左子树找到，直接返回
        if (left.found) {
            return new ResultType(true, left.val);
        }
        //左子树的节点数目=k-1，结果为root的值
        if (k - left.val == 1) {
            return new ResultType(true, root.val);
        }
        //右子树寻找
        ResultType right = kthSmallestHelper(root.rightNode, k - left.val - 1);
        if (right.found) {
            return new ResultType(true, right.val);
        }
        //没 找到，返回节点总数
        return new ResultType(false, left.val + 1 + right.val);
    }


}

class TreeNode {
    int val;
    TreeNode leftNode;
    TreeNode rightNode;

    public TreeNode(int x) {
        this.val = x;
    }
}