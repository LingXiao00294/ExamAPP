package App.chinese.Student;

import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public void login(JFrame frame, JPanel panel) {
        panel.removeAll();
        panel.setLayout(null);
        getLoginComponent(frame, panel);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void getLoginComponent(JFrame frame, JPanel panel) {
        JLabel label = SwingUtils.createLabel("学生登录", 0, 10, 600, 50, 30);
        panel.add(label);

        JLabel label1 = SwingUtils.createLabel("Student ID:", 10, 70, 200, 30, 15);
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label1);

        JLabel label2 = SwingUtils.createLabel("Password:", 10, 110, 200, 30, 15);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label2);

        JTextField textField1 = SwingUtils.createTextField(20, 200, 70, 200, 30, 15);
        panel.add(textField1);

        JPasswordField passwordField = SwingUtils.createPasswordField(20, 200, 110, 200, 30, 15);
        panel.add(passwordField);

        JButton buttonBack = SwingUtils.createButton("Back", 10, 20, 100, 30, 15);
        buttonBack.addActionListener(e -> {
            panel.removeAll();
            new Operation().getOperationComponent(frame, panel);
            panel.revalidate();
            panel.repaint();
        });
        panel.add(buttonBack);

        JButton buttonLogin = SwingUtils.createButton("Login", 200, 170, 200, 40, 20);
        buttonLogin.addActionListener(e -> {

            String studentId = textField1.getText();
            String password = new String(passwordField.getPassword());

            try {
                Connection conn = DBUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student WHERE sno = ? AND password = ?");
                pstmt.setString(1, studentId);
                pstmt.setString(2, password);

                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Student Login Success", "Success", JOptionPane.INFORMATION_MESSAGE);
                    new Exam(studentId, rs.getString("sname")).chooseCourse(frame, panel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Student Login Failed", "Error", JOptionPane.ERROR_MESSAGE);
                    panel.removeAll();
                    getLoginComponent(frame, panel);
                    panel.revalidate();
                    panel.repaint();
                }
                DBUtils.closeResource(rs, pstmt, conn);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(frame, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                throw new RuntimeException(ex);
            }
        });
        panel.add(buttonLogin);
    }
}
