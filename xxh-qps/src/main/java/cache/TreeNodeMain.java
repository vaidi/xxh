package cache;

import java.util.LinkedList;

public class TreeNodeMain {


    public static void main(String[] args) {
        TreeNode left  =   new TreeNode(2,new TreeNode(5,null,null),new TreeNode(6,null,null));
        TreeNode right  =   new TreeNode(3,new TreeNode(7,null,null),new TreeNode(8,null,null));
        TreeNode treeNode = new TreeNode(1,left,right);
        revoseTreeNode(treeNode);


    }

    public static  void revoseTreeNode(TreeNode treeNode){
        LinkedList<TreeNode> link = new LinkedList<>();
        link.add(treeNode);
        while (!link.isEmpty()){
            int size = link.size();
            for(int i = 0;i< size;i++){
                TreeNode poll = link.poll();
                System.out.print(poll.val +" ");
                TreeNode right =   poll.right;
                if(right != null){
                    link.add(right);
                }
                TreeNode left =   poll.left;
                if(left != null){
                    link.add(left);
                }

            }
            System.out.println();
        }



    }







   static   class  TreeNode{
       TreeNode left;
       TreeNode right;
        int val;
         TreeNode( int val,TreeNode left,TreeNode right){
             this.val = val;
             this.left = left;
             this.right = right;
         }
    }



}
