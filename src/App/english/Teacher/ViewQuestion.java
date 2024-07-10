package App.english.Teacher;

import model.Questions;
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

public class ViewQuestion {
    private static final List<Questions> qs = new ArrayList<>();
    int pageSize = 0; //总页数
    String searchText = ""; //搜索内容
    String sort = "COURSE";
    private String course; //科目
    private int n; //总题数
    private int page = 0; //当前页
    private JTextArea questionArea;
    private JTextField ans1Field;
    private JTextField ans2Field;
    private JTextField ans3Field;
    private JTextField ans4Field;
    private JTextField answerField;

    private static void writeQuestionToList(ResultSet rs) throws SQLException {
        System.out.println("ViewQuestion.writeQuestionToList");
        while (rs.next()) {
            Questions q = new Questions();
            q.setCourse(rs.getString("course"));
            q.setQno(rs.getInt("qno"));
            q.setQname(rs.getString("qname"));
            q.setAns1(rs.getString("ans1"));
            q.setAns2(rs.getString("ans2"));
            q.setAns3(rs.getString("ans3"));
            q.setAns4(rs.getString("ans4"));
            q.setAnswer(rs.getString("answer"));
            qs.add(q);
        }
    }

    public void viewQuestion(JFrame frame, JPanel panel) {
        System.out.println("ViewQuestion.viewQuestion");
        panel.removeAll();
        panel.setLayout(null);
        getCourseComponent(frame, panel);

    }

    private void getCourseComponent(JFrame frame, JPanel panel) {
        System.out.println("ViewQuestion.getCourseComponent");
        JLabel label = SwingUtils.createLabel("Choose course", 0, 10, 600, 50, 40);
        panel.add(label);

        JButton buttonBack = SwingUtils.createButton("Back", 10, 10, 100, 30, 15);
        buttonBack.addActionListener(e -> new Administrator().getAdministerComponent(frame, panel));
        panel.add(buttonBack);

        JButton buttonJava = SwingUtils.createButton("Java", 200, 100, 200, 50, 40);
        buttonJava.addActionListener(e -> {
            course = "Java";
            viewQuestionComponent(frame);
        });
        panel.add(buttonJava);

        JButton buttonMysql = SwingUtils.createButton("Mysql", 200, 200, 200, 50, 40);
        buttonMysql.addActionListener(e -> {
            course = "Mysql";
            viewQuestionComponent(frame);
        });
        panel.add(buttonMysql);

        JButton buttonAll = SwingUtils.createButton("All", 200, 300, 200, 50, 40);
        buttonAll.addActionListener(e -> {
            course = "%";
            viewQuestionComponent(frame);
        });
        panel.add(buttonAll);
        panel.revalidate();
        panel.repaint();
    }

    public void viewQuestionComponent(JFrame frame) {
        System.out.println("ViewQuestion.viewQuestionComponent");
        qs.clear();
        page = 0;
        try {
            Connection conn0 = DBUtils.getConnection();
            PreparedStatement pstmt0 = conn0.prepareStatement("select count(*) from question where COURSE like ?");
            pstmt0.setString(1, course);
            ResultSet rs0 = pstmt0.executeQuery();
            if (rs0.next()) {
                n = rs0.getInt(1);
            } else JOptionPane.showMessageDialog(frame, "No question found", "Error", JOptionPane.ERROR_MESSAGE);
            pageSize = n / 5 + (n % 5 == 0 ? 0 : 1);
            DBUtils.closeResource(rs0, pstmt0, conn0);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        JFrame subFrame = SwingUtils.getSubFrame(frame);
        subFrame.setSize(830, 600);
        subFrame.setLocationRelativeTo(null);
        JPanel subPanel = new JPanel();
        subPanel.setLayout(null);
        defaultUpdateQuestionList();
        displayQuestion(subFrame, subPanel);
    }

    private void displayQuestion(JFrame subFrame, JPanel subPanel) {
        System.out.println("ViewQuestion.displayQuestion");

        subPanel.removeAll();

//        JLabel labelQNO = SwingUtils.createLabel("QNO", 15, 65, 60, 25, 20);
//        JLabel labelQuestion = SwingUtils.createLabel("Question", 200, 65, 100, 25, 20);
//        JLabel labelAnswer = SwingUtils.createLabel("Answer", 550, 65, 100, 25, 20);
//        subPanel.add(labelQNO);subPanel.add(labelQuestion);subPanel.add(labelAnswer);

        JButton buttonQNO = SwingUtils.createButton("QNO", 15, 65, 60, 25, 10);
        buttonQNO.addActionListener(e -> {
            if (sort.equals("QNO")) {
                sort = "QNO DESC";
            } else sort = "QNO";
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        JButton buttonCourse = SwingUtils.createButton("Course", 90, 65, 100, 25, 10);
        buttonCourse.addActionListener(e -> {
            if (sort.equals("COURSE")) {
                sort = "COURSE DESC";
            } else sort = "COURSE";
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        JButton buttonQuestion = SwingUtils.createButton("Question", 205, 65, 150, 25, 10);
        buttonQuestion.addActionListener(e -> {
            if (sort.equals("QNAME")) {
                sort = "QNAME DESC";
            } else sort = "QNAME";
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        JButton buttonAnswer = SwingUtils.createButton("Answer", 525, 65, 150, 25, 10);
        buttonAnswer.addActionListener(e -> {
            if (sort.equals("ANSWER")) {
                sort = "ANSWER DESC";
            } else sort = "ANSWER";
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        subPanel.add(buttonQNO);
        subPanel.add(buttonCourse);
        subPanel.add(buttonQuestion);
        subPanel.add(buttonAnswer);

        if (!qs.isEmpty()) {
            JTextArea question1 = SwingUtils.createTextArea(qs.getFirst().getQno() + ". " + " (" + qs.getFirst().getCourse() + ")" + qs.getFirst().getQname(), 30, 100, 500, 28, 20);
            subPanel.add(question1);
            JLabel q1a = SwingUtils.createLabel("(" + qs.getFirst().getAnswer() + ")", 550, 100, 100, 25, 20);
            subPanel.add(q1a);
            JLabel q1a1 = SwingUtils.createLabel("A. " + qs.getFirst().getAns1(), 50, 130, 100, 25, 20);
            q1a1.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q1a1);
            JLabel q1a2 = SwingUtils.createLabel("B. " + qs.getFirst().getAns2(), 150, 130, 100, 25, 20);
            q1a2.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q1a2);
            JLabel q1a3 = SwingUtils.createLabel("C. " + qs.getFirst().getAns3(), 250, 130, 100, 25, 20);
            q1a3.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q1a3);
            JLabel q1a4 = SwingUtils.createLabel("D. " + qs.getFirst().getAns4(), 350, 130, 100, 25, 20);
            q1a4.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q1a4);
            JButton buttonEdit1 = SwingUtils.createButton("Edit", 720, 100, 60, 20, 10);
            buttonEdit1.addActionListener(e -> updateQuestion(subFrame, subPanel, qs.getFirst().getCourse(), qs.getFirst().getQno()));
            subPanel.add(buttonEdit1);
            JButton buttonDelete1 = SwingUtils.createButton("Del", 720, 130, 60, 20, 10);
            buttonDelete1.addActionListener(e -> showDeleteQuestionDialog(subFrame, subPanel, qs.getFirst().getCourse(), qs.getFirst().getQno()));
            subPanel.add(buttonDelete1);
        }

        if (qs.size() >= 2) {
            JTextArea question2 = SwingUtils.createTextArea(qs.get(1).getQno() + ". " + " (" + qs.get(1).getCourse() + ")" + qs.get(1).getQname(), 30, 170, 500, 28, 20);
            subPanel.add(question2);
            JLabel q2a = SwingUtils.createLabel("(" + qs.get(1).getAnswer() + ")", 550, 170, 100, 25, 20);
            subPanel.add(q2a);
            JLabel q2a1 = SwingUtils.createLabel("A. " + qs.get(1).getAns1(), 50, 200, 100, 25, 20);
            q2a1.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q2a1);
            JLabel q2a2 = SwingUtils.createLabel("B. " + qs.get(1).getAns2(), 150, 200, 100, 25, 20);
            q2a2.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q2a2);
            JLabel q2a3 = SwingUtils.createLabel("C. " + qs.get(1).getAns3(), 250, 200, 100, 25, 20);
            q2a3.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q2a3);
            JLabel q2a4 = SwingUtils.createLabel("D. " + qs.get(1).getAns4(), 350, 200, 100, 25, 20);
            q2a4.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q2a4);
            JButton buttonEdit1 = SwingUtils.createButton("Edit", 720, 170, 60, 20, 10);
            buttonEdit1.addActionListener(e -> updateQuestion(subFrame, subPanel, qs.get(1).getCourse(), qs.get(1).getQno()));
            subPanel.add(buttonEdit1);
            JButton buttonDelete1 = SwingUtils.createButton("Del", 720, 200, 60, 20, 10);
            buttonDelete1.addActionListener(e -> showDeleteQuestionDialog(subFrame, subPanel, qs.get(1).getCourse(), qs.get(1).getQno()));
            subPanel.add(buttonDelete1);
        }

        if (qs.size() >= 3) {
            JTextArea question3 = SwingUtils.createTextArea(qs.get(2).getQno() + ". " + " (" + qs.get(2).getCourse() + ")" + qs.get(2).getQname(), 30, 240, 500, 28, 20);
            subPanel.add(question3);
            JLabel q3a = SwingUtils.createLabel("(" + qs.get(2).getAnswer() + ")", 550, 240, 100, 25, 20);
            subPanel.add(q3a);
            JLabel q3a1 = SwingUtils.createLabel("A. " + qs.get(2).getAns1(), 50, 270, 100, 25, 20);
            q3a1.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q3a1);
            JLabel q3a2 = SwingUtils.createLabel("B. " + qs.get(2).getAns2(), 150, 270, 100, 25, 20);
            q3a2.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q3a2);
            JLabel q3a3 = SwingUtils.createLabel("C. " + qs.get(2).getAns3(), 250, 270, 100, 25, 20);
            q3a3.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q3a3);
            JLabel q3a4 = SwingUtils.createLabel("D. " + qs.get(2).getAns4(), 350, 270, 100, 25, 20);
            q3a4.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q3a4);
            JButton buttonEdit1 = SwingUtils.createButton("Edit", 720, 240, 60, 20, 10);
            buttonEdit1.addActionListener(e -> updateQuestion(subFrame, subPanel, qs.get(2).getCourse(), qs.get(2).getQno()));
            subPanel.add(buttonEdit1);
            JButton buttonDelete1 = SwingUtils.createButton("Del", 720, 270, 60, 20, 10);
            buttonDelete1.addActionListener(e -> showDeleteQuestionDialog(subFrame, subPanel, qs.get(2).getCourse(), qs.get(2).getQno()));
            subPanel.add(buttonDelete1);
        }

        if (qs.size() >= 4) {
            JTextArea question4 = SwingUtils.createTextArea(qs.get(3).getQno() + ". " + " (" + qs.get(3).getCourse() + ")" + qs.get(3).getQname(), 30, 310, 500, 28, 20);
            subPanel.add(question4);
            JLabel q4a = SwingUtils.createLabel("(" + qs.get(3).getAnswer() + ")", 550, 310, 100, 25, 20);
            subPanel.add(q4a);
            JLabel q4a1 = SwingUtils.createLabel("A. " + qs.get(3).getAns1(), 50, 340, 100, 25, 20);
            q4a1.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q4a1);
            JLabel q4a2 = SwingUtils.createLabel("B. " + qs.get(3).getAns2(), 150, 340, 100, 25, 20);
            q4a2.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q4a2);
            JLabel q4a3 = SwingUtils.createLabel("C. " + qs.get(3).getAns3(), 250, 340, 100, 25, 20);
            q4a3.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q4a3);
            JLabel q4a4 = SwingUtils.createLabel("D. " + qs.get(3).getAns4(), 350, 340, 100, 25, 20);
            q4a4.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q4a4);
            JButton buttonEdit1 = SwingUtils.createButton("Edit", 720, 310, 60, 20, 10);
            buttonEdit1.addActionListener(e -> updateQuestion(subFrame, subPanel, qs.get(3).getCourse(), qs.get(3).getQno()));
            subPanel.add(buttonEdit1);
            JButton buttonDelete1 = SwingUtils.createButton("Del", 720, 340, 60, 20, 10);
            buttonDelete1.addActionListener(e -> showDeleteQuestionDialog(subFrame, subPanel, qs.get(3).getCourse(), qs.get(3).getQno()));
            subPanel.add(buttonDelete1);
        }

        if (qs.size() >= 5) {
            JTextArea question5 = SwingUtils.createTextArea(qs.get(4).getQno() + ". " + " (" + qs.get(4).getCourse() + ")" + qs.get(4).getQname(), 30, 380, 500, 28, 20);
            subPanel.add(question5);
            JLabel q5a = SwingUtils.createLabel("(" + qs.get(4).getAnswer() + ")", 550, 380, 100, 25, 20);
            subPanel.add(q5a);
            JLabel q5a1 = SwingUtils.createLabel("A. " + qs.get(4).getAns1(), 50, 410, 100, 25, 20);
            q5a1.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q5a1);
            JLabel q5a2 = SwingUtils.createLabel("B. " + qs.get(4).getAns2(), 150, 410, 100, 25, 20);
            q5a2.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q5a2);
            JLabel q5a3 = SwingUtils.createLabel("C. " + qs.get(4).getAns3(), 250, 410, 100, 25, 20);
            q5a3.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q5a3);
            JLabel q5a4 = SwingUtils.createLabel("D. " + qs.get(4).getAns4(), 350, 410, 100, 25, 20);
            q5a4.setHorizontalAlignment(SwingConstants.LEFT);
            subPanel.add(q5a4);
            JButton buttonEdit1 = SwingUtils.createButton("Edit", 720, 380, 60, 20, 10);
            buttonEdit1.addActionListener(e -> updateQuestion(subFrame, subPanel, qs.get(4).getCourse(), qs.get(4).getQno()));
            subPanel.add(buttonEdit1);
            JButton buttonDelete1 = SwingUtils.createButton("Del", 720, 410, 60, 20, 10);
            buttonDelete1.addActionListener(e -> showDeleteQuestionDialog(subFrame, subPanel, qs.get(4).getCourse(), qs.get(4).getQno()));
            subPanel.add(buttonDelete1);
        }


        JButton buttonBack = SwingUtils.createButton("Back", 10, 10, 75, 30, 15);
        buttonBack.addActionListener(e -> subFrame.dispose());
        subPanel.add(buttonBack);

        JButton buttonRefresh = SwingUtils.createButton("Refresh", 100, 10, 150, 30, 15);
        buttonRefresh.addActionListener(e -> {
            refreshQuestionNumber();
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        subPanel.add(buttonRefresh);

        JTextArea currentPage = SwingUtils.createTextArea((page + 1) + "", 85, 470, 25, 27, 20);
        currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        currentPage.setEditable(true);
        currentPage.setOpaque(true);
        subPanel.add(currentPage);

        JTextArea textPage = SwingUtils.createTextArea("Page     of " + pageSize, 30, 470, 140, 30, 20);
        currentPage.setFont(new Font("微软雅黑", Font.PLAIN, 20));
        subPanel.add(textPage);

        JButton buttonGo = SwingUtils.createButton("Go", 170, 470, 70, 30, 15);
        buttonGo.addActionListener(e -> {
            String text = currentPage.getText();
            try {
                int newPage = Integer.parseInt(text.trim()) - 1;
                if (newPage >= 0 && newPage < pageSize) {
                    page = newPage;
                } else {
                    JOptionPane.showMessageDialog(subFrame, "Invalid page number", "Error", JOptionPane.ERROR_MESSAGE);
                }
                defaultUpdateQuestionList();
                displayQuestion(subFrame, subPanel);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(subFrame, "Invalid input. Please enter a valid page number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
        addEnterKeyListenerToButtonGo(currentPage, buttonGo);
        subPanel.add(buttonGo);

        JButton buttonPreviousPage = SwingUtils.createButton("Previous", 250, 470, 100, 30, 15);
        buttonPreviousPage.addActionListener(e -> {
            page--;
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        buttonPreviousPage.setEnabled(page != 0);
        subPanel.add(buttonPreviousPage);

        JButton buttonNextPage = SwingUtils.createButton("Next", 360, 470, 100, 30, 15);
        buttonNextPage.addActionListener(e -> {
            page++;
            defaultUpdateQuestionList();
            displayQuestion(subFrame, subPanel);
        });
        buttonNextPage.setEnabled(page < pageSize - 1);
        subPanel.add(buttonNextPage);

        JTextField searchFrame = SwingUtils.createTextField(40, 470, 470, 230, 30, 15);
        searchFrame.setText(searchText);
        subPanel.add(searchFrame);

        JButton buttonSearch = SwingUtils.createButton("Search", 710, 470, 70, 30, 10);
        buttonSearch.addActionListener(e -> {
            searchText = searchFrame.getText();
            defaultUpdateQuestionList();
            page = 0;
            displayQuestion(subFrame, subPanel);
        });
        addEnterKeyListenerToButtonSearch(searchFrame, buttonSearch);
        subPanel.add(buttonSearch);

        subFrame.add(subPanel);
        subPanel.revalidate();
        subPanel.repaint();
    }

    private void defaultUpdateQuestionList() {
        System.out.println("ViewQuestion.defaultUpdateQuestionList");
        qs.clear();

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from question where COURSE like ? and QNAME like ? order by " + sort + " limit ?, 5");
            pstmt.setString(1, course);
            pstmt.setString(2, "%" + searchText + "%");
            pstmt.setInt(3, page * 5);
            ResultSet rs = pstmt.executeQuery();
            writeQuestionToList(rs);
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

    private void updateQuestion(JFrame subFrame, JPanel subPanel, String thisCourse, int qno) {
        System.out.println("ViewQuestion.updateQuestion");
        JFrame subSubFrame = SwingUtils.getSubFrame(subFrame);
        subSubFrame.setTitle("Edit");
        JPanel subSubPanel = new JPanel();
        subSubPanel.setLayout(null);
        displayUpdate(subFrame, subPanel, subSubFrame, subSubPanel, thisCourse, qno);
    }

    private void displayUpdate(JFrame subFrame, JPanel subPanel, JFrame subSubFrame, JPanel subSubPanel, String thisCourse, int qno) {
        System.out.println("ViewQuestion.displayUpdate");

        subSubPanel.removeAll();

        JLabel label3 = SwingUtils.createLabel("Question: ", 10, 30, 200, 30, 15);
        JLabel label4 = SwingUtils.createLabel("Answer A: ", 10, 110, 200, 30, 15);
        JLabel label5 = SwingUtils.createLabel("Answer B: ", 10, 150, 200, 30, 15);
        JLabel label6 = SwingUtils.createLabel("Answer C: ", 10, 190, 200, 30, 15);
        JLabel label7 = SwingUtils.createLabel("Answer D: ", 10, 230, 200, 30, 15);
        JLabel label8 = SwingUtils.createLabel("Current Answer: ", 10, 270, 200, 30, 15);
        subSubPanel.add(label3);
        subSubPanel.add(label4);
        subSubPanel.add(label5);
        subSubPanel.add(label6);
        subSubPanel.add(label7);
        subSubPanel.add(label8);


        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("select * from question where course = ? and QNO = ?");
            pstmt.setString(1, thisCourse);
            pstmt.setInt(2, qno);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                questionArea = new JTextArea(rs.getString("qname"));
                questionArea.setFont(new Font("微软雅黑", Font.PLAIN, 15)); // 设置字体样式和大小
                questionArea.setLineWrap(true);
                questionArea.setWrapStyleWord(true);

                JScrollPane scrollPane = new JScrollPane(questionArea);
                scrollPane.setBounds(210, 30, 300, 70); // 设置 JScrollPane 的位置和大小
                scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);//水平滚动条
                scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);//垂直滚动条
                subSubPanel.add(scrollPane);

                ans1Field = SwingUtils.createTextField(20, 210, 110, 300, 30, 15);
                ans1Field.setText(rs.getString("ans1"));
                ans2Field = SwingUtils.createTextField(20, 210, 150, 300, 30, 15);
                ans2Field.setText(rs.getString("ans2"));
                ans3Field = SwingUtils.createTextField(20, 210, 190, 300, 30, 15);
                ans3Field.setText(rs.getString("ans3"));
                ans4Field = SwingUtils.createTextField(20, 210, 230, 300, 30, 15);
                ans4Field.setText(rs.getString("ans4"));
                answerField = SwingUtils.createTextField(20, 210, 270, 300, 30, 15);
                answerField.setText(rs.getString("answer"));
                subSubPanel.add(ans1Field);
                subSubPanel.add(ans2Field);
                subSubPanel.add(ans3Field);
                subSubPanel.add(ans4Field);
                subSubPanel.add(answerField);
            } else {
                JOptionPane.showMessageDialog(subSubFrame, "Error", "Error", JOptionPane.ERROR_MESSAGE);
                subSubFrame.dispose();
            }
            DBUtils.closeResource(rs, pstmt, conn);
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        JButton buttonConfirm = SwingUtils.createButton("Confirm", 100, 330, 150, 40, 20);
        buttonConfirm.addActionListener(e -> confirmUpdateQuestion(subFrame, subPanel, subSubFrame, thisCourse, qno));
        subSubPanel.add(buttonConfirm);

        JButton buttonBack = SwingUtils.createButton("Back", 300, 330, 200, 40, 20);
        buttonBack.addActionListener(e -> subSubFrame.dispose());
        subSubPanel.add(buttonBack);

        subSubFrame.add(subSubPanel);
        subSubPanel.revalidate();
        subSubPanel.repaint();

    }

    private void confirmUpdateQuestion(JFrame subFrame, JPanel subPanel, JFrame subSubFrame, String thisCourse, int qno) {
        System.out.println("ViewQuestion.confirmUpdateQuestion");
        String qname = questionArea.getText();
        String ans1 = ans1Field.getText();
        String ans2 = ans2Field.getText();
        String ans3 = ans3Field.getText();
        String ans4 = ans4Field.getText();
        String answer = answerField.getText().toUpperCase();

        if (course.isEmpty() || qname.isEmpty() || ans1.isEmpty() || ans2.isEmpty() || ans3.isEmpty() || ans4.isEmpty() || answer.isEmpty()) {
            JOptionPane.showMessageDialog(subSubFrame, "Please fill in all fields.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            String sql = "update question set QNAME = ?, ANS1 = ?, ANS2 = ?, ANS3 = ?, ANS4 = ?, answer = ? where COURSE = ? and QNO = ?";
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, qname);
            pstmt.setString(2, ans1);
            pstmt.setString(3, ans2);
            pstmt.setString(4, ans3);
            pstmt.setString(5, ans4);
            pstmt.setString(6, answer);
            pstmt.setString(7, thisCourse);
            pstmt.setInt(8, qno);
            int i = pstmt.executeUpdate();
            if (i == 1) {
                JOptionPane.showMessageDialog(subSubFrame, "Question updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
                defaultUpdateQuestionList();
                displayQuestion(subFrame, subPanel);
                subSubFrame.dispose();
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(subSubFrame, "An error occurred while adding the question.\n" + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            System.out.println(e.getMessage());
        }
    }

    private void clearFields() {
        System.out.println("ViewQuestion.clearFields");
        questionArea.setText("");
        ans1Field.setText("");
        ans2Field.setText("");
        ans3Field.setText("");
        ans4Field.setText("");
        answerField.setText("");
    }

    private void showDeleteQuestionDialog(JFrame parentFrame, JPanel panel, String thisCourse, int qno) {
        System.out.println("ViewQuestion.showDeleteQuestionDialog");
        Object[] options = {"Yes", "No"};

        int result = JOptionPane.showOptionDialog(parentFrame,
                "Confirm deletion?",
                "tips",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);

        if (result == JOptionPane.YES_OPTION) {
            deleteQuestion(thisCourse, qno);
            defaultUpdateQuestionList();
            displayQuestion(parentFrame, panel);
        } else if (result == JOptionPane.NO_OPTION) {
            System.out.println("No");
        }
    }

    private void deleteQuestion(String thisCourse, int k) {
        System.out.println("ViewQuestion.deleteQuestion");
        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("delete from question where COURSE = ? and QNO = ?");
            pstmt.setString(1, thisCourse);
            pstmt.setInt(2, k);
            int i = pstmt.executeUpdate();
            if (i > 0) {
                pstmt = conn.prepareStatement("update question set QNO = QNO - 1 where QNO > ? and COURSE like ?");
                pstmt.setInt(1, k);
                pstmt.setString(2, course);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Question deleted", "Question deleted", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "failed to delete question", "failed to delete question", JOptionPane.ERROR_MESSAGE);
            }
            DBUtils.closeConnection(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void refreshQuestionNumber() {
        try {
            Connection conn = DBUtils.getConnection();
            String sql = "select QNO from question where course = ? order by QNO";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "Java");
            ResultSet rs = pstmt.executeQuery();
            int id;
            int newId = 1;
            while (rs.next()) {
                id = rs.getInt("QNO");
                pstmt = conn.prepareStatement("update question set QNO = ? where COURSE like ? and QNO = ?");
                pstmt.setInt(1, newId);
                pstmt.setString(2, course);
                pstmt.setInt(3, id);
                pstmt.executeUpdate();
                newId++;
            }
            pstmt = conn.prepareStatement("select QNO from question where course = ? order by QNO");
            pstmt.setString(1, "Mysql");
            rs = pstmt.executeQuery();
            newId = 1;
            while (rs.next()) {
                id = rs.getInt("QNO");
                pstmt = conn.prepareStatement("update question set QNO = ? where COURSE like ? and QNO = ?");
                pstmt.setInt(1, newId);
                pstmt.setString(2, course);
                pstmt.setInt(3, id);
                pstmt.executeUpdate();
                newId++;
            }
            DBUtils.closeResource(rs, pstmt, conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}