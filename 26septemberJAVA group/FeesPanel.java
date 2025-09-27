//SINDAYIGAYA SAMUEL 223004485
//NSABIYUMVA JEAN MARIE VIANNEY 223008033
//IRADUKUNDA Epiphanie 223015618package com.SFMS;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FeesPanel extends JPanel {
    private JTable feesTable;
    private DefaultTableModel tableModel;
    private JTextField feesIdField, nameField, attribute1Field, attribute2Field;
    private JTextArea descriptionArea;
    private JButton addButton, updateButton, deleteButton, loadButton, clearButton;
    
    public FeesPanel() {
        initializeUI();
        loadFeesData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Table
        String[] columnNames = {"Fees ID", "Name", "Description", "Attribute 1", "Attribute 2", "Created At"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        feesTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(feesTable);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Fees ID:"));
        feesIdField = new JTextField();
        feesIdField.setEditable(false);
        formPanel.add(feesIdField);
        
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Description:"));
        descriptionArea = new JTextArea(2, 20);
        JScrollPane descriptionScroll = new JScrollPane(descriptionArea);
        formPanel.add(descriptionScroll);
        
        formPanel.add(new JLabel("Attribute 1:"));
        attribute1Field = new JTextField();
        formPanel.add(attribute1Field);
        
        formPanel.add(new JLabel("Attribute 2:"));
        attribute2Field = new JTextField();
        formPanel.add(attribute2Field);
        
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
                addFee();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateFee();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFee();
            }
        });
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadFeesData();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        // Table selection listener
        feesTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting() && feesTable.getSelectedRow() != -1) {
                    loadSelectedFee();
                }
            }
        });
        
        // Layout
        JPanel northPanel = new JPanel(new BorderLayout());
        northPanel.add(new JLabel("Fees Management", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(formPanel, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    
    private void loadFeesData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM fees";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("FeesID"),
                    rs.getString("Name"),
                    rs.getString("Description"),
                    rs.getString("Attribute1"),
                    rs.getString("Attribute2"),
                    rs.getTimestamp("CreatedAt") != null ? 
                        rs.getTimestamp("CreatedAt").toLocalDateTime()
                            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) : "N/A"
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading fees: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addFee() {
        String name = nameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String attribute1 = attribute1Field.getText().trim();
        String attribute2 = attribute2Field.getText().trim();
        
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter fee name", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "INSERT INTO fees (Name, Description, Attribute1, Attribute2) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, attribute1);
            stmt.setString(4, attribute2);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Fee added successfully!");
                loadFeesData();
                clearForm();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding fee: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateFee() {
        if (feesIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a fee from the table to update", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int feesId = Integer.parseInt(feesIdField.getText().trim());
        String name = nameField.getText().trim();
        String description = descriptionArea.getText().trim();
        String attribute1 = attribute1Field.getText().trim();
        String attribute2 = attribute2Field.getText().trim();
        
        String sql = "UPDATE fees SET Name = ?, Description = ?, Attribute1 = ?, Attribute2 = ? WHERE FeesID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, description);
            stmt.setString(3, attribute1);
            stmt.setString(4, attribute2);
            stmt.setInt(5, feesId);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Fee updated successfully!");
                loadFeesData();
                clearForm();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating fee: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteFee() {
        if (feesIdField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a fee from the table to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int feesId = Integer.parseInt(feesIdField.getText().trim());
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this fee?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM fees WHERE FeesID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, feesId);
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Fee deleted successfully!");
                    loadFeesData();
                    clearForm();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting fee: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSelectedFee() {
        int selectedRow = feesTable.getSelectedRow();
        if (selectedRow >= 0) {
            feesIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            descriptionArea.setText(tableModel.getValueAt(selectedRow, 2) != null ? 
                tableModel.getValueAt(selectedRow, 2).toString() : "");
            attribute1Field.setText(tableModel.getValueAt(selectedRow, 3) != null ? 
                tableModel.getValueAt(selectedRow, 3).toString() : "");
            attribute2Field.setText(tableModel.getValueAt(selectedRow, 4) != null ? 
                tableModel.getValueAt(selectedRow, 4).toString() : "");
        }
    }
    
    public void clearForm() {
        feesIdField.setText("");
        nameField.setText("");
        descriptionArea.setText("");
        attribute1Field.setText("");
        attribute2Field.setText("");
        feesTable.clearSelection();
    }

}
