package algoritohm;

import java.util.LinkedList;

public class HanOi {


    /**
     * 杨辉三角
     * @param i
     * @param j
     * @return
     */
    private static int element(int i,int j){
        if(i == j || j ==0){
            return 1;
        }
        return element(i-1,j-1)+element(i-1,j);
    }


    private static int element(int[][] trigle,int i,int j){
        if(trigle[i][j] > 0){
            return trigle[i][j];
        }
        if(i == j || j ==0){
            trigle[i][j] =1;
            return 1;
        }
        trigle[i][j] = element(i-1,j-1)+element(i-1,j);
        return  trigle[i][j];
    }

    /**
     *
     * @param n 个数
     * @param a 原始柱子a
     * @param b 中间柱子b
     * @param c 最终柱子c
     */
    static void move(int n, LinkedList<Integer> a, LinkedList<Integer> b,LinkedList<Integer> c){
        if(n == 0){
            return;
        }
        move(n-1,a,c,b); //把n-1的盘子由a借c移到b
        c.addLast(a.removeLast());
        move(n-1,b,a,c); //把n-1的盘子由b借a移到c

    }


}
