package App.english.Student;

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

    private final String id; // 学生编号
    private final String name; // 学生姓名
    private final List<Questions> qs = new ArrayList<>(); // 存储问题的列表
    private String course; // 课程名
    private int currentQuestionIndex = 0; // 当前问题索引
    private JFrame frame; // 主框架
    private JPanel panel; // 主面板

    // 构造器，初始化学生信息
    public Exam(String id, String name) {
        this.id = id;
        this.name = name;
    }

    // 显示选择课程界面
    public void chooseCourse(JFrame frame, JPanel panel) {
        course = "";
        qs.clear();
        currentQuestionIndex = 0;
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

    // 开始Java考试
    private void examJava() {
        String sql = "SELECT * FROM question WHERE course = 'java' ORDER BY rand() LIMIT 10";
        try {
            getQuestionsList(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        course = "java";
        showQuestion();
    }

    // 开始MySQL考试
    private void examMysql() {
        String sql = "SELECT * FROM question WHERE course = 'Mysql' ORDER BY rand() LIMIT 10";
        try {
            getQuestionsList(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        course = "Mysql";
        showQuestion();
    }

    // 从数据库中获取问题列表
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

    // 开始考试，显示第一道题目
    private void startExam() {
        panel.removeAll();
        panel.setLayout(null);

        showQuestion();

        frame.revalidate();
        frame.repaint();
    }

    // 显示当前问题
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

            JTextArea textQuestion = SwingUtils.createTextArea(currentQuestionIndex + 1 + ". " + currentQuestion.getQname(), 10, 10, 560, 110, 17);
            textQuestion.setWrapStyleWord(true);
            panel.add(textQuestion);

            // 创建答案选项按钮
            JRadioButton optionA = new JRadioButton("A. " + currentQuestion.getAns1());
            optionA.setBounds(10, 120, 580, 30);
            panel.add(optionA);

            JRadioButton optionB = new JRadioButton("B. " + currentQuestion.getAns2());
            optionB.setBounds(10, 160, 580, 30);
            panel.add(optionB);

            JRadioButton optionC = new JRadioButton("C. " + currentQuestion.getAns3());
            optionC.setBounds(10, 200, 580, 30);
            panel.add(optionC);

            JRadioButton optionD = new JRadioButton("D. " + currentQuestion.getAns4());
            optionD.setBounds(10, 240, 580, 30);
            panel.add(optionD);

            ButtonGroup group = new ButtonGroup();//***
            group.add(optionA);
            group.add(optionB);
            group.add(optionC);
            group.add(optionD);

            // 创建下一步按钮
            JButton buttonNext;
            if (currentQuestionIndex == qs.size() - 1) {
                buttonNext = SwingUtils.createButton("Submit", 400, 300, 150, 50, 20);
            } else {
                buttonNext = SwingUtils.createButton("Next", 400, 300, 150, 50, 20);
            }
            buttonNext.addActionListener(e -> {
                String selectedAnswer = qs.get(currentQuestionIndex).getUseranswer();
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

            // 创建上一步按钮
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

    // 提交考试结果
    private void submit() {
        int score = 0;
        for (Questions question : qs) {
            if (question.getUseranswer() != null && question.getUseranswer().equalsIgnoreCase(question.getAnswer())) {
                score += 10;
            }
        }

        try {
            Connection conn = DBUtils.getConnection();
            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO result (SNO, SNAME, COURSE, SCORE, DATE) VALUES (?, ?, ?, ?, ?)");
            pstmt.setString(1, id);
            pstmt.setString(2, name);
            pstmt.setString(3, course);
            pstmt.setInt(4, score);
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            String formattedDateTime = now.format(formatter);
            pstmt.setString(5, formattedDateTime);
            int i = pstmt.executeUpdate();

            if (i >= 1) {
                JOptionPane.showMessageDialog(frame, "Submit successfully. Your score is: " + score, "Success", JOptionPane.INFORMATION_MESSAGE);
                chooseCourse(frame, panel);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(frame, "An error occurred while connecting to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
}
