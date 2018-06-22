/*界面布局：*/
package cal;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CalFrame extends JFrame {
    
    private JTextField textField = null;                        // 显示计算结果的textField
    
    private String[] rOp = { "Back", "CE", "C" };                // 用一个数组保存结果操作符
                                                                // 用一个数组保存数字与其它操作符
    private String[] nOp = { "7", "8", "9", "/", "sqrt", "4", "5", "6", "*",
            "%", "1", "2", "3", "-", "1/x", "0", "+/-", ".", "+", "=" };
    
    private CalService service = new CalService();                // 业务逻辑类
    
    private ActionListener actionListener = null;                // 定义监听器
    
    private final int PRE_WIDTH = 360;                            // 设置panel的宽
    
    private final int PRE_HEIGHT = 250;                            // 设置panel的高

    
    
    public CalFrame() {                                            // 默认构造器
        super();
        init();
    }

    
    private void init() {                                                // 初始化界面
        
        this.setTitle("我的第一个计算器");                                    // 设置窗口的标题        
        this.setResizable(false);                                        // 设置为不可改变大小
        
        JPanel panel = new JPanel();                                    // 增加计算输入框
        panel.setLayout(new BorderLayout(10, 1));
        panel.add(getTextField(), BorderLayout.NORTH);
        panel.setPreferredSize(new Dimension(PRE_WIDTH, PRE_HEIGHT));        
        
        JButton[] rButton = getRButton();                                // 增加结果操作键
        JPanel panel2 = new JPanel();
        panel2.setLayout(new BorderLayout(1, 5));        
        
        JPanel panel21 = new JPanel();                                    // 新建一个panel，用于放置按钮        
        panel21.setLayout(new GridLayout(1, 3, 3, 3));                    // 设置布局管理器        
        for (JButton b : rButton) {                                        // 迭代增加按钮
            panel21.add(b);
        }        
        
        JButton[] nButton = getNButton();                                // 增加数字与其它运算符        
        JPanel panel22 = new JPanel();                                    // 新建一个panel，用于放置按钮        
        panel22.setLayout(new GridLayout(4, 5, 3, 5));                    // 设置布局管理器        
        for (JButton b : nButton) {                                        // 迭代增加按钮
            panel22.add(b);
        }
        
        panel2.add(panel21, BorderLayout.NORTH);                        // 把新增加的面板加到frame
        panel2.add(panel22, BorderLayout.CENTER);
        panel.add(panel2, BorderLayout.CENTER);
        this.add(panel);
    }

    public ActionListener getActionListener() {                            // 这个方法用来获取监听器
        if (actionListener == null) {
            actionListener = new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String cmd = e.getActionCommand();
                    String result = null;
                    try {                                                // 计算操作结果                        
                        result = service.callMethod(cmd, textField.getText());
                    } catch (Exception e1) {
                        System.out.println(e1.getMessage());
                    }                    
                    if (result != null) {                                // 设置计算结果
                        textField.setText(result);
                    }
                }
            };
        }
        return actionListener;
    }
    

    
    private JTextField getTextField() {                        // 这个方法初始化输入框
        
        if (textField == null) {            
            textField = new JTextField("0");                // 设置默认值为0            
            textField.setEditable(false);                    // 设置为不可编辑            
            textField.setBackground(Color.white);            // 设置背景为白色
        }
        return textField;
    }

    private JButton[] getRButton() {                        // 此方法获得计算器的结果操作键
        JButton[] result = new JButton[rOp.length];
        for (int i = 0; i < this.rOp.length; i++) {            
            JButton b = new JButton(this.rOp[i]);            // 新建按钮            
            b.addActionListener(getActionListener());        // 为按钮增加事件            
            b.setForeground(Color.red);                        // 设置按钮颜色
            result[i] = b;
        }
        return result;
    }
    
    private JButton[] getNButton() {                        // 此方法获得计算器的其它操作键        
        String[] redButton = { "/", "*", "-", "+", "=" };    // 这个数组保存需要设置为红色的操作符
        JButton[] result = new JButton[nOp.length];
        for (int i = 0; i < this.nOp.length; i++) {
            
            JButton b = new JButton(this.nOp[i]);            // 新建按扭            
            b.addActionListener(getActionListener());        // 为按钮增加事件            
            Arrays.sort(redButton);                            // 对redButton排序，才可以使用binarySearch方法
                                                            // 如果操作符在redButton出现
            if (Arrays.binarySearch(redButton, nOp[i]) >= 0) {
                b.setForeground(Color.red);
            } else {
                b.setForeground(Color.blue);
            }
            result[i] = b;
        }
        return result;
    }
}