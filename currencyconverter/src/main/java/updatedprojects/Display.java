package updatedprojects;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Currency;
import java.util.Date;

public class Display extends JFrame {

    private CurrencyConverter converter;

    private JLabel sourceLabel, destinationLabel, amountLabel, resultLabel, resultValue, rateLabel, rateValue;
    private JTextField amountTextField;
    private JComboBox<String> sourceComboBox, destinationComboBox;
    private JButton convertButton;

    public Display(){
        converter = new CurrencyConverter();

        Date date = new Date();
        System.out.println(date);

        // Create a frame (window) and set its properties
        setTitle("PROJECT: CURRENCY CONVERTER PROJECT WITH LIVE RESULTS(2)" + "     DATE: " + new Date());
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(255,255,173));//red,green,blue==white



        String relativepath = "currencyconverter\\src\\main\\java\\updatedprojects\\currency_symbol.png";
        Path path = Paths.get(relativepath);
        ImageIcon image = new ImageIcon(String.valueOf(path.toAbsolutePath()));

        //create labels and set properties and bounds
        Border border = BorderFactory.createLineBorder(Color.GREEN);
        sourceLabel = new JLabel("From Currency:");
        destinationLabel = new JLabel("To Currency:");
        amountLabel = new JLabel("Amount:");
        resultLabel = new JLabel("");//takes the input
        resultValue = new JLabel("");//returns the converted value
        rateLabel = new JLabel("Rate:");//returns exchange rate
        rateValue = new JLabel("");

        sourceLabel.setBackground(Color.LIGHT_GRAY);
        sourceLabel.setForeground(Color.BLACK);//set font colour
        sourceLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
        sourceLabel.setOpaque(true);
        sourceLabel.setBorder(border);
        sourceLabel.setBounds(20, 40, 80, 30);

        destinationLabel.setBackground(Color.LIGHT_GRAY);
        destinationLabel.setForeground(Color.BLACK);//set font colour
        destinationLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
        destinationLabel.setOpaque(true);
        destinationLabel.setBorder(border);
        destinationLabel.setBounds(20, 40, 80, 30);

        amountLabel.setBackground(Color.LIGHT_GRAY);
        amountLabel.setForeground(Color.BLACK);//set font colour
        amountLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
        amountLabel.setOpaque(true);
        amountLabel.setBorder(border);
        amountLabel.setBounds(20, 40, 80, 30);

        resultLabel.setBackground(Color.LIGHT_GRAY);
        resultLabel.setForeground(Color.BLACK);//set font colour
        resultLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
        resultLabel.setOpaque(true);
        resultLabel.setBorder(border);
        resultLabel.setBounds(20, 40, 80, 30);

        rateLabel.setBackground(Color.LIGHT_GRAY);
        rateLabel.setForeground(Color.BLACK);//set font colour
        rateLabel.setFont(new Font("MV Boli",Font.PLAIN,16));
        rateLabel.setOpaque(true);
        rateLabel.setBorder(border);
        rateLabel.setBounds(20, 40, 80, 30);

        resultValue.setBackground(Color.LIGHT_GRAY);
        resultValue.setForeground(Color.BLACK);//set font colour
        resultValue.setFont(new Font("MV Boli",Font.PLAIN,16));
        resultValue.setOpaque(true);
        resultValue.setBorder(border);
        resultValue.setBounds(20, 40, 80, 30);

        rateValue.setBackground(Color.LIGHT_GRAY);
        rateValue.setForeground(Color.BLACK);//set font colour
        rateValue.setFont(new Font("MV Boli",Font.PLAIN,16));
        rateValue.setOpaque(true);
        rateValue.setBorder(border);
        rateValue.setBounds(20, 40, 80, 30);

        //set the combo boxes to the results in the String array
        String[] currencyCodes = converter.getCurrencyCodes();
        sourceComboBox = new JComboBox<>(currencyCodes);
        destinationComboBox = new JComboBox<>(currencyCodes);

        //creates the number of columns in the frame
        amountTextField = new JTextField(10);

        //create button
        convertButton = new JButton("Convert");


        //Create panel and adding objects to the form
        JPanel panel = new JPanel();

        //set row and column
        panel.setLayout(new GridLayout(5, 2));
        panel.add(sourceLabel);
        panel.add(sourceComboBox);
        panel.add(destinationLabel);
        panel.add(destinationComboBox);
        panel.add(amountLabel);
        panel.add(amountTextField);
        panel.add(rateLabel);

        panel.add(rateValue);
        panel.add(convertButton);

        panel.add(resultLabel);
        panel.add(resultValue);


        //The ActionListener will receive an ActionEvent when a selection has been made. If the combo box is editable,
        // then an ActionEvent will be fired when editing has stopped.
        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String sourceCurrency = (String) sourceComboBox.getSelectedItem();
                    String destinationCurrency = (String) destinationComboBox.getSelectedItem();
                    Currency destinationCurrencySymbol = Currency.getInstance(destinationCurrency);
                    Currency sourceCurrencySymbol = Currency.getInstance(sourceCurrency);
                    double amount = Double.parseDouble(amountTextField.getText());
                    double result = CurrencyConverter.convertCurrency(sourceCurrency, destinationCurrency, amount);
                    String convertResultToString = Double.toString(result);
                    resultValue.setText(convertResultToString);
                    resultLabel.setText("(" + sourceCurrency + ") "  + sourceCurrencySymbol.getSymbol()
                            +amount + " = (" + destinationCurrency + ") " + destinationCurrencySymbol.getSymbol() + String.format("%.2f", result));

                    rateValue.setText("" + result/amount);
                    //resultLabel.setText(String.format("%.2f %s is equal to %.2f %s", amount, sourceCurrency, result, destinationCurrency));
                } catch (Exception ex) {
                    resultLabel.setText("Invalid input or conversion error.");
                }
            }
        });


        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);

        add(panel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Display converter = new Display();
            converter.setVisible(true);
        });
    }
}
