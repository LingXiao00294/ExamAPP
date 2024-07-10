package App.chinese.Student;

import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class Register {

    public void stdRegister(JFrame frame, JPanel panel) {
        panel.removeAll();
        panel.setLayout(null);
        getRegisterComponent(panel, frame);
        frame.revalidate();
        frame.repaint();
        frame.setVisible(true);
    }

    private void getRegisterComponent(JPanel panel, JFrame frame) {
        JLabel label = SwingUtils.createLabel("Student Register", 0, 10, 600, 50, 30);
        panel.add(label);

        JLabel label1 = SwingUtils.createLabel("Student ID:", 10, 70, 200, 30, 15);
        label1.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label1);

        JLabel label2 = SwingUtils.createLabel("Student Name:", 10, 110, 200, 30, 15);
        label2.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label2);

        JLabel label3 = SwingUtils.createLabel("手机号: ", 10, 150, 200, 30, 15);
        label3.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label3);

        JLabel label4 = SwingUtils.createLabel("Enter Password:", 10, 190, 200, 30, 15);
        label4.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label4);

        JLabel label5 = SwingUtils.createLabel("Confirm Password:", 10, 230, 200, 30, 15);
        label5.setHorizontalAlignment(SwingConstants.LEFT);
        panel.add(label5);

        JTextField textField1 = SwingUtils.createTextField(20, 200, 70, 200, 30, 15);
        panel.add(textField1);

        JTextField textField2 = SwingUtils.createTextField(20, 200, 110, 200, 30, 15);
        panel.add(textField2);

        JTextField textField3 = SwingUtils.createTextField(20, 200, 150, 200, 30, 15);
        panel.add(textField3);

        JPasswordField pwdField4 = SwingUtils.createPasswordField(20, 200, 190, 200, 30, 15);
        panel.add(pwdField4);

        JPasswordField pwdField5 = SwingUtils.createPasswordField(20, 200, 230, 200, 30, 15);
        panel.add(pwdField5);

        JButton buttonBack = SwingUtils.createButton("Back", 10, 20, 100, 30, 15);
        buttonBack.addActionListener(e -> {
            panel.removeAll();
            new Operation().getOperationComponent(frame, panel);
            panel.revalidate();
            panel.repaint();
        });
        panel.add(buttonBack);

        JButton buttonConfirm = SwingUtils.createButton("Confirm", 200, 300, 200, 40, 20);
        buttonConfirm.addActionListener(e -> {
            String id = textField1.getText();
            String name = textField2.getText();
            String phone = textField3.getText();
            String pwd = String.valueOf(pwdField4.getPassword());
            String pwd2 = String.valueOf(pwdField5.getPassword());

            if (!id.isEmpty() && !name.isEmpty() && !phone.isEmpty() && !pwd.isEmpty() && !pwd2.isEmpty()) {
                if (pwd.equals(pwd2)) {
                    int stdId = Integer.parseInt(id);
                    try {
                        Connection conn = DBUtils.getConnection();
                        PreparedStatement pstmt = conn.prepareStatement("INSERT INTO student VALUES (?, ?, ?, ?)");
                        pstmt.setInt(1, stdId);
                        pstmt.setString(2, name);
                        pstmt.setString(3, phone);
                        pstmt.setString(4, pwd);

                        int i = pstmt.executeUpdate();

                        if (i == 1) {
                            JOptionPane.showMessageDialog(frame, "Student registered successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                            panel.removeAll();
                            new Operation().getOperationComponent(frame, panel);
                            frame.revalidate();
                            frame.repaint();
                        }
                        DBUtils.closeResource(pstmt, conn);
                    } catch (SQLIntegrityConstraintViolationException ex) {
                        JOptionPane.showMessageDialog(frame, "Registration failed. Please check the student ID and try again.", "Error", JOptionPane.ERROR_MESSAGE);
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(frame, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        panel.add(buttonConfirm);
    }
}
