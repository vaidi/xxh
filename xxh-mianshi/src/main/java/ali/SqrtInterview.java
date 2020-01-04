package ali;

/**
 * 找平方根的计算
 */
public class SqrtInterview {

    public static void main(String[] args) {
        double squrt = Math.sqrt(4.0);
        System.out.println("squrt:" + squrt);
        System.out.println("squrt22:" + sqrtDichotomy(4.0));
        System.out.println("///" + (1.4 + 1.5 / 2));
    }


    /**
     * 利用二分法进行开方计算
     * @param num
     * @return
     */
    public static double sqrtDichotomy(Double num) {
        double lowNum = 1;
        double highNum = num;
        double epsilon = 0.0000000001;
        double middleNum =1.0;
        while (highNum - lowNum >= epsilon) {
            middleNum = (lowNum + highNum) / 2;
            double sqrtNum = middleNum * middleNum;
            if (sqrtNum > num) {
                highNum = middleNum;
            } else {
                lowNum = middleNum;
            }
        }
        return middleNum;
    }

    /**
     *
     * 牛顿迭代法
     * EPSILON = 0.1 ** 10
     * def newton(x):
     *     if abs(x ** 2 - 2) > EPSILON:
     *         return newton(x - (x ** 2 - 2) / (2 * x))
     *     else:
     *         return x
     * @param num
     * @return
     */
    public static Double sqrtNiuDun(Double num){
       return null;
    }






}
