// SINDAYIGAYA Samuel 223004485
package RegistrationSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;

public class EnhancedUserRegistrationSystem {
    private JFrame frame;
    private JTextField nameField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton, resetButton, viewUsersButton;
    private JLabel statusLabel;
    private JCheckBox showPasswordCheckBox;
    
    private Map<String, User> userDatabase;
    
    public EnhancedUserRegistrationSystem() {
        userDatabase = new HashMap<>();
        initializeEnhancedGUI();
    }
    
    private void initializeEnhancedGUI() {
        frame = new JFrame("Enhanced User Registration System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(450, 400);
        frame.setLayout(new BorderLayout(10, 10));
        
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Title
        JLabel titleLabel = new JLabel("User Registration Form", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(0, 100, 0));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(10, 5, 25, 5);
        mainPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 5, 5, 5);
        
        // Name Field
        addFormField(mainPanel, gbc, "Name:", nameField = new JTextField(20), 1);
        
        // Username Field
        addFormField(mainPanel, gbc, "UserName:", usernameField = new JTextField(20), 2);
        
        // Password Field
        addFormField(mainPanel, gbc, "Password:", passwordField = new JPasswordField(20), 3);
        
        // Confirm Password Field
        addFormField(mainPanel, gbc, "Confirm Password:", 
                    confirmPasswordField = new JPasswordField(20), 4);
        
        // Show Password Checkbox
        showPasswordCheckBox = new JCheckBox("Show Password");
        gbc.gridx = 1;
        gbc.gridy = 5;
        mainPanel.add(showPasswordCheckBox, gbc);
        int e;
		//showPasswordCheckBox.addActionListener(e -> togglePasswordVisibility());
        
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        
        registerButton = createStyledButton("Register", new Color(34, 139, 34));
        resetButton = createStyledButton("Reset", new Color(220, 20, 60));
        viewUsersButton = createStyledButton("View Users", new Color(70, 130, 180));
        
        buttonPanel.add(registerButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(viewUsersButton);
        
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        mainPanel.add(buttonPanel, gbc);
        
        // Status Label
        statusLabel = new JLabel("Welcome! Please fill in the registration form.", JLabel.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        gbc.gridx = 0;
        gbc.gridy = 7;
        mainPanel.add(statusLabel, gbc);
        
        // Add listeners
        addEventListeners();
        
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.setLocationRelativeTo(null);
    }
    
    private void addFormField(JPanel panel, GridBagConstraints gbc, String labelText, 
                            JComponent field, int row) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(label, gbc);
        
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(field, gbc);
    }
    
    private JButton createStyledButton(String text, final Color color) {
        final JButton button = new JButton(text);
        button.setBackground(color);
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        button.setBorder(BorderFactory.createEmptyBorder(8, 15, 8, 15));
        button.setFont(new Font("Arial", Font.BOLD, 12));
        
        // Add hover effect
        button.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                button.setBackground(color.darker());
            }
            public void mouseExited(MouseEvent e) {
                button.setBackground(color);
            }
        });
        
        return button;
    }
    
    private void addEventListeners() {
        int e;
		//registerButton.addActionListener(e -> registerUser());
       // resetButton.addActionListener(e -> resetForm());
       // viewUsersButton.addActionListener(e -> showRegisteredUsers());
        
        // Add Enter key support for registration
        frame.getRootPane().setDefaultButton(registerButton);
    }
    
    private void registerUser() {
        String name = nameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        // Validation
        if (name.isEmpty() || username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All fields are required!");
            return;
        }
        
        if (!password.equals(confirmPassword)) {
            showError("Passwords do not match!");
            return;
        }
        
        if (password.length() < 6) {
            showError("Password must be at least 6 characters long!");
            return;
        }
        
        if (userDatabase.containsKey(username)) {
            showError("Username already exists! Please choose another one.");
            return;
        }
        
        // Registration successful
        User newUser = new User(name, username, password);
        userDatabase.put(username, newUser);
        
        showSuccess("Registration successful! Welcome, " + name + "!");
        resetForm();
    }
    
    private void resetForm() {
        nameField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
        nameField.requestFocus();
        showInfo("Form reset successfully.");
    }
    
    private void showRegisteredUsers() {
        if (userDatabase.isEmpty()) {
            showInfo("No users registered yet.");
            return;
        }
        
        StringBuilder usersList = new StringBuilder("Registered Users:\n\n");
        for (User user : userDatabase.values()) {
            usersList.append("Name: ").append(user.getName())
                    .append(" | Username: ").append(user.getUsername())
                    .append("\n");
        }
        
        JOptionPane.showMessageDialog(frame, usersList.toString(), 
                                    "Registered Users", JOptionPane.INFORMATION_MESSAGE);
    }
    
    private void togglePasswordVisibility() {
        boolean show = showPasswordCheckBox.isSelected();
        passwordField.setEchoChar(show ? (char) 0 : '•');
        confirmPasswordField.setEchoChar(show ? (char) 0 : '•');
    }
    
    private void showError(String message) {
        statusLabel.setForeground(Color.RED);
        statusLabel.setText(message);
    }
    
    private void showSuccess(String message) {
        statusLabel.setForeground(new Color(0, 100, 0));
        statusLabel.setText(message);
    }
    
    private void showInfo(String message) {
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setText(message);
    }
    
    public void show() {
        frame.setVisible(true);
    }
    
    // User class
    private static class User {
        private String name, username, password;
        
        public User(String name, String username, String password) {
            this.name = name;
            this.username = username;
            this.password = password;
        }
        
        public String getName() { return name; }
        public String getUsername() { return username; }
        public String getPassword() { return password; }
    }
    
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //SwingUtilities.invokeLater(() -> {
          new EnhancedUserRegistrationSystem().show();
        };
   }

