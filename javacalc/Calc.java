import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Calc {
    private static double firstNum = 0;
    private static String operator = "";
    private static boolean isNewInput = true;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Docker Java Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(320, 450);
        frame.setLayout(new BorderLayout());
        frame.getContentPane().setBackground(new Color(30, 30, 30));

        // --- Панель экрана ---
        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new BorderLayout());
        displayPanel.setBackground(new Color(30, 30, 30));
        displayPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Верхняя строка (история: весь пример)
        JLabel historyLabel = new JLabel(" ");
        historyLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        historyLabel.setForeground(new Color(150, 150, 150)); // Серый текст
        historyLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayPanel.add(historyLabel, BorderLayout.NORTH);

        // Нижняя строка (основной ввод и результат)
        JTextField display = new JTextField("0");
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.BOLD, 40));
        display.setForeground(Color.WHITE); // Светлые цифры
        display.setBackground(new Color(30, 30, 30));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setBorder(null);
        displayPanel.add(display, BorderLayout.CENTER);

        frame.add(displayPanel, BorderLayout.NORTH);

        // --- Панель кнопок ---
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 4, 2, 2));
        panel.setBackground(new Color(30, 30, 30));

        String[] buttons = {
            "7", "8", "9", "/",
            "4", "5", "6", "*",
            "1", "2", "3", "-",
            "C", "0", "=", "+"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.BOLD, 24));
            btn.setForeground(Color.WHITE); // Светлый текст на кнопках
            
            // Раскрашиваем кнопки
            if (text.matches("[0-9]")) {
                btn.setBackground(new Color(60, 60, 60)); // Темно-серые цифры
            } else if (text.equals("C")) {
                btn.setBackground(new Color(200, 50, 50)); // Красная кнопка сброса
            } else if (text.equals("=")) {
                btn.setBackground(new Color(50, 150, 50)); // Зеленое равно
            } else {
                btn.setBackground(new Color(255, 140, 0)); // Оранжевые операторы
            }
            
            btn.setOpaque(true);
            btn.setBorderPainted(false); // Для корректного отображения цветов на Mac/Linux

            btn.addActionListener((ActionEvent e) -> {
                String cmd = e.getActionCommand();
                
                if (cmd.matches("[0-9]")) {
                    // Ввод цифр
                    if (isNewInput) {
                        display.setText(cmd);
                        isNewInput = false;
                    } else {
                        display.setText(display.getText() + cmd);
                    }
                } else if (cmd.equals("C")) {
                    // Очистка
                    display.setText("0");
                    historyLabel.setText(" ");
                    firstNum = 0;
                    operator = "";
                    isNewInput = true;
                } else if (cmd.equals("=")) {
                    // Вычисление результата
                    if (!operator.isEmpty()) {
                        double secondNum = Double.parseDouble(display.getText());
                        historyLabel.setText(formatNum(firstNum) + " " + operator + " " + formatNum(secondNum) + " =");
                        
                        double result = 0;
                        switch (operator) {
                            case "+": result = firstNum + secondNum; break;
                            case "-": result = firstNum - secondNum; break;
                            case "*": result = firstNum * secondNum; break;
                            case "/": result = firstNum / secondNum; break;
                        }
                        display.setText(formatNum(result));
                        firstNum = result; // Позволяет продолжать вычисления
                        operator = "";
                        isNewInput = true;
                    }
                } else {
                    // Обработка операторов (+, -, *, /)
                    firstNum = Double.parseDouble(display.getText());
                    operator = cmd;
                    historyLabel.setText(formatNum(firstNum) + " " + operator);
                    isNewInput = true;
                }
            });
            panel.add(btn);
        }

        frame.add(panel, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    private static String formatNum(double num) {
        if (num == (long) num) {
            return String.format("%d", (long) num);
        } else {
            return String.format("%s", num);
        }
    }
}