package com.erlong.jvm.heapoom;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TrreeNode {


    public static void main(String[] args) {


    }

    public  static  void sort(Tree tree){

        LinkedList<Tree> list = new LinkedList<>();
        list.add(tree);
        List<List<Integer>> res = new ArrayList<>();
        while (!list.isEmpty()){
            int size = list.size();
            List<Integer> subRes = new ArrayList<>();
            for(int i = 0;i < size;i++){
                Tree poll = list.poll();
                if(poll.right != null){
                    list.push(poll.right);
                }
                if(poll.left != null){
                    list.push(poll.left);
                }
                subRes.add(poll.val);
            }
            res.add(subRes);

        }

    }




}

class Tree{

    Tree left ;
    Tree right;
    int val;


}
