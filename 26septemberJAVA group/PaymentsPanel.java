//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618package com.SFMS;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class PaymentsPanel extends JPanel {
    private JTable paymentsTable;
    private DefaultTableModel tableModel;
    private JTextField paymentIdField, referenceNoField, amountField;
    private JComboBox<String> methodComboBox, statusComboBox;
    private JFormattedTextField dateField;
    private JButton addButton, updateButton, deleteButton, loadButton, clearButton;
    
    public PaymentsPanel() {
        initializeUI();
        loadPaymentsData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Table
        String[] columnNames = {"Payment ID", "Reference No", "Amount", "Date", "Method", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        paymentsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(paymentsTable);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Payment ID:"));
        paymentIdField = new JTextField();
        paymentIdField.setEditable(false);
        formPanel.add(paymentIdField);
        
        formPanel.add(new JLabel("Reference No:"));
        referenceNoField = new JTextField();
        formPanel.add(referenceNoField);
        
        formPanel.add(new JLabel("Amount:"));
        amountField = new JTextField();
        formPanel.add(amountField);
        
        formPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        dateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(dateField);
        
        formPanel.add(new JLabel("Method:"));
        methodComboBox = new JComboBox<>(new String[]{"Cash", "Credit Card", "Debit Card", "Bank Transfer", "Online"});
        formPanel.add(methodComboBox);
        
        formPanel.add(new JLabel("Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Completed", "Pending", "Failed", "Refunded"});
        formPanel.add(statusComboBox);
        
        // Buttons Panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        
        addButton = new JButton("Add");
        updateButton = new JButton("Update");
        deleteButton = new JButton("Delete");
        loadButton = new JButton("Load");
        clearButton = new JButton("Clear");
        
        buttonPanel.add(addButton);
        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(loadButton);
        buttonPanel.add(clearButton);
        
        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addPayment();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updatePayment();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deletePayment();
            }
        });
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadPaymentsData();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        // Table selection listener
        paymentsTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedPayment();
                }
            }
        });
        
        // Layout
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel formContainer = new JPanel(new GridLayout(1, 2));
        formContainer.add(formPanel);
        formContainer.add(new JPanel()); // spacer
        
        northPanel.add(new JLabel("Payments Management", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(formContainer, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    
    private void loadPaymentsData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM payments";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("PaymentID"),
                    rs.getString("ReferenceNo"),
                    rs.getDouble("Amount"),
                    rs.getDate("Date"),
                    rs.getString("Method"),
                    rs.getString("Status")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading payments: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addPayment() {
        String referenceNo = referenceNoField.getText().trim();
        String amountText = amountField.getText().trim();
        String date = dateField.getText().trim();
        String method = methodComboBox.getSelectedItem().toString();
        String status = statusComboBox.getSelectedItem().toString();
        
        if (referenceNo.isEmpty() || amountText.isEmpty() || date.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double amount = Double.parseDouble(amountText);
            String sql = "INSERT INTO payments (ReferenceNo, Amount, Date, Method, Status) VALUES (?, ?, ?, ?, ?)";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, referenceNo);
                stmt.setDouble(2, amount);
                stmt.setDate(3, Date.valueOf(date));
                stmt.setString(4, method);
                stmt.setString(5, status);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Payment added successfully!");
                    loadPaymentsData();
                    clearForm();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding payment: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updatePayment() {
        String paymentId = paymentIdField.getText().trim();
        if (paymentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a payment to update", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String referenceNo = referenceNoField.getText().trim();
        String amountText = amountField.getText().trim();
        String date = dateField.getText().trim();
        String method = methodComboBox.getSelectedItem().toString();
        String status = statusComboBox.getSelectedItem().toString();
        
        try {
            double amount = Double.parseDouble(amountText);
            String sql = "UPDATE payments SET ReferenceNo = ?, Amount = ?, Date = ?, Method = ?, Status = ? WHERE PaymentID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, referenceNo);
                stmt.setDouble(2, amount);
                stmt.setDate(3, Date.valueOf(date));
                stmt.setString(4, method);
                stmt.setString(5, status);
                stmt.setInt(6, Integer.parseInt(paymentId));
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Payment updated successfully!");
                    loadPaymentsData();
                    clearForm();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for amount", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating payment: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deletePayment() {
        String paymentId = paymentIdField.getText().trim();
        if (paymentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a payment to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this payment?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM payments WHERE PaymentID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, Integer.parseInt(paymentId));
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Payment deleted successfully!");
                    loadPaymentsData();
                    clearForm();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting payment: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSelectedPayment() {
        int selectedRow = paymentsTable.getSelectedRow();
        if (selectedRow >= 0) {
            paymentIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            referenceNoField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            amountField.setText(tableModel.getValueAt(selectedRow, 2).toString());
            dateField.setValue(tableModel.getValueAt(selectedRow, 3));
            methodComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 4).toString());
            statusComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }
    
    // ADD THIS METHOD TO FIX THE ERROR
    public void clearForm() {
        paymentIdField.setText("");
        referenceNoField.setText("");
        amountField.setText("");
        dateField.setValue("");
        methodComboBox.setSelectedIndex(0);
        statusComboBox.setSelectedIndex(0);
        paymentsTable.clearSelection();
    }

}
