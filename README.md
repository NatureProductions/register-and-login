<h1>LOGIN FORM</h1>

<h2>Description</h2>
In this project I created a simple Login form using JAVA Jframes in NetBeans. I want show my abilities to connect any 
Java programm to the database.I can do more than this project, this is just a simple demonstrait the conectivity of java and database.
And also show  So if the user is not registered, the is a signup option for registration. if the user
is registered or found in the database he/she will be able to login.
<br />


<h2>Languages and Utilities Used</h2>
- <b>JAVA</b> 
- <b>MySQL</b>


<h2>Application Used</h2>
-	<b>NetBeans</b> (21H2)

<h2>Program walk-through:</h2>

<p align=”center”>

This is the login Form, User have to input user name and password. If Username password is not in the database
an error will show up telling the user to register first.<br/>

![image](https://github.com/user-attachments/assets/e6c1cd64-eb08-43f0-90cc-f6789cfbb939)

<br />

<br />

This is the registration Form:  <br/>
![image](https://github.com/user-attachments/assets/9fe9e8ba-6daf-4b6b-900e-9dc24e2b2ee3)<br />
This isthe register values in the database  <br/>

![image](https://github.com/user-attachments/assets/a041527b-c50b-4dc3-b880-154f9d488972)






<br />

This is the page after Login page: <br/>

![image](https://github.com/user-attachments/assets/dac83c73-d7fd-4eed-a319-d3bf16966101)
<br />
It culculates any Two numbers. After entering the values, press and get summ buttonand the answers will appear. </br>
![image](https://github.com/user-attachments/assets/b5902399-8412-42b1-936e-6ffc2bde7292)

<br />

<br />

If the user is not found in the database this will display :  <br/>
![image](https://github.com/user-attachments/assets/e87f9330-bc6c-4b37-ae2d-f845fa6ee865)

<br />

<br />

<br/>

This is the Login code




 ```diff

-	package main;

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


```




