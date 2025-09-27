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

public class StudentsPanel extends JPanel {
    private JTable studentsTable;
    private DefaultTableModel tableModel;
    private JTextField studentIdField, nameField;
    private JComboBox<String> typeComboBox, statusComboBox;
    private JFormattedTextField startDateField, endDateField;
    private JButton addButton, updateButton, deleteButton, loadButton, clearButton;
    
    public StudentsPanel() {
        initializeUI();
        loadStudentsData();
    }
    
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        
        // Table
        String[] columnNames = {"Student ID", "Name", "Type", "Start Date", "End Date", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        studentsTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(studentsTable);
        
        // Form Panel
        JPanel formPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        
        formPanel.add(new JLabel("Student ID:"));
        studentIdField = new JTextField();
        studentIdField.setEditable(false);
        formPanel.add(studentIdField);
        
        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);
        
        formPanel.add(new JLabel("Type:"));
        typeComboBox = new JComboBox<>(new String[]{"Regular", "Scholarship", "International", "Part-time"});
        formPanel.add(typeComboBox);
        
        formPanel.add(new JLabel("Start Date (yyyy-mm-dd):"));
        startDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(startDateField);
        
        formPanel.add(new JLabel("End Date (yyyy-mm-dd):"));
        endDateField = new JFormattedTextField(new SimpleDateFormat("yyyy-MM-dd"));
        formPanel.add(endDateField);
        
        formPanel.add(new JLabel("Status:"));
        statusComboBox = new JComboBox<>(new String[]{"Active", "Inactive", "Graduated", "Suspended"});
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
                addStudent();
            }
        });
        
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateStudent();
            }
        });
        
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteStudent();
            }
        });
        
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadStudentsData();
            }
        });
        
        clearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });
        
        // Table selection listener
        studentsTable.getSelectionModel().addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    loadSelectedStudent();
                }
            }
        });
        
        // Layout
        JPanel northPanel = new JPanel(new BorderLayout());
        JPanel formContainer = new JPanel(new GridLayout(1, 2));
        formContainer.add(formPanel);
        formContainer.add(new JPanel()); // spacer
        
        northPanel.add(new JLabel("Students Management", JLabel.CENTER), BorderLayout.NORTH);
        northPanel.add(formContainer, BorderLayout.CENTER);
        northPanel.add(buttonPanel, BorderLayout.SOUTH);
        
        add(northPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }
    
    private void loadStudentsData() {
        tableModel.setRowCount(0);
        String sql = "SELECT * FROM students";
        
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            
            while (rs.next()) {
                Object[] row = {
                    rs.getInt("StudentID"),
                    rs.getString("Name"),
                    rs.getString("Type"),
                    rs.getDate("StartDate"),
                    rs.getDate("EndDate"),
                    rs.getString("Status")
                };
                tableModel.addRow(row);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error loading students: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void addStudent() {
        String name = nameField.getText().trim();
        String type = typeComboBox.getSelectedItem().toString();
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String status = statusComboBox.getSelectedItem().toString();
        
        if (name.isEmpty() || startDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill all required fields", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String sql = "INSERT INTO students (Name, Type, StartDate, EndDate, Status) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, endDate.isEmpty() ? null : Date.valueOf(endDate));
            stmt.setString(5, status);
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student added successfully!");
                loadStudentsData();
                clearForm();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error adding student: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateStudent() {
        String studentId = studentIdField.getText().trim();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a student to update", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        String name = nameField.getText().trim();
        String type = typeComboBox.getSelectedItem().toString();
        String startDate = startDateField.getText().trim();
        String endDate = endDateField.getText().trim();
        String status = statusComboBox.getSelectedItem().toString();
        
        String sql = "UPDATE students SET Name = ?, Type = ?, StartDate = ?, EndDate = ?, Status = ? WHERE StudentID = ?";
        
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, name);
            stmt.setString(2, type);
            stmt.setDate(3, Date.valueOf(startDate));
            stmt.setDate(4, endDate.isEmpty() ? null : Date.valueOf(endDate));
            stmt.setString(5, status);
            stmt.setInt(6, Integer.parseInt(studentId));
            
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(this, "Student updated successfully!");
                loadStudentsData();
                clearForm();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error updating student: " + ex.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void deleteStudent() {
        String studentId = studentIdField.getText().trim();
        if (studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a student to delete", 
                "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        int confirm = JOptionPane.showConfirmDialog(this, 
            "Are you sure you want to delete this student?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM students WHERE StudentID = ?";
            
            try (Connection conn = DBConnection.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {
                
                stmt.setInt(1, Integer.parseInt(studentId));
                int rowsAffected = stmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Student deleted successfully!");
                    loadStudentsData();
                    clearForm();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error deleting student: " + ex.getMessage(), 
                    "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void loadSelectedStudent() {
        int selectedRow = studentsTable.getSelectedRow();
        if (selectedRow >= 0) {
            studentIdField.setText(tableModel.getValueAt(selectedRow, 0).toString());
            nameField.setText(tableModel.getValueAt(selectedRow, 1).toString());
            typeComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 2).toString());
            startDateField.setValue(tableModel.getValueAt(selectedRow, 3));
            endDateField.setValue(tableModel.getValueAt(selectedRow, 4));
            statusComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 5).toString());
        }
    }
    
    public void clearForm() {
        studentIdField.setText("");
        nameField.setText("");
        typeComboBox.setSelectedIndex(0);
        startDateField.setValue("");
        endDateField.setValue("");
        statusComboBox.setSelectedIndex(0);
        studentsTable.clearSelection();
    }

}
