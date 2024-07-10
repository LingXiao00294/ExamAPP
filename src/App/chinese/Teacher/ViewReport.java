package App.chinese.Teacher;

import model.Result;
import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ViewReport {

    private final List<Result> results = new ArrayList<>();
    private int page = 0;//当前页
    private int pageSize;//最大页数
    private String searchText = "";//搜索文本
    private String sort = "SNO";

    public void viewReport(JFrame frame, JPanel panel) {
        System.out.println("ViewReport.viewReport");
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select count(*) from result");
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int n = rs.getInt(1);
                pageSize = n / 10 + (n % 10 == 0 ? 0 : 1);
            } else {
                JOptionPane.showMessageDialog(frame, "No data found", "Error", JOptionPane.ERROR_MESSAGE);
                frame.dispose();
            }
            DBUtils.closeResource(rs, pstmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        defaultUpdateResultList();
        displayResult(frame, panel);
    }

    private void displayResult(JFrame frame, JPanel panel) {
        System.out.println("ViewReport.displayResult");
        panel.removeAll();

        JLabel labelSNO = SwingUtils.createLabel("NO", 15, 65, 60, 25, 20);
        JLabel labelSName = SwingUtils.createLabel("Name", 75, 65, 80, 25, 20);
        JLabel labelCourse = SwingUtils.createLabel("Course", 155, 65, 100, 25, 20);
        JLabel labelScore = SwingUtils.createLabel("Score", 255, 65, 60, 25, 20);
        JLabel labelDate = SwingUtils.createLabel("Date", 350, 65, 300, 25, 20);
        panel.add(labelSNO);
        panel.add(labelSName);
        panel.add(labelCourse);
        panel.add(labelScore);
        panel.add(labelDate);

        if (!results.isEmpty()) {
            JLabel labelSNO1 = SwingUtils.createLabel(results.getFirst().getSno() + "", 15, 100, 60, 25, 20);
            panel.add(labelSNO1);
            JLabel labelSNAME1 = SwingUtils.createLabel(results.getFirst().getSname(), 75, 100, 80, 25, 20);
            panel.add(labelSNAME1);
            JLabel labelCOURSE1 = SwingUtils.createLabel(results.getFirst().getCourse(), 155, 100, 100, 25, 20);
            panel.add(labelCOURSE1);
            JLabel labelSCORE1 = SwingUtils.createLabel(results.getFirst().getScore() + "", 255, 100, 60, 25, 20);
            panel.add(labelSCORE1);
            JLabel labelDATE1 = SwingUtils.createLabel(results.getFirst().getDate(), 350, 100, 300, 25, 20);
            panel.add(labelDATE1);
        }

        if (results.size() >= 2) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(1).getSno() + "", 15, 135, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(1).getSname(), 75, 135, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(1).getCourse(), 155, 135, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(1).getScore() + "", 255, 135, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(1).getDate(), 350, 135, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 3) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(2).getSno() + "", 15, 170, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(2).getSname(), 75, 170, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(2).getCourse(), 155, 170, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(2).getScore() + "", 255, 170, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(2).getDate(), 350, 170, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 4) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(3).getSno() + "", 15, 205, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(3).getSname(), 75, 205, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(3).getCourse(), 155, 205, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(3).getScore() + "", 255, 205, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(3).getDate(), 350, 205, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 5) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(4).getSno() + "", 15, 240, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(4).getSname(), 75, 240, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(4).getCourse(), 155, 240, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(4).getScore() + "", 255, 240, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(4).getDate(), 350, 240, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 6) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(5).getSno() + "", 15, 275, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(5).getSname(), 75, 270, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(5).getCourse(), 155, 275, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(5).getScore() + "", 255, 275, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(5).getDate(), 350, 275, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 7) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(6).getSno() + "", 15, 310, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(6).getSname(), 75, 310, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(6).getCourse(), 155, 310, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(6).getScore() + "", 310, 135, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(6).getDate(), 350, 310, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 8) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(7).getSno() + "", 15, 345, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(7).getSname(), 75, 345, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(7).getCourse(), 155, 345, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(7).getScore() + "", 345, 135, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(7).getDate(), 350, 345, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 9) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(8).getSno() + "", 15, 380, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(8).getSname(), 75, 380, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(8).getCourse(), 155, 380, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(8).getScore() + "", 255, 380, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(8).getDate(), 350, 380, 300, 25, 20);
            panel.add(labelDATE2);
        }

        if (results.size() >= 10) {
            JLabel labelSNO2 = SwingUtils.createLabel(results.get(9).getSno() + "", 15, 415, 60, 25, 20);
            panel.add(labelSNO2);
            JLabel labelSNAME2 = SwingUtils.createLabel(results.get(9).getSname(), 75, 415, 80, 25, 20);
            panel.add(labelSNAME2);
            JLabel labelCOURSE2 = SwingUtils.createLabel(results.get(9).getCourse(), 155, 415, 100, 25, 20);
            panel.add(labelCOURSE2);
            JLabel labelSCORE2 = SwingUtils.createLabel(results.get(9).getScore() + "", 255, 415, 60, 25, 20);
            panel.add(labelSCORE2);
            JLabel labelDATE2 = SwingUtils.createLabel(results.get(9).getDate(), 350, 415, 300, 25, 20);
            panel.add(labelDATE2);
        }


        JButton buttonBack = SwingUtils.createButton("Back", 10, 10, 75, 30, 15);
        buttonBack.addActionListener(e -> frame.dispose());
        panel.add(buttonBack);

        JButton buttonRefresh = SwingUtils.createButton("Refresh", 100, 10, 150, 30, 15);
        buttonRefresh.addActionListener(e -> {
            defaultUpdateResultList();
            displayResult(frame, panel);
        });
        panel.add(buttonRefresh);

        JTextArea currentPage = SwingUtils.createTextArea((page + 1) + "", 85, 470, 25, 27, 20);
        currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        currentPage.setEditable(true);
        currentPage.setOpaque(true);
        panel.add(currentPage);

        JTextArea textPage = SwingUtils.createTextArea("Page     of " + pageSize, 30, 470, 140, 30, 20);
        currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        panel.add(textPage);

        JButton buttonGo = SwingUtils.createButton("Go", 170, 470, 70, 30, 15);
        buttonGo.addActionListener(e -> {
            String text = currentPage.getText();
            try {
                int newPage = Integer.parseInt(text.trim()) - 1;
                if (newPage >= 0 && newPage < pageSize) {
                    page = newPage;
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid page number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                defaultUpdateResultList();
                displayResult(frame, panel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid page number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        addEnterKeyListenerToButtonGo(currentPage, buttonGo);
        panel.add(buttonGo);

        JButton buttonPreviousPage = SwingUtils.createButton("Previous", 250, 470, 100, 30, 15);
        buttonPreviousPage.addActionListener(e -> {
            page--;
            defaultUpdateResultList();
            displayResult(frame, panel);
        });
        buttonPreviousPage.setEnabled(page != 0);
        panel.add(buttonPreviousPage);

        JButton buttonNextPage = SwingUtils.createButton("Next", 360, 470, 100, 30, 15);
        buttonNextPage.addActionListener(e -> {
            page++;
            defaultUpdateResultList();
            displayResult(frame, panel);
        });
        buttonNextPage.setEnabled(page < pageSize - 1);
        panel.add(buttonNextPage);

        JTextField searchFrame = SwingUtils.createTextField(40, 470, 470, 230, 30, 15);
        searchFrame.setText(searchText);
        panel.add(searchFrame);

        JButton buttonSearch = SwingUtils.createButton("Search", 710, 470, 70, 30, 10);
        buttonSearch.addActionListener(e -> {
            searchText = searchFrame.getText();
            defaultUpdateResultList();
            page = 0;
            displayResult(frame, panel);
        });
        addEnterKeyListenerToButtonSearch(searchFrame, buttonSearch);
        panel.add(buttonSearch);

        frame.add(panel);
        panel.revalidate();
        panel.repaint();
    }

    public void defaultUpdateResultList() {
        System.out.println("ViewReport.defaultUpdateResultList");

        results.clear();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from result where SNAME like ? order by ? limit ?, 10");
            pstmt.setString(1, "%" + searchText + "%");
            pstmt.setString(2, sort);
            pstmt.setInt(3, page * 10);
            ResultSet rs = pstmt.executeQuery();
            writeResultToList(rs);
            DBUtils.closeResource(rs, pstmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void addEnterKeyListenerToButtonGo(JTextArea currentPage, JButton buttonGo) {
        System.out.println("ViewQuestion.addEnterKeyListenerToButtonGo");
        currentPage.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonGo.doClick(); // 模拟按钮点击
                }
            }
        });
    }

    private void addEnterKeyListenerToButtonSearch(JTextField searchFrame, JButton buttonSearch) {
        System.out.println("ViewQuestion.addEnterKeyListenerToButtonSearch");
        searchFrame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    buttonSearch.doClick();
                }
            }
        });
    }

    private void writeResultToList(ResultSet rs) throws SQLException {
        while (rs.next()) {
            Result r = new Result();
            r.setSno(rs.getLong("sno"));
            r.setSname(rs.getString("sname"));
            r.setCourse(rs.getString("course"));
            r.setScore(rs.getDouble("score"));
            r.setDate(rs.getString("date"));
            results.add(r);
        }
    }
}
