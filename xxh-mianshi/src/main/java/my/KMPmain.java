package my;

import java.util.Arrays;

/***
 *
 *
 *
 *
 *
 *
 * 用来研究kmp算法
 *
 */
public class KMPmain {

    public static void main(String[] args) {
        String str = "AIFAISFLIUHADIFUHLAIUFABCD EAKJAABCDEFG";
        char[] chars = str.toCharArray();
        Arrays.asList(chars).stream().forEach(System.out::println);
        String txt = "ABCDABGM";
        String part ="ABG";
        System.out.println(str.charAt(0));

        int[][] array =new int[3][256];
        System.out.println(array[0][chars[0]]);

        KMP kmp = new KMP(txt);
        kmp.search(str);











    }

    public static void test(){
        int[][] arr = {{1,2,3},{4,5,6},{7,8,9}};
        for(int x=0;x<arr.length;x++){
            for(int y=0;y<arr[x].length;y++){
                System.out.print(arr[x][y]);
            }
            System.out.println();
        }
    }


    /**
     * 验证值传递
     * @param mm
     */
    private static void increase(int mm) {
        for(int i=0;i<10;i++){
            mm++;
        }
        System.out.println("mm:"+mm);
    }
    /**
     * 暴力匹配
     */
    public static  int search(String part,String txt){
        int M = part.length();
        int N = txt.length();
        char[] parts = part.toCharArray();
        char[] txts = txt.toCharArray();
        for(int i=0;i< N-M;i++){
            int j;
            for(j =0;j<M;j++){
                System.out.println("i:"+i+",j:"+j);
                if(parts[j] != txts[i+j]){
                    break;
                }
            }
            if(j==M){
                System.out.println("j:"+j+"m:"+M);
                return i;
            }
        }
        return -1;
    }








}

/**
 *
 * KMP算法深入研究
 *
 */
class KMP{
    private int [][] dp;
    private String pat;
    public KMP(String pat){
        this.pat = pat;
        int M = pat.length();
        // dp[状态][字符] = 下个状态
        dp = new int[M][256];
        // base case
        dp[0][pat.charAt(0)] = 1;
        // 影子状态 X 初始为 0
        int X = 0;
        // 构建状态转移图（稍改的更紧凑了）
        for (int j = 1; j < M; j++) {
            for (int c = 0; c < 256; c++) {
                dp[j][c] = dp[X][c];
                dp[j][pat.charAt(j)] = j + 1;
                // 更新影子状态
                X = dp[X][pat.charAt(j)];
            }
        }
    }
    public int search(String txt){
        int M = pat.length();
        int N = txt.length();
        // pat 的初始态为 0
        int j = 0;
        for (int i = 0; i < N; i++) {
            // 当前是状态 j，遇到字符 txt[i]，
            // pat 应该转移到哪个状态？
            j = dp[j][txt.charAt(i)];
            // 如果达到终止态，返回匹配开头的索引
            if (j == M) return i - M + 1;
        }
        // 没到达终止态，匹配失败
        return -1;
    }
















}
