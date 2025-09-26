package com.SFMS;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainApplication extends JFrame {
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private LoginFormPanel loginPanel;
    private UserPanel userPanel;
    private FeesPanel feesPanel;
    private FeeStructurePanel feeStructurePanel;
    private StudentsPanel studentsPanel;
    private PaymentsPanel paymentsPanel;
    
    public MainApplication() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("School Fee Management System - Group 28");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        
        // Create panels
        loginPanel = new LoginFormPanel(this);
        userPanel = new UserPanel();
        feesPanel = new FeesPanel();
        feeStructurePanel = new FeeStructurePanel();
        studentsPanel = new StudentsPanel();
        paymentsPanel = new PaymentsPanel();
        
        // Add panels to card layout
        mainPanel.add(loginPanel, "Login");
        mainPanel.add(createMainInterface(), "Main");
        
        add(mainPanel);
        cardLayout.show(mainPanel, "Login");
    }
    
    private JPanel createMainInterface() {
        JPanel mainInterface = new JPanel(new BorderLayout());
        
        // Create tabbed pane
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Users", userPanel);
        tabbedPane.addTab("Fees", feesPanel);
        tabbedPane.addTab("Fee Structure", feeStructurePanel);
        tabbedPane.addTab("Students", studentsPanel);
        tabbedPane.addTab("Payments", paymentsPanel);
        
        // Header
        JPanel headerPanel = new JPanel(new BorderLayout());
        JLabel headerLabel = new JLabel("School Fee Management System - Group 28", JLabel.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setForeground(Color.BLUE);
        headerPanel.add(headerLabel, BorderLayout.CENTER);
        
        // Logout button
        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            public void actionPerformed1(ActionEvent e) {
                cardLayout.show(mainPanel, "Login");
                clearFormFields();
            }

			
			public void actionPerformed(ActionEvent e ) {
				// TODO Auto-generated method stub
				
			}
        });
        headerPanel.add(logoutButton, BorderLayout.EAST);
        
        mainInterface.add(headerPanel, BorderLayout.NORTH);
        mainInterface.add(tabbedPane, BorderLayout.CENTER);
        
        return mainInterface;
    }
    
    private void clearFormFields() {
        // Clear all form fields when logging out
        userPanel.clearForm();
        feesPanel.clearForm();
        feeStructurePanel.clearForm();
        studentsPanel.clearForm();
        paymentsPanel.clearForm();
    }
    
    public void showMainPanel() {
        cardLayout.show(mainPanel, "Main");
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
                new MainApplication().setVisible(true);
            }
        });
    }
}