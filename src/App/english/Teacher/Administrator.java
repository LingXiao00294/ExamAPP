package App.english.Teacher;

import utils.SwingUtils;

import javax.swing.*;

public class Administrator {

    public void administer(JFrame frame, JPanel panel) {
        panel.removeAll();
        panel.setLayout(null);
        getAdministerComponent(frame, panel);
    }

    public void getAdministerComponent(JFrame frame, JPanel panel) {
        panel.removeAll();

        JLabel label1 = SwingUtils.createLabel("Administer", 0, 20, 600, 100, 40);
        panel.add(label1);

        JButton buttonAdd = SwingUtils.createButton("Add question", 150, 130, 300, 50, 30);
        buttonAdd.addActionListener(e -> {
            new AddQuestion().addQuestion(frame, panel);
        });
        panel.add(buttonAdd);

        JButton buttonViewQuestion = SwingUtils.createButton("View question", 150, 190, 300, 50, 30);
        buttonViewQuestion.addActionListener(e -> {
            new ViewQuestion().viewQuestion(frame, panel);
        });
        panel.add(buttonViewQuestion);

        JButton buttonViewReport = SwingUtils.createButton("View report", 150, 250, 300, 50, 30);
        buttonViewReport.addActionListener(e -> {
            JFrame subFrame = SwingUtils.getSubFrame(frame);
            subFrame.setSize(1000, 600);
            subFrame.setLocationRelativeTo(null);
            JPanel subPanel = new JPanel();
            subPanel.setLayout(null);
            new ViewReport().viewReport(subFrame, subPanel);
        });
        panel.add(buttonViewReport);

        JButton buttonLogout = SwingUtils.createButton("Logout", 150, 310, 300, 50, 30);
        buttonLogout.addActionListener(e -> {
            panel.removeAll();
            new Login().getLoginComponent(frame, panel);
            panel.revalidate();
            panel.repaint();
        });
        panel.add(buttonLogout);
        panel.revalidate();
        panel.repaint();

    }
}
