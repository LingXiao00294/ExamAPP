package App.english.Teacher;

import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    public void login(JFrame frame) {
        JPanel panel = new JPanel();
        panel.removeAll();
        panel.setLayout(null);
        getLoginComponent(frame, panel);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void getLoginComponent(JFrame frame, JPanel panel) {
        JLabel label1 = SwingUtils.createLabel("Teacher Login.", 0, 10, 600, 50, 30);
        panel.add(label1);

        JLabel label2 = SwingUtils.createLabel("Enter Username:", 10, 70, 200, 30, 15);
        label2.setHorizontalAlignment(2);
        panel.add(label2);

        JLabel label3 = SwingUtils.createLabel("Enter Password:", 10, 110, 200, 30, 15);
        label3.setHorizontalAlignment(2);
        panel.add(label3);

        JTextField textField1 = SwingUtils.createTextField(20, 200, 70, 200, 30, 15);
        panel.add(textField1);

        JPasswordField passwordField1 = SwingUtils.createPasswordField(20, 200, 110, 200, 30, 15);
        panel.add(passwordField1);

        JButton buttonConfirm = SwingUtils.createButton("Confirm", 200, 250, 200, 40, 20);
        buttonConfirm.addActionListener(e -> {
            String username = textField1.getText();
            String password = String.valueOf(passwordField1.getPassword());
            try {
                String sql = "select * from teacher where username = ? and password = ?";
                Connection conn = DBUtils.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1, username);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    JOptionPane.showMessageDialog(frame, "Login success!", "success", JOptionPane.INFORMATION_MESSAGE);
                    new Administrator().administer(frame, panel);
                } else {
                    JOptionPane.showMessageDialog(frame, "Login failed!\nPlease try again!", "error", JOptionPane.ERROR_MESSAGE);
                    panel.removeAll();
                    getLoginComponent(frame, panel);
                    panel.revalidate();
                    panel.repaint();
                }
                DBUtils.closeResource(rs, pstmt, conn);
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        panel.add(buttonConfirm);

        JButton buttonBack = SwingUtils.createButton("Back", 10, 10, 100, 30, 15);
        buttonBack.addActionListener(e -> {
            frame.dispose();
        });
        panel.add(buttonBack);
        addKeyListenerToButtonConfirm(passwordField1, buttonConfirm);
    }

    private void addKeyListenerToButtonConfirm(JPasswordField passwordField, JButton buttonConfirm) {
        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonConfirm.doClick();
                }
            }
        });
    }
}
