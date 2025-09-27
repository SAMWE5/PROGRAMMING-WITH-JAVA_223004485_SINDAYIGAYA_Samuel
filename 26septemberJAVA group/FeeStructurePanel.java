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

public class FeeStructurePanel extends JPanel {
    private JTable feeStructureTable;
    private DefaultTableModel tableModel;
    private JTextField feeStructureIdField, titleField, valueField;
    private JTextArea notesArea;
    private JComboBox<String> statusComboBox;
    private JFormattedTextField dateField;
    private JButton addButton, updateButton, deleteButton, loadButton, clearButton;
    
    public FeeStructurePanel() {
        initializeUI();
        loadFeeStructureData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Table
        String[] columnNames = {"Fee Structure ID", "Title", "Date", "Status", "Value", "Notes"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        feeStructureTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(feeStructureTable);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Fee Structure ID:"));
        feeStructureIdField = new JTextField();
        feeStructureIdField.setEditable(false);
        formPanel.add(feeStructureIdField);
        
        formPanel.add(new JLabel("Title:"));
        titleField = new JTextField();
        formPanel.add(titleField);
        
        formPanel.add(new JLabel("Date (yyyy-mm-dd):"));
        dateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(dateField);
        
        formPanel.add(new JLabel("Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Active", "Inactive", "Pending"});
        formPanel.add(statusComboBox);
        
        formPanel.add(new JLabel("Value:"));
        valueField = new JTextField();
        formPanel.add(valueField);
        
        formPanel.add(new JLabel("Notes:"));
        notesArea = new JTextArea(3, 20);
        JScrollPane notesScroll = new JScrollPane(notesArea);
        formPanel.add(notesScroll);
        
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
                addFeeStructure();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFeeStructure();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFeeStructure();
            }
        });
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFeeStructureData();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        // Table selection listener
        feeStructureTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedFeeStructure();
                }
            }
        });
        
        // Layout
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel formContainer = new JPanel(new GridLayout(1, 2));
        formContainer.add(formPanel);
        formContainer.add(new JPanel()); // spacer
        
        northPanel.add(new JLabel("Fee Structure Management", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(formContainer, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    
    private void loadFeeStructureData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM fee_structure";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("FeeStructureID"),
                    rs.getString("Title"),
                    rs.getDate("Date"),
                    rs.getString("Status"),
                    rs.getDouble("Value"),
                    rs.getString("Notes")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading fee structure: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addFeeStructure() {
        String title = titleField.getText().trim();
        String date = dateField.getText().trim();
        String status = statusComboBox.getSelectedItem().toString();
        String valueText = valueField.getText().trim();
        String notes = notesArea.getText().trim();
        
        if (title.isEmpty() || date.isEmpty() || valueText.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            double value = Double.parseDouble(valueText);
            String sql = "INSERT INTO fee_structure (Title, Date, Status, Value, Notes) VALUES (?, ?, ?, ?, ?)";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, title);
                stmt.setDate(2, Date.valueOf(date));
                stmt.setString(3, status);
                stmt.setDouble(4, value);
                stmt.setString(5, notes);
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Fee structure added successfully!");
                    loadFeeStructureData();
                    clearForm();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for value", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding fee structure: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateFeeStructure() {
        String feeStructureId = feeStructureIdField.getText().trim();
        if (feeStructureId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a fee structure to update", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String title = titleField.getText().trim();
        String date = dateField.getText().trim();
        String status = statusComboBox.getSelectedItem().toString();
        String valueText = valueField.getText().trim();
        String notes = notesArea.getText().trim();
        
        try {
            double value = Double.parseDouble(valueText);
            String sql = "UPDATE fee_structure SET Title = ?, Date = ?, Status = ?, Value = ?, Notes = ? WHERE FeeStructureID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setString(1, title);
                stmt.setDate(2, Date.valueOf(date));
                stmt.setString(3, status);
                stmt.setDouble(4, value);
                stmt.setString(5, notes);
                stmt.setInt(6, Integer.parseInt(feeStructureId));
                
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Fee structure updated successfully!");
                    loadFeeStructureData();
                    clearForm();
                }
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid number for value", 
                "Error", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating fee structure: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteFeeStructure() {
        String feeStructureId = feeStructureIdField.getText().trim();
        if (feeStructureId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a fee structure to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this fee structure?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM fee_structure WHERE FeeStructureID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, Integer.parseInt(feeStructureId));
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Fee structure deleted successfully!");
                    loadFeeStructureData();
                    clearForm();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting fee structure: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSelectedFeeStructure() {
        int selectedRow = feeStructureTable.getSelectedRow();
        if (selectedRow >= 0) {
            feeStructureIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            titleField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            dateField.setValue(tableModel.getValueAt(selectedRow, 2));
            statusComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3).toString());
            valueField.setText(tableModel.getValueAt(selectedRow, 4).toString());
            notesArea.setText(tableModel.getValueAt(selectedRow, 5) != null ? 
                tableModel.getValueAt(selectedRow, 5).toString() : "");
        }
    }
    
    public void clearForm() {
        feeStructureIdField.setText("");
        titleField.setText("");
        dateField.setValue("");
        statusComboBox.setSelectedIndex(0);
        valueField.setText("");
        notesArea.setText("");
        feeStructureTable.clearSelection();
    }
}

