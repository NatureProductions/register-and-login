package main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Main extends JFrame implements ActionListener {
    JLabel tit = new JLabel("Enter User_Name");
    JTextField fieldn = new JTextField(20);
    JLabel titl = new JLabel("Enter Password");
    JTextField field1 = new JTextField(20);
    JButton log = new JButton("Login");
    JButton sign = new JButton("Signup");

    public Main() {
        add(tit);
        add(fieldn);
        add(titl);
        add(field1);
        add(log);
        add(sign);

        setSize(200, 300);
        setLayout(new FlowLayout());

        log.addActionListener(this);
        sign.addActionListener(this);
    }

    public static void main(String[] args) {
        Main o = new Main();
        o.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == log) {
            String username = fieldn.getText();
            if (authenticateUser(username)) {
                // Successful login, navigate to view page
                view v = new view();
                v.setVisible(true);
                dispose();
            } else {
                // Invalid username, display error message
                JOptionPane.showMessageDialog(this, "Invalid username.");
            }
        } else if (e.getSource() == sign) {
            // Handle registration logic
            insert i = new insert();
            i.setVisible(true);
            dispose();
        }
    }

    private boolean authenticateUser(String username) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/classD", "root", "");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Testing WHERE sname = '" + username + "'");

            if (resultSet.next()) {
                // User found, authentication successful
                return true;
            } else {
                // User not found, authentication failed
                return false;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}