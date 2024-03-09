package ali;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 二叉搜索树
 */
public class BinarySearchTree {

    public static void main(String[] args) {
        TreeNode rootNode = new TreeNode(6);
        TreeNode node3 = new TreeNode(3);
        rootNode.leftNode=node3;

        TreeNode node2 = new TreeNode(2);
        rootNode.rightNode=node2;

        TreeNode node4 = new TreeNode(4);
        node3.leftNode = node4;
//        TreeNode node1 = new TreeNode(1);
//        //右边的
//        TreeNode node8 = new TreeNode(8);
//        TreeNode node7 = new TreeNode(7);
//        TreeNode node9 = new TreeNode(9);
        sort(rootNode);



    }



    public  static  void sort(TreeNode tree){
        LinkedList<TreeNode> list = new LinkedList<>();
        list.add(tree);
        List<List<Integer>> res = new ArrayList<>();
        while (!list.isEmpty()){
            int size = list.size();
            List<Integer> subRes = new ArrayList<>();
            for(int i = 0;i < size;i++){
                TreeNode poll = list.poll();
                if(poll.leftNode != null){
                    list.push(poll.leftNode);
                }
                if(poll.rightNode != null){
                    list.push(poll.rightNode);
                }
                subRes.add(poll.val);
            }
            res.add(subRes);
        }
        System.out.println(JSONObject.toJSON(res));
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