package App.english.Teacher;

import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddQuestion {
    String course = ""; // 定义课程变量，用于存储选择的课程名称

    // 声明文本字段组件，用于输入问题和答案
    private JTextField questionField;
    private JTextField ans1Field;
    private JTextField ans2Field;
    private JTextField ans3Field;
    private JTextField ans4Field;
    private JTextField answerField;

    // 获取新的问题编号（QNO）的方法
    private int getNewQNO() {
        int newQNO;
        try {
            String sql = "select * from question where course = ? order by `QNO` desc";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                newQNO = rs.getInt("QNO") + 1;
            } else {
                newQNO = 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return newQNO;
    }

    public void addQuestion(JFrame frame, JPanel panel) {
        panel.removeAll();
        getAddQuestionComponent(frame, panel);
        panel.revalidate();
        panel.repaint();
    }

    // 获取添加问题所需组件的方法
    private void getAddQuestionComponent(JFrame frame, JPanel panel) {
        JLabel label = SwingUtils.createLabel("Welcome leader!", 0, 10, 600, 50, 40);
        panel.add(label);

        JLabel label2 = SwingUtils.createLabel("Please choose a course", 0, 70, 600, 50, 25);
        panel.add(label2);

        JButton buttonJava = SwingUtils.createButton("Java", 200, 130, 200, 50, 30);
        JButton buttonMysql = SwingUtils.createButton("Mysql", 200, 190, 200, 50, 30);
        buttonMysql.addActionListener(e -> {
            course = "Mysql";
            addInputFields(frame, panel);
        });
        buttonJava.addActionListener(e -> {
            course = "Java";
            addInputFields(frame, panel);
        });
        panel.add(buttonJava);
        panel.add(buttonMysql);
        panel.revalidate();
        panel.repaint();
    }

    private void addInputFields(JFrame frame, JPanel panel) {
        panel.removeAll();

        JLabel label3 = SwingUtils.createLabel("Question: ", 10, 70, 200, 30, 15);
        JLabel label4 = SwingUtils.createLabel("Answer A: ", 10, 110, 200, 30, 15);
        JLabel label5 = SwingUtils.createLabel("Answer B: ", 10, 150, 200, 30, 15);
        JLabel label6 = SwingUtils.createLabel("Answer C: ", 10, 190, 200, 30, 15);
        JLabel label7 = SwingUtils.createLabel("Answer D: ", 10, 230, 200, 30, 15);
        JLabel label8 = SwingUtils.createLabel("Current Answer: ", 10, 270, 200, 30, 15);

        panel.add(label3);
        panel.add(label4);
        panel.add(label5);
        panel.add(label6);
        panel.add(label7);
        panel.add(label8);

        questionField = SwingUtils.createTextField(20, 210, 70, 300, 30, 15);
        ans1Field = SwingUtils.createTextField(20, 210, 110, 300, 30, 15);
        ans2Field = SwingUtils.createTextField(20, 210, 150, 300, 30, 15);
        ans3Field = SwingUtils.createTextField(20, 210, 190, 300, 30, 15);
        ans4Field = SwingUtils.createTextField(20, 210, 230, 300, 30, 15);
        answerField = SwingUtils.createTextField(20, 210, 270, 100, 30, 15);
        panel.add(questionField);
        panel.add(ans1Field);
        panel.add(ans2Field);
        panel.add(ans3Field);
        panel.add(ans4Field);
        panel.add(answerField);

        JButton buttonConfirm = SwingUtils.createButton("Confirm", 100, 330, 150, 40, 20);
        buttonConfirm.addActionListener(e -> confirmAddQuestion(frame));
        panel.add(buttonConfirm);

        JButton buttonBack = SwingUtils.createButton("Back to Admin Page", 300, 330, 200, 40, 20);
        buttonBack.addActionListener(e -> backToAdmin(frame, panel));
        panel.add(buttonBack);

        panel.revalidate();
        panel.repaint();
    }

    private void confirmAddQuestion(JFrame frame) {
        String qname = questionField.getText();
        String ans1 = ans1Field.getText();
        String ans2 = ans2Field.getText();
        String ans3 = ans3Field.getText();
        String ans4 = ans4Field.getText();
        String answer = answerField.getText().toUpperCase();

        if (course.isEmpty() || qname.isEmpty() || ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int newQNO = getNewQNO();

        try {
            String sql = "INSERT INTO question VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, course);
            pstmt.setInt(2, newQNO);
            pstmt.setString(3, qname);
            pstmt.setString(4, ans1);
            pstmt.setString(5, ans2);
            pstmt.setString(6, ans3);
            pstmt.setString(7, ans4);
            pstmt.setString(8, answer);
            int i = pstmt.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(frame, "Question added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while adding the question.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void clearFields() {
        questionField.setText("");
        ans1Field.setText("");
        ans2Field.setText("");
        ans3Field.setText("");
        ans4Field.setText("");
        answerField.setText("");
    }

    private void backToAdmin(JFrame frame, JPanel panel) {
        panel.removeAll();
        new Administrator().administer(frame, panel);
        panel.revalidate();
        panel.repaint();
    }
}