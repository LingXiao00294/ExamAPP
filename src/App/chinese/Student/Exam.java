package App.chinese.Student;

import model.Questions;
import utils.DBUtils;
import utils.SwingUtils;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Exam {

    private final String id;
    private final String name;
    private final List<Questions> qs = new ArrayList<>();
    private String course;
    private int currentQuestionIndex = 0;
    private JFrame frame;
    private JPanel panel;

    public Exam(String id, String name) {
        this.id = id;
        this.name = name;
    }//构造器

    private void getQuestionsList(String sql) throws SQLException {
        Connection conn = DBUtils.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            Questions questions = new Questions();
            questions.setQname(rs.getString("qname"));
            questions.setAns1(rs.getString("ans1"));
            questions.setAns2(rs.getString("ans2"));
            questions.setAns3(rs.getString("ans3"));
            questions.setAns4(rs.getString("ans4"));
            questions.setAnswer(rs.getString("answer"));
            qs.add(questions);
        }
    }

    private void showQuestion() {
        panel.removeAll();
        if (currentQuestionIndex >= qs.size()) {
            currentQuestionIndex = qs.size() - 1;
        } else if (currentQuestionIndex < 0) {
            currentQuestionIndex = 0;
        }

        Questions currentQuestion;
        try {
            currentQuestion = qs.get(currentQuestionIndex);

            JTextArea textQuestion = SwingUtils.createTextArea(currentQuestionIndex + 1 + ". " + currentQuestion.getQname(), 10, 10, 580, 50, 20);
            textQuestion.setWrapStyleWord(true);
            panel.add(textQuestion);

            JRadioButton optionA = new JRadioButton("A. " + currentQuestion.getAns1());
            optionA.setBounds(10, 70, 580, 30);
            panel.add(optionA);

            JRadioButton optionB = new JRadioButton("B. " + currentQuestion.getAns2());
            optionB.setBounds(10, 110, 580, 30);
            panel.add(optionB);

            JRadioButton optionC = new JRadioButton("C. " + currentQuestion.getAns3());
            optionC.setBounds(10, 150, 580, 30);
            panel.add(optionC);

            JRadioButton optionD = new JRadioButton("D. " + currentQuestion.getAns4());
            optionD.setBounds(10, 190, 580, 30);
            panel.add(optionD);

            ButtonGroup group = new ButtonGroup();
            group.add(optionA);
            group.add(optionB);
            group.add(optionC);
            group.add(optionD);

            JButton buttonNext;
            if (currentQuestionIndex == qs.size() - 1) {
                buttonNext = SwingUtils.createButton("提交", 400, 300, 150, 50, 20);
            } else {
                buttonNext = SwingUtils.createButton("Next", 400, 300, 150, 50, 20);
            }
            buttonNext.addActionListener(e -> {
                String selectedAnswer = null;
                if (optionA.isSelected()) {
                    selectedAnswer = "A";
                } else if (optionB.isSelected()) {
                    selectedAnswer = "B";
                } else if (optionC.isSelected()) {
                    selectedAnswer = "C";
                } else if (optionD.isSelected()) {
                    selectedAnswer = "D";
                }
                currentQuestion.setUseranswer(selectedAnswer);
                currentQuestionIndex++;
                if (currentQuestionIndex < qs.size()) {
                    showQuestion();
                } else {
                    submit();
                }
            });
            panel.add(buttonNext);

            JButton buttonPrevious = SwingUtils.createButton("Previous", 50, 300, 150, 50, 20);
            if (currentQuestionIndex == 0) buttonPrevious.setEnabled(false);
            buttonPrevious.addActionListener(e -> {
                currentQuestionIndex--;
                showQuestion();
            });
            panel.add(buttonPrevious);

            frame.revalidate();
            frame.repaint();
        } catch (IndexOutOfBoundsException e) {
            JOptionPane.showMessageDialog(frame, "No " + course + " questions!", "Error", JOptionPane.ERROR_MESSAGE);
            chooseCourse(frame, panel);
        }

    }

    public void chooseCourse(JFrame frame, JPanel panel) {
        this.frame = frame;
        this.panel = panel;
        panel.removeAll();
        panel.setLayout(null);

        JLabel label = SwingUtils.createLabel("Please choose a course", 0, 10, 600, 50, 30);
        panel.add(label);

        JButton buttonJava = SwingUtils.createButton("Java", 200, 100, 200, 50, 25);
        buttonJava.addActionListener(e -> examJava());
        panel.add(buttonJava);

        JButton buttonMysql = SwingUtils.createButton("MySQL", 200, 200, 200, 50, 20);
        buttonMysql.addActionListener(e -> examMysql());
        panel.add(buttonMysql);

        frame.revalidate();
        frame.repaint();
    }

    private void examJava() {
        String sql = "SELECT * FROM question WHERE course = 'java' ORDER BY rand() LIMIT 10";
        try {
            getQuestionsList(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        course = "java";
        startExam();
    }

    private void examMysql() {
        String sql = "SELECT * FROM question WHERE course = 'Mysql' ORDER BY rand() LIMIT 10";
        try {
            getQuestionsList(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        course = "Mysql";
        startExam();
    }

    private void startExam() {
        panel.removeAll();
        panel.setLayout(null);
        showQuestion();

        frame.revalidate();
        frame.repaint();
    }

    private void submit() {
        int score = 0;
        for (Questions question : qs) {
            if (question.getUseranswer() != null && question.getUseranswer().equalsIgnoreCase(question.getAnswer())) {
                score += 10;
            }
        }

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO result (SNO, SNAME, COURSE, SCORE, DATE) VALUES (?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE sname = VALUES(sname), score = VALUES(score), date = VALUES(date)");
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setInt(4, score);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            pstmt.setString(5, formattedDateTime);
            int result = pstmt.executeUpdate();

            if (result >= 1) {
                JOptionPane.showMessageDialog(frame, "Submit successfully. Your score is: " + score, "Success", JOptionPane.INFORMATION_MESSAGE);
                new Exam(id, name).chooseCourse(frame, panel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
