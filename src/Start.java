import App.english.Student.Operation;
import App.english.Teacher.Login;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Start {

    public static void main(String[] args) {
        // 创建主框架
        JFrame frame = new JFrame("ExamApp");
        frame.setBounds(500, 300, 600, 450);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // 获取初始面板
        JPanel panel1 = getPanel(frame);

        frame.add(panel1);
        frame.setVisible(true);
    }

    private static JPanel getPanel(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);

        addComponent(frame, panel);
        return panel;
    }

    private static void addComponent(JFrame frame, JPanel panel) {
        JLabel jl1 = new JLabel("Welcome to the exam management System.");
        jl1.setBounds(10, 10, 600, 50);
        jl1.setHorizontalAlignment(JLabel.CENTER);
        jl1.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(jl1);

        JLabel jl2 = new JLabel("Please choose a language.");
        jl2.setBounds(10, 70, 600, 50);
        jl2.setHorizontalAlignment(JLabel.CENTER);
        jl2.setFont(new Font("Times New Roman", Font.BOLD, 20));
        panel.add(jl2);

        // 创建英文按钮
        JButton buttonEN = SwingUtils.createButton("English", 175, 250, 250, 100, 50);
        buttonEN.addActionListener(e -> {
            updatePanelForEnglish(panel, frame);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(buttonEN);

        // 创建中文按钮
        JButton buttonZH = SwingUtils.createButton("简体中文", 175, 130, 250, 100, 50);
        buttonZH.addActionListener(e -> {
            updatePanelForChinese(panel, frame);
            frame.revalidate();
            frame.repaint();
        });
        panel.add(buttonZH);
    }

    private static void updatePanelForEnglish(JPanel panel, JFrame frame) {
        // 清除所有组件
        panel.removeAll();

        // 设置标签字体
        Font fontLabel = new Font("微软雅黑", Font.BOLD, 20);

        // 创建英文欢迎标签
        JLabel jl = new JLabel("Welcome to the English section.");
        jl.setBounds(10, 10, 600, 50);
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setFont(fontLabel);
        panel.add(jl);

        // 创建学生按钮
        JButton buttonStudent = new JButton("Student");
        buttonStudent.setBounds(200, 100, 200, 100);
        buttonStudent.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        buttonStudent.addActionListener(e -> {
            JFrame subFrame = SwingUtils.getSubFrame(frame);
            Operation op = new Operation();
            op.operationStudent(subFrame);
        });
        panel.add(buttonStudent);

        // 创建教师按钮
        JButton buttonTeacher = new JButton("Teacher");
        buttonTeacher.setBounds(200, 210, 200, 100);
        buttonTeacher.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        buttonTeacher.addActionListener(e -> {
            JFrame subFrame = SwingUtils.getSubFrame(frame);
            Login login = new Login();
            login.login(subFrame);
        });
        panel.add(buttonTeacher);

        // 创建返回按钮
        JButton buttonBack = new JButton("Back");
        buttonBack.setBounds(225, 320, 150, 50);
        buttonBack.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        buttonBack.addActionListener(e -> returnToInitialPanel(panel, frame));
        panel.add(buttonBack);
    }

    private static void updatePanelForChinese(JPanel panel, JFrame frame) {
        // 清除所有组件
        panel.removeAll();

        // 设置标签字体
        Font fontLabel = new Font("微软雅黑", Font.BOLD, 20);

        // 创建中文欢迎标签
        JLabel jl = new JLabel("欢迎来到中文选择");
        jl.setBounds(10, 10, 600, 50);
        jl.setHorizontalAlignment(JLabel.CENTER);
        jl.setFont(fontLabel);
        panel.add(jl);

        // 创建学生按钮
        JButton buttonStudent = new JButton("学生");
        buttonStudent.setBounds(200, 100, 200, 100);
        buttonStudent.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        buttonStudent.addActionListener(e -> {
            JFrame subFrame = SwingUtils.getSubFrame(frame);
            new App.chinese.Student.Operation().operationStudent(subFrame);
            subFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    super.windowClosing(e);
                }
            });
        });
        panel.add(buttonStudent);

        // 创建教师按钮
        JButton buttonTeacher = new JButton("老师");
        buttonTeacher.setBounds(200, 210, 200, 100);
        buttonTeacher.setFont(new Font("微软雅黑", Font.PLAIN, 40));
        buttonTeacher.addActionListener(e -> {
            JFrame subFrame = SwingUtils.getSubFrame(frame);
            subFrame.setLocationRelativeTo(null);
            new App.chinese.Teacher.Login().login(subFrame);
        });
        panel.add(buttonTeacher);

        // 创建返回按钮
        JButton buttonBack = new JButton("返回");
        buttonBack.setBounds(225, 320, 150, 50);
        buttonBack.setFont(new Font("微软雅黑", Font.PLAIN, 30));
        buttonBack.addActionListener(e -> returnToInitialPanel(panel, frame));
        panel.add(buttonBack);
    }

    private static void returnToInitialPanel(JPanel panel, JFrame frame) {
        // 清除所有组件并添加初始组件
        panel.removeAll();
        addComponent(frame, panel);

        frame.revalidate();
        frame.repaint();
    }
}
