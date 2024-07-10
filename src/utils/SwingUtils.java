package utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SwingUtils {

    // 创建并返回一个子窗口（JFrame），当子窗口关闭时，原窗口（frame）会重新变为可见
    public static JFrame getSubFrame(JFrame frame) {
        frame.setVisible(false); // 隐藏原窗口
        JFrame subFrame = new JFrame(); // 创建一个新的JFrame作为子窗口
        subFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE); // 设置默认关闭操作为仅销毁窗体
        subFrame.setSize(600, 450); // 设置子窗口的大小
        // 添加窗口关闭监听器，当子窗口关闭时，使原窗口可见
        subFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                frame.setVisible(true);
            }
        });
        subFrame.setLocationRelativeTo(null); // 设置子窗口位置居中于屏幕
        subFrame.setVisible(true); // 显示子窗口
        return subFrame; // 返回子窗口实例
    }

    // 创建并返回一个居中对齐的标签（JLabel），支持自定义文本、位置、大小和字体大小
    public static JLabel createLabel(String text, int x, int y, int w, int h, int size) {
        JLabel label = new JLabel(text); // 创建JLabel
        label.setHorizontalAlignment(JLabel.CENTER); // 设置文本居中对齐
        label.setFont(new Font("微软雅黑", Font.BOLD, size)); // 设置字体样式和大小
        label.setBounds(x, y, w, h); // 设置标签的位置和大小
        return label; // 返回JLabel实例
    }

    // 创建并返回一个居中对齐的按钮（JButton），支持自定义文本、位置、大小和字体大小
    public static JButton createButton(String text, int x, int y, int w, int h, int size) {
        JButton button = new JButton(text); // 创建JButton
        button.setHorizontalAlignment(JButton.CENTER); // 设置文本居中对齐
        button.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体样式和大小
        button.setBounds(x, y, w, h); // 设置按钮的位置和大小
        return button; // 返回JButton实例
    }

    // 创建并返回一个文本输入框（JTextField），支持指定列数、位置、大小和字体大小
    public static JTextField createTextField(int n, int x, int y, int w, int h, int size) {
        JTextField textField = new JTextField(n); // 创建JTextField，n为列数
        textField.setHorizontalAlignment(JTextField.LEFT); // 设置文本左对齐
        textField.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体样式和大小
        textField.setBounds(x, y, w, h); // 设置文本框的位置和大小
        return textField; // 返回JTextField实例
    }

    // 创建并返回一个密码输入框（JPasswordField），支持指定列数、位置、大小和字体大小
    public static JPasswordField createPasswordField(int n, int x, int y, int w, int h, int size) {
        JPasswordField passwordField = new JPasswordField(n); // 创建JPasswordField，n为列数
        passwordField.setHorizontalAlignment(JPasswordField.LEFT); // 设置密码文本左对齐
        passwordField.setFont(new Font("微软雅黑", Font.PLAIN, size)); // 设置字体样式和大小
        passwordField.setBounds(x, y, w, h); // 设置密码框的位置和大小
        return passwordField; // 返回JPasswordField实例
    }

    // 创建并返回一个不可编辑的文本区域（JTextArea），通常用于展示信息，支持自定义文本、位置、大小和字体大小
    public static JTextArea createTextArea(String text, int x, int y, int w, int h, int size) {
        JTextArea textArea = new JTextArea(text); // 创建JTextArea
        textArea.setFont(new Font("微软雅黑", Font.BOLD, size)); // 设置字体样式和大小
        textArea.setBounds(x, y, w, h); // 设置文本区域的位置和大小
        textArea.setEditable(false); // 设置为不可编辑
        textArea.setBorder(BorderFactory.createEmptyBorder()); // 移除边框
        textArea.setOpaque(false); // 设置透明，以便背景色能透过
        textArea.setLineWrap(true); // 启用自动换行
        return textArea; // 返回JTextArea实例
    }
}