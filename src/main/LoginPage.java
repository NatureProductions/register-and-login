
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginPage extends JFrame implements ActionListener {
     JLabel label5 = new JLabel("Enter your name");
     JTextField field1 = new JTextField(20);
      JLabel label6 = new JLabel("NAME");
     JTextField field5 = new JTextField(20);
      JLabel label7 = new JLabel("SURNAME");
     JTextField field6 = new JTextField(20);
      JLabel label8 = new JLabel("CLASS");
     JTextField field7 = new JTextField(20);   
     JButton get = new JButton("Get Me");
     JButton home = new JButton("Home");
     JButton check = new JButton("check");
     JButton update = new JButton("update");

    public LoginPage() {
        add(label5);
        add(field1);
        add(check);
        add(label6);
       add(field5);
       add(label7);
       add(field6);
       add(label8);
       add(field7);
        add(get);
       
        add(update);
        add(home);

        setSize(200, 300);
        setLayout(new FlowLayout());
        get.addActionListener(this);
       
        update.addActionListener(this);
        home.addActionListener(this);
         check.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String toSearch = field1.getText();

        if (e.getSource() == home) {
            Main v = new Main();
            v.setVisible(true);
            dispose();
        } else if (e.getSource() == check) {
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

                    field5.setText(name);
                    field6.setText(surname);
                    field7.setText(myclass);
                } else {
                    JOptionPane.showMessageDialog(this, "No record found for " + toSearch);
                }
            } catch (Exception ex) {
                System.out.print(ex);
            }
        } 
       else if (e.getSource() == update) {
            // Update functionality
            String username = toSearch; // Assuming username is the search field value
            String newName = field5.getText();
            String newSurname = field6.getText();
            String newClass = field7.getText();

            // Update logic using database manager class (replace with your implementation)
            boolean updateSuccessful = updateUserData(username, newName, newSurname, newClass);

            if (updateSuccessful) {
                JOptionPane.showMessageDialog(this, "User data updated successfully!");
                // Clear or disable display fields if desired
            } else {
                JOptionPane.showMessageDialog(this, "Error updating user data.");
            }
        }
    }

    // **Database Update Logic (replace with your actual implementation)**
    private boolean updateUserData(String username, String newName, String newSurname, String newClass) {
        try {
            // Replace with your database connection details
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost/classD", "root", "");
            PreparedStatement statement = connection.prepareStatement("UPDATE testing SET sname = ?, lname = ?, class = ? WHERE sname = ?");
            statement.setString(1, newName);
            statement.setString(2, newSurname);
            statement.setString(3, newClass);
            statement.setString(4, username);
            int rowsUpdated = statement.executeUpdate();

            if (rowsUpdated > 0) {
                return true; // Update successful
            } else {
                return false; // Update failed
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }


    }}