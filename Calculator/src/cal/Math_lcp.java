/*四则运算：*/
package cal;
import java.math.BigDecimal;
public class Math_lcp {

    private static BigDecimal BigDecimal(double num1) {
        // TODO Auto-generated method stub
        return new BigDecimal( num1 );
    }

    //加法
    public static double add(double num1, double num2 ) {
        BigDecimal first = BigDecimal( num1 );
        BigDecimal second = BigDecimal( num2 );
        return first.add(second).doubleValue();
        
    }
    
    
    //减法
    public static double subtract(double num1, double num2 ) {
        BigDecimal first = BigDecimal( num1 );
        BigDecimal second = BigDecimal( num2 );
        return first.subtract(second).doubleValue();
    }
    
    //乘法
    public static double multiply(double num1, double num2 ) {
        BigDecimal first = BigDecimal( num1 );
        BigDecimal second = BigDecimal( num2 );
        return first.multiply(second).doubleValue();
    }
    
    //除法
    public static double divide(double num1, double num2 ) {
        BigDecimal first = BigDecimal( num1 );
        BigDecimal second = BigDecimal( num2 );
        return first.divide(second, 20, BigDecimal.ROUND_HALF_UP).doubleValue();//四舍五入
    }
    
    
    
}