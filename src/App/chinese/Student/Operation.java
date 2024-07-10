package App.chinese.Student;

import utils.SwingUtils;

import javax.swing.*;

public class Operation {

    public void operationStudent(JFrame frame) {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        getOperationComponent(frame, panel);
        frame.add(panel);
        frame.setVisible(true);
    }

    public void getOperationComponent(JFrame frame, JPanel panel) {
        JLabel jl1 = SwingUtils.createLabel("注册或登录", 10, 10, 600, 50, 20);
        panel.add(jl1);

        JButton buttonRegister = SwingUtils.createButton("Register", 175, 100, 250, 100, 50);
        buttonRegister.addActionListener(e -> new Register().stdRegister(frame, panel));
        panel.add(buttonRegister);

        JButton buttonLogin = SwingUtils.createButton("登录", 175, 225, 250, 100, 50);
        buttonLogin.addActionListener(e -> new Login().login(frame, panel));
        panel.add(buttonLogin);

        JButton buttonBack = SwingUtils.createButton("Back", 10, 10, 100, 30, 15);
        buttonBack.addActionListener(e -> {
            frame.dispose();
        });
        panel.add(buttonBack);
    }
}
