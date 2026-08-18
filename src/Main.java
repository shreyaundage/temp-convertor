import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Main extends JFrame implements ActionListener {

    JTextField tempField;
    JComboBox<String> fromBox, toBox;
    JLabel resultValue;
    JButton convertButton;

    Color bg = new Color(245, 249, 255);
    Color blue = new Color(65, 130, 235);
    Color darkBlue = new Color(20, 55, 100);
    Color green = new Color(72, 243, 109);

    String[] units = {"Celsius", "Fahrenheit", "Kelvin"};

    public Main() {

        setTitle("Temperature Converter");
        setSize(450, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(bg);
        setLayout(new BorderLayout());

        // Header
        JPanel header = new JPanel();
        header.setBackground(bg);
        header.setLayout(new BoxLayout(header, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Temperature Converter");
        title.setFont(new Font("Arial", Font.BOLD, 32));
        title.setForeground(darkBlue);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Convert between Celsius, Fahrenheit and Kelvin"
        );
        subtitle.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitle.setForeground(new Color(90, 110, 135));
        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        header.add(Box.createVerticalStrut(25));
        header.add(title);
        header.add(Box.createVerticalStrut(8));
        header.add(subtitle);
        header.add(Box.createVerticalStrut(25));

        add(header, BorderLayout.NORTH);

        // Main form
        JPanel form = new JPanel(new GridLayout(4, 2, 10, 10));
        form.setBackground(Color.WHITE);
        form.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(220, 225, 235)),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
        ));

        JLabel tempLabel = new JLabel("Enter Temperature:");
        tempLabel.setFont(new Font("Arial", Font.BOLD, 13));
        tempLabel.setForeground(darkBlue);

        tempField = new JTextField();
        tempField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel fromLabel = new JLabel("From:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 13));
        fromLabel.setForeground(darkBlue);

        fromBox = new JComboBox<>(units);
        fromBox.setFont(new Font("Arial", Font.PLAIN, 17));

        JLabel toLabel = new JLabel("To:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 13));
        toLabel.setForeground(darkBlue);

        toBox = new JComboBox<>(units);
        toBox.setFont(new Font("Arial", Font.PLAIN, 17));

        convertButton = new JButton("CONVERT");
        convertButton.setFont(new Font("Arial", Font.BOLD, 18));
        convertButton.setForeground(Color.WHITE);
        convertButton.setBackground(blue);
        convertButton.setFocusPainted(false);
        convertButton.addActionListener(this);

        form.add(tempLabel);
        form.add(tempField);
        form.add(fromLabel);
        form.add(fromBox);
        form.add(toLabel);
        form.add(toBox);
        form.add(new JLabel());
        form.add(convertButton);

        JPanel center = new JPanel(new BorderLayout());
        center.setBackground(bg);
        center.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
        center.add(form);

        add(center, BorderLayout.CENTER);

        // Result
        JPanel result = new JPanel(new BorderLayout());
        result.setBackground(green);
        result.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));

        JLabel resultLabel = new JLabel("Result:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 13));
        resultLabel.setForeground(new Color(30, 110, 50));

        resultValue = new JLabel("0.00 °C");
        resultValue.setFont(new Font("Arial", Font.BOLD, 22));
        resultValue.setForeground(new Color(20, 120, 50));

        JPanel resultText = new JPanel();
        resultText.setBackground(green);
        resultText.setLayout(new BoxLayout(resultText, BoxLayout.Y_AXIS));
        resultText.add(resultLabel);
        resultText.add(Box.createVerticalStrut(5));
        resultText.add(resultValue);

        result.add(resultText);

        JPanel bottom = new JPanel(new BorderLayout());
        bottom.setBackground(bg);
        bottom.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));
        bottom.add(result);

        add(bottom, BorderLayout.SOUTH);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        try {
            double temp = Double.parseDouble(tempField.getText());
            String from = (String) fromBox.getSelectedItem();
            String to = (String) toBox.getSelectedItem();

            double celsius;

            if (from.equals("Celsius"))
                celsius = temp;
            else if (from.equals("Fahrenheit"))
                celsius = (temp - 32) * 5 / 9;
            else
                celsius = temp - 273.15;

            double result;

            if (to.equals("Celsius"))
                result = celsius;
            else if (to.equals("Fahrenheit"))
                result = celsius * 9 / 5 + 32;
            else
                result = celsius + 273.15;

            resultValue.setText(
                    String.format("%.2f %s", result, getSymbol(to))
            );

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(
                    this,
                    "Please enter a valid temperature!",
                    "Invalid Input",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    String getSymbol(String unit) {
        if (unit.equals("Celsius"))
            return "°C";
        if (unit.equals("Fahrenheit"))
            return "°F";
        return "K";
    }

    public static void main(String[] args) {
        new Main();
    }
}