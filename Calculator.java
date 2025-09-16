import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String operator = "";
    private double num1 = 0, num2 = 0;

    public Calculator() {
        // Frame setup
        setTitle("Calculator");
        setSize(280, 370);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // Display field
        display = new JTextField();
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 22));
        display.setHorizontalAlignment(JTextField.RIGHT);
        add(display, BorderLayout.NORTH);

        // Buttons grid
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 5, 5));
        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "0", "C", "=", "+"
        };

        int i = 0;
        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 18)); // smaller font
            btn.setFocusPainted(false);

            // Alternate button colors (grey & white)
            if (i % 2 == 0) {
                btn.setBackground(new Color(230, 230, 230)); // light grey
            } else {
                btn.setBackground(Color.WHITE);
            }
            btn.setOpaque(true);
            btn.setBorderPainted(false);

            btn.addActionListener(this);
            panel.add(btn);
            i++;
        }

        add(panel, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        if (cmd.matches("\\d")) { // numbers
            display.setText(display.getText() + cmd);
        } else if (cmd.matches("[+\\-*/]")) { // operators
            if (!display.getText().isEmpty()) {
                num1 = Double.parseDouble(display.getText());
                operator = cmd;
                display.setText("");
            }
        } else if (cmd.equals("=")) {
            if (!display.getText().isEmpty()) {
                num2 = Double.parseDouble(display.getText());
                double result = 0;
                switch (operator) {
                    case "+": result = num1 + num2; break;
                    case "-": result = num1 - num2; break;
                    case "*": result = num1 * num2; break;
                    case "/":
                        if (num2 == 0) {
                            JOptionPane.showMessageDialog(this, "Error: Division by zero");
                            display.setText("");
                            return;
                        }
                        result = num1 / num2;
                        break;
                }
                display.setText(String.valueOf(result));
            }
        } else if (cmd.equals("C")) { // clear
            display.setText("");
            num1 = num2 = 0;
            operator = "";
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new Calculator().setVisible(true);
        });
    }
}
