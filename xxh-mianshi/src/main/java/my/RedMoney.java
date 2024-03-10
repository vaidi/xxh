package my;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/***
 * 红包  二倍均值法
 */
public class RedMoney {



    /**
     *
     * 抢红包 分配 ----  二倍均值法实现
     * @param totalAmount  总金额
     * @param totalPeopleNum  总个数
     * @return
     */

    // 发红包算法，金额参数以分为单位
    public static List<String> divideRedPackage(String totalAmount,
                                                Integer totalPeopleNum) {

        List<String> amountList = new ArrayList<>();

        Integer restAmount ;
        boolean flag = false;
        boolean numeric = isNumeric(totalAmount);
        if (numeric){
            BigDecimal multiply = new BigDecimal(totalAmount).multiply(new BigDecimal(100));
            restAmount = multiply.intValue();
            flag = true;
        }else {
            restAmount = Integer.parseInt(totalAmount);
        }

        Integer restPeopleNum = totalPeopleNum;
        Random random = new Random();

        for (int i = 0; i < totalPeopleNum - 1; i++) {

            // 随机范围：[1，剩余人均金额的两倍)，左闭右开
            int amount = random.nextInt(restAmount / restPeopleNum * 2 - 1) + 1;
            // 金额递减
            restAmount -= amount;
            // 红包数量递减
            restPeopleNum--;

            amountList.add(bigDecimalToString(flag,amount));
        }
        //最后一个拿剩余全部
        amountList.add(bigDecimalToString(flag,restAmount));

        return amountList;
    }

    /**
     * 判断是否为小数
     * @param str
     * @return
     */
    private static boolean isNumeric(String str){

        String[] split = str.split("\\.");

        if (split.length > 1 && new BigDecimal(split[1]).compareTo(new BigDecimal("0")) ==1){
            return true;
        }
        if (split.length > 1 && new BigDecimal(split[1]).compareTo(new BigDecimal("0")) <=0){
            return false;
        }
        return false;
    }

    /**
     * 格式化输出
     * @param flag
     * @param b
     * @return
     */
    private static String bigDecimalToString( boolean flag,Integer b){
        BigDecimal bigDecimal ;
        if(flag){
            bigDecimal = new BigDecimal(b).divide(new BigDecimal(100)).setScale(2);
        }else {
            bigDecimal =  new BigDecimal( b);
        }

        return bigDecimal.toPlainString();
    }



    public static void main(String[] args) {
        // 小数 整数 随便输入 如: 12.12 3434.21  30
        List<String> amountList = divideRedPackage("30", 10);
        BigDecimal total = new BigDecimal("0.00");
        for (String amount : amountList) {
            BigDecimal bigDecimal = new BigDecimal(amount);
            System.out.println("抢到金额：" + bigDecimal);
            total =total.add(bigDecimal);
        }
        System.out.println("金额total：" + total);
    }


}
