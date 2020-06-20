package com.grafon.test;

import com.grafon.test.calendarDB.CalendarTasks;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class UserLogin extends JFrame {

    private static final long serialVersionUID = 1L;
    private final JTextField textField;
    private final JPasswordField passwordField;
    private final JButton btnNewButton;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {

        UserLogin frame = new UserLogin();
        frame.setVisible(true);

    }

    public UserLogin() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(450, 190, 1014, 597);
        setResizable(false);
        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JLabel lblNewLabel = new JLabel("Login");
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setFont(new Font("Times New Roman", Font.PLAIN, 46));
        lblNewLabel.setBounds(423, 13, 273, 93);
        contentPane.add(lblNewLabel);

        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        textField.setBounds(481, 170, 281, 68);
        contentPane.add(textField);
        textField.setColumns(10);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Tahoma", Font.PLAIN, 32));
        passwordField.setBounds(481, 286, 281, 68);
        contentPane.add(passwordField);

        JLabel lblUsername = new JLabel("Username");
        lblUsername.setBackground(Color.BLACK);
        lblUsername.setForeground(Color.BLACK);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblUsername.setBounds(250, 166, 193, 52);
        contentPane.add(lblUsername);

        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.BLACK);
        lblPassword.setBackground(Color.CYAN);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 31));
        lblPassword.setBounds(250, 286, 193, 52);
        contentPane.add(lblPassword);

        btnNewButton = new JButton("Login");
        btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 26));
        btnNewButton.setBounds(545, 392, 162, 73);
        btnNewButton.addActionListener(e -> {
            String userName = textField.getText();
            String password = new String(passwordField.getPassword());
            try {
                Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/login_password?serverTimezone=UTC",
                        "Grafon", "1q2w3e");

                PreparedStatement st = connection
                        .prepareStatement("Select name, password from user where name=? and password=?");

                st.setString(1, userName);
                st.setString(2, password);
                ResultSet rs = st.executeQuery();
                if (rs.next()) {
                    dispose();
                   CalendarTasks ah = new CalendarTasks(userName,connection);
                    JOptionPane.showMessageDialog(btnNewButton, "You have successfully logged in");
                } else {
                    JOptionPane.showMessageDialog(btnNewButton, "Wrong Username & Password");
                }
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        });

        contentPane.add(btnNewButton);

        JLabel label = new JLabel("");
        label.setBounds(0, 0, 1008, 562);
        contentPane.add(label);
    }
}