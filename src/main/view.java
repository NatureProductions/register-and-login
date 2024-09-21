
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class view extends JFrame implements ActionListener {

    private JTextField field1 = new JTextField(20);
    private JLabel label1 = new JLabel("");
    private JLabel label2 = new JLabel("");
    private JLabel label3 = new JLabel("");
    private JButton get = new JButton("Get Me");
    private JButton home = new JButton("Home");
    private JButton delete = new JButton("Delete");
     private JButton update = new JButton("Update");

    public view() {
        add(field1);
        add(label1);
        add(label2);
        add(label3);
        add(get);
        add(update);
       
        add(delete);
        add(home);

        setSize(200, 300);
        setLayout(new FlowLayout());
        get.addActionListener(this);
       
        delete.addActionListener(this);
        home.addActionListener(this);
        update.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String toSearch = field1.getText();

        if (e.getSource() == home) {
            Main v = new Main();
            v.setVisible(true);
            dispose();
        }else if (e.getSource() == update) {
            LoginPage v = new LoginPage();
            v.setVisible(true);
            dispose();
        } else if (e.getSource() == get) {
            // Retrieve data based on the search field
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/classD", "root", "");
                Statement stmt = con.createStatement();

                String mydis = "SELECT * FROM testing WHERE sname = '" + toSearch + "'";
                ResultSet get = stmt.executeQuery(mydis);

                if (get.next()) {
                    String name = get.getString("sname");
                    String surname = get.getString("lname");
                    String myclass = get.getString("class");

                    label1.setText("My name is " + name);
                    label2.setText("My last name is " + surname);
                    label3.setText("I'm on class " + myclass);
                } else {
                    JOptionPane.showMessageDialog(this, "No record found for " + toSearch);
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } else if (e.getSource() == delete) {
            // Implement delete functionality here
            try {
                Connection con = DriverManager.getConnection("jdbc:mysql://localhost/classD", "root", "");
                Statement stmt = con.createStatement();

                String deleteQuery = "DELETE FROM testing WHERE sname = '" + toSearch + "'";
                int rowsAffected = stmt.executeUpdate(deleteQuery);

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(this, "Record deleted successfully!");
                    // Clear labels for visual feedback
                    label1.setText("");
                    label2.setText("");
                    label3.setText("");
                } else {
                    JOptionPane.showMessageDialog(this, "No record found for deletion!");
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        }
    }
}