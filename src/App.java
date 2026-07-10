import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class App extends JFrame implements ActionListener {

    private JPasswordField passwordField;
    private JButton checkButton, resetButton;
    private JCheckBox showPassword;

    private JLabel strengthLabel;
    private JLabel lengthLabel, upperLabel, lowerLabel, numberLabel, specialLabel;

    public App() {

        setTitle("Password Strength Checker");
        setSize(500, 420);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLayout(new BorderLayout(15,15));

        // ---------- TOP PANEL ----------
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BoxLayout(topPanel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Password Strength Checker");
        title.setFont(new Font("Arial", Font.BOLD, 22));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel passwordPanel = new JPanel(new BorderLayout(5,5));

        JLabel passwordLabel = new JLabel("Enter Password:");

        passwordField = new JPasswordField();

        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField, BorderLayout.CENTER);

        showPassword = new JCheckBox("Show Password");

        showPassword.addActionListener(e -> {

            if(showPassword.isSelected())
                passwordField.setEchoChar((char)0);
            else
                passwordField.setEchoChar('•');

        });

        topPanel.add(Box.createVerticalStrut(10));
        topPanel.add(title);
        topPanel.add(Box.createVerticalStrut(15));
        topPanel.add(passwordPanel);
        topPanel.add(showPassword);

        // ---------- BUTTON PANEL ----------

        JPanel buttonPanel = new JPanel();

        checkButton = new JButton("Check Strength");
        resetButton = new JButton("Reset");

        checkButton.addActionListener(this);

        resetButton.addActionListener(e -> {

            passwordField.setText("");

            strengthLabel.setText("Strength : ");

            strengthLabel.setForeground(Color.BLACK);

            lengthLabel.setText("✖ Minimum 8 Characters");
            upperLabel.setText("✖ Uppercase Letter");
            lowerLabel.setText("✖ Lowercase Letter");
            numberLabel.setText("✖ Number");
            specialLabel.setText("✖ Special Character");

        });

        buttonPanel.add(checkButton);
        buttonPanel.add(resetButton);

        // ---------- RESULT PANEL ----------

        JPanel resultPanel = new JPanel();
        resultPanel.setLayout(new BoxLayout(resultPanel, BoxLayout.Y_AXIS));

        strengthLabel = new JLabel("Strength : ");

        lengthLabel = new JLabel("✖ Minimum 8 Characters");
        upperLabel = new JLabel("✖ Uppercase Letter");
        lowerLabel = new JLabel("✖ Lowercase Letter");
        numberLabel = new JLabel("✖ Number");
        specialLabel = new JLabel("✖ Special Character");

        resultPanel.add(strengthLabel);
        resultPanel.add(Box.createVerticalStrut(10));
        resultPanel.add(lengthLabel);
        resultPanel.add(upperLabel);
        resultPanel.add(lowerLabel);
        resultPanel.add(numberLabel);
        resultPanel.add(specialLabel);

        add(topPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        String password = new String(passwordField.getPassword());

        boolean length = password.length() >= 8;
        boolean upper = password.matches(".*[A-Z].*");
        boolean lower = password.matches(".*[a-z].*");
        boolean number = password.matches(".*[0-9].*");
        boolean special = password.matches(".*[^a-zA-Z0-9].*");

        int score = 0;

        if(length) score++;
        if(upper) score++;
        if(lower) score++;
        if(number) score++;
        if(special) score++;

        lengthLabel.setText((length ? "✔ " : "✖ ") + "Minimum 8 Characters");
        upperLabel.setText((upper ? "✔ " : "✖ ") + "Uppercase Letter");
        lowerLabel.setText((lower ? "✔ " : "✖ ") + "Lowercase Letter");
        numberLabel.setText((number ? "✔ " : "✖ ") + "Number");
        specialLabel.setText((special ? "✔ " : "✖ ") + "Special Character");

        if(score <= 2){

            strengthLabel.setText("Strength : WEAK");
            strengthLabel.setForeground(Color.RED);

        }
        else if(score <= 4){

            strengthLabel.setText("Strength : MEDIUM");
            strengthLabel.setForeground(Color.ORANGE);

        }
        else{

            strengthLabel.setText("Strength : STRONG");
            strengthLabel.setForeground(new Color(0,150,0));

        }

    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(App::new);

    }

}