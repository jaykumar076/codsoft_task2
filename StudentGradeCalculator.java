import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentGradeCalculator extends JFrame implements ActionListener {
    private JLabel[] subjectLabels;
    private JTextField[] subjectFields;
    private JLabel totalMarksLabel, averagePercentageLabel, gradeLabel;
    private JButton calculateButton, resetButton;

    public StudentGradeCalculator() {
        setTitle("Grade Calculator");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        int numSubjects = 5; 

        subjectLabels = new JLabel[numSubjects];
        subjectFields = new JTextField[numSubjects];

        JPanel panel = new JPanel(new GridLayout(numSubjects + 4, 2));

        for (int i = 0; i < numSubjects; i++) {
            subjectLabels[i] = new JLabel("Subject " + (i + 1) + ":");
            subjectFields[i] = new JTextField(5);
            panel.add(subjectLabels[i]);
            panel.add(subjectFields[i]);
        }

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(this);

        resetButton = new JButton("Reset");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetFields();
            }
        });

        totalMarksLabel = new JLabel("Total Marks: ");
        averagePercentageLabel = new JLabel("Average Percentage: ");
        gradeLabel = new JLabel("Grade: ");

        panel.add(calculateButton);
        panel.add(resetButton);
        panel.add(totalMarksLabel);
        panel.add(averagePercentageLabel);
        panel.add(gradeLabel);

        add(panel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int totalMarks = 0;
        int numSubjects = subjectFields.length;

        for (int i = 0; i < numSubjects; i++) {
            try {
                int marks = Integer.parseInt(subjectFields[i].getText());
                if (marks < 0 || marks > 100) {
                    JOptionPane.showMessageDialog(this, "Invalid input. Marks should be between 0 and 100.");
                    resetFields();
                    return;
                }
                totalMarks += marks;
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Invalid input. Please enter numeric values.");
                resetFields();
                return;
            }
        }

        double averagePercentage = (double) totalMarks / (numSubjects * 100) * 100;
        String grade = calculateGrade(averagePercentage);

        totalMarksLabel.setText("Total Marks: " + totalMarks);
        averagePercentageLabel.setText("Average Percentage: " + averagePercentage + "%");
        gradeLabel.setText("Grade: " + grade);
    }

    private void resetFields() {
        for (int i = 0; i < subjectFields.length; i++) {
            subjectFields[i].setText("");
        }
        totalMarksLabel.setText("Total Marks: ");
        averagePercentageLabel.setText("Average Percentage: ");
        gradeLabel.setText("Grade: ");
    }

    public static String calculateGrade(double averagePercentage) {
        if (averagePercentage >= 90) {
            return "A+";
        } else if (averagePercentage >= 80) {
            return "A";
        } else if (averagePercentage >= 70) {
            return "B";
        } else if (averagePercentage >= 60) {
            return "C";
        } else if (averagePercentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            StudentGradeCalculator calculator = new StudentGradeCalculator();
            calculator.setVisible(true);
        });
    }
}
