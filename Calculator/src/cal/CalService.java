/*业务逻辑： */
package cal;

public class CalService {
        
        private double store = 0;                    // 存储器,默认为0，用于保存需要暂时保存的计算结果        
        private String firstNum = null;                // 第一个操作数        
        private String lastOp = null;                // 去保存计算符号或者其它操作        
        private String secondNum = null;            // 第二个操作数        
        private boolean isSecondNum = false;        // 是否第二个操作数，如果是，点击数字键时，则在文本框中重新输入        
        private String numString = "0123456789.";    // 数字        
        private String opString = "+-*/";            // 四则运算
        
        public String callMethod(String cmd, String text) throws Exception {    //调用方法并返回计算结果
            if (cmd.equals("C")) {
                return clearAll();
            } else if (cmd.equals("CE")) {
                return clear(text);
            } else if (cmd.equals("Back")) {
                return backSpace(text);
            } else if (numString.indexOf(cmd) != -1) {
                return catNum(cmd, text);
            } else if (opString.indexOf(cmd) != -1) {
                return setOp(cmd, text);
            } else if (cmd.equals("=")) {
                return cal(text, false);
            } else if (cmd.equals("+/-")) {
                return setNegative(text);
            } else if (cmd.equals("1/x")) {
                return setReciprocal(text);
            } else if (cmd.equals("sqrt")) {
                return sqrt(text);
            } else {
                return cal(text, true);
            } 
        }
        /*如果secondNum为空，secondNum就等于最后输入的数字，如果不为空，则等于原来的值，如果有“%”号操作，则secondNum再等于两数相乘除以100的结果；
         * 然后根据lastOp的值（+、-、*、/）去调用MyMath类中的add、subtract、multiply、divide方法，
         * 并把返回的结果保存到firstNum；最后把secondNum设置为空，把firstNum当做结果返回。
         * */
        public String cal(String text, boolean isPercent) throws Exception {        //四则运算isPercent，这是计算器的“%”号操作，如果有这种操作，第二个操作数就等于两数相乘再除以100
                                                                                    // 初始化第二个操作数
            double secondResult = secondNum == null ? Double.valueOf(text)
                    .doubleValue() : Double.valueOf(secondNum).doubleValue();
                                                                                    
            if (secondResult == 0 && this.lastOp.equals("/")) {                        // 如果除数为0，不处理
                return "0";
            }
                                                                                    // 如果有"%"操作，则第二个操作数等于两数相乘再除以100
            if (isPercent) {
                secondResult = Math_lcp.multiply(Double.valueOf(firstNum), Math_lcp
                        .divide(secondResult, 100));
            }
                        
            if (this.lastOp.equals("+")) {                                            // 四则运算，返回结果赋给第一个操作数
                firstNum = String.valueOf(Math_lcp.add(Double.valueOf(firstNum),
                        secondResult));
            } else if (this.lastOp.equals("-")) {
                firstNum = String.valueOf(Math_lcp.subtract(Double.valueOf(firstNum),
                        secondResult));
            } else if (this.lastOp.equals("*")) {
                firstNum = String.valueOf(Math_lcp.multiply(Double.valueOf(firstNum),
                        secondResult));
            } else if (this.lastOp.equals("/")) {
                firstNum = String.valueOf(Math_lcp.divide(Double.valueOf(firstNum),
                        secondResult));
            }
            
            secondNum = secondNum == null ? text : secondNum;                // 给第二个操作数重新赋值                                                                            
            this.isSecondNum = true;                                        // 把isSecondNum标志为true
            return firstNum;
        }
                                                                                    
        public String setReciprocal(String text) {                            // 计算倒数
                                                                            
            if (text.equals("0")) {                                            // 如果text为0，则不求倒数
                return text;
            } else {                                                                            
                this.isSecondNum = true;                                        // 将isSecondNum标志为true                                                                            
                return String.valueOf(Math_lcp.divide(1, Double.valueOf(text)));// 计算结果并返回
            }
        }                                                                            
         
        public String sqrt(String text) {                                    // 计算开方
                                                                            
            this.isSecondNum = true;                                        // 将isSecondNum标志为true                                                                            
            return String.valueOf(Math.sqrt(Double.valueOf(text)));            // 计算结果并返回
        }

        public String setOp(String cmd, String text) {                        //设置操作符号
                                                                            
            this.lastOp = cmd;                                                // 将此操作符号设置为上次的操作            
            this.firstNum = text;                                            // 设置第一个操作数的值    代表下次输入数字时，要清空输入框并重新输入。        
            this.secondNum = null;                                            // 将第二个操作数赋值为空            
            this.isSecondNum = true;                                        // 将isSecondNum标志为true            
            return null;                                                    // 返回空值
        }
        
        public String setNegative(String text) {                            // 设置正负数
                                                    
            if (text.indexOf("-") == 0) {                                    // 如果text是负数，就将它变为正数
                return text.substring(1, text.length());
            }                                                                            
            return text.equals("0") ? text : "-" + text;                    // 否则，将正数变成负数
        }
        
        /*首先判断是第一个操作数还是第二个，如果是第一个，就把用户新输入的数字追加到原来数字的后面，并做为结果返回；
         * 如果是第二个，直接返回结果，并把isSecondNum标志为false，用户继续输入数字的时候，就把数字追加到原来数字的后面做为结果返回
         */
        public String catNum(String cmd, String text) {                        // 连接输入的数字，每次点击数字 把新加的数字追加到后面
            String result = cmd;
            
            if (!text.equals("0")) {                                        // 如果目前的text不等于0
                if (isSecondNum) {                                                                            
                    isSecondNum = false;                                    // 将isSecondNum标志为false
                } else {                                                                            
                    result = text + cmd;                                    // 刚返回结果为目前的text加上新点击的数字
                }
            }
            if (result.indexOf(".") == 0) {                                    // 如果有.开头，刚在前面补0
                result = "0" + result;
            }
            return result;
        }

        /*将用户输入的数字进行截取，如果接收到的字符串是“0”或者为null，则不作任何操作，
         * 直接返回，否则，我们将使用String的substring方法进行处理，将输入字符串的最后一位截取
         * */
        public String backSpace(String text) {                                // 实现backspace功能
            return text.equals("0") || text.equals("") ? "0" : text.substring(0,
                    text.length() - 1);
        }
        
        /*清除所有计算结果，把firstNum与secondNum都设置为原始值，并返回firstNum，
         * 在CalService中提供了一个clearAll方法，用于清除所有的计算结果。
         * */
        public String clearAll() {                                            // 清除所有计算结果
                                                                            
            this.firstNum = "0";                                            // 将第一第二操作数恢复为默认值
            this.secondNum = null;
            return this.firstNum;
        }

        public String clear(String text) {                                    // 清除上次计算结果
            return "0";
        }
        
        public double getStore() {                                            //返回存储器中的结果
            return this.store;
        }
}
