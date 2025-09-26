// SINDAYIGAYA Samuel  223004485

package com.LoanCalculator;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoanCalculatorGUI extends JFrame {

    private JTextField txtAmount;
    private JTextField txtDuration;
    private JTextField txtTotal;
    private JButton btnCalculate;

    public LoanCalculatorGUI() {
        setTitle("Loan Calculator");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        
        JPanel panel = new JPanel();
        panel.setBackground(new Color(79, 129, 189)); 
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblAmount = new JLabel("Amount requested");
        lblAmount.setForeground(Color.WHITE);
        txtAmount = new JTextField(15);

        JLabel lblDuration = new JLabel("Duration(year)");
        lblDuration.setForeground(Color.WHITE);
        txtDuration = new JTextField(15);

        JLabel lblTotal = new JLabel("Total to return");
        lblTotal.setForeground(Color.WHITE);
        txtTotal = new JTextField(15);
        txtTotal.setEditable(false);
        txtTotal.setFont(new Font("SansSerif", Font.BOLD, 14));

        btnCalculate = new JButton("Calculate");
        btnCalculate.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                calculateTotal();
            }
        });

        gbc.gridx = 0; gbc.gridy = 0;
        panel.add(lblAmount, gbc);
        gbc.gridx = 1;
        panel.add(txtAmount, gbc);

        gbc.gridx = 0; gbc.gridy = 1;
        panel.add(lblDuration, gbc);
        gbc.gridx = 1;
        panel.add(txtDuration, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        panel.add(lblTotal, gbc);
        gbc.gridx = 1;
        panel.add(txtTotal, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        panel.add(btnCalculate, gbc);

        add(panel);
    }

    private void calculateTotal() {
        try {
            double amount = Double.parseDouble(txtAmount.getText().replace(" ", ""));
            double years = Double.parseDouble(txtDuration.getText());
            // Example: simple interest 0.6% per year (change as needed)
            double total = amount + (amount * 0.006 * years);
            txtTotal.setText(String.format("%,.0f", total));
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this,
                    "Please enter valid numbers.",
                    "Input Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
       // SwingUtilities.invokeLater(() -> {
            new LoanCalculatorGUI().setVisible(true);
        };
    }


