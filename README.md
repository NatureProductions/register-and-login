<h1>LOGIN FORM</h1>

<h2>Description</h2>
In this project I created a simple Login form using JAVA Jframes in NetBeans. I want show my abilities to connect any 
Java programm to the database.I can do more than this project, this is just a simple demonstrait the conectivity of java and database.
And also show  So if the user is not registered, the is a signup option for registration. if the user
is registered or found in the database he/she will be able to login.
<br />


<h2>Languages and Utilities Used</h2>
- <b>JAVA</br> 
- <b>MySQL</br>
- <b>MySQL_connector</br>



<h2>Application Used</h2>
-	<b>NetBeans</b> (21H2)

<h2>Program walk-through:</h2>

<p align=”center”>

This is the login Form, User have to input user name and password. If Username password is not in the database
an error will show up telling the user to register first.<br/>

![image](https://github.com/user-attachments/assets/e6c1cd64-eb08-43f0-90cc-f6789cfbb939)

<br />

If the user is not found in the database this will display :  <br/>
![image](https://github.com/user-attachments/assets/ac475f8b-32c4-4ea0-8025-e652125547f1)

<br />

<br />

This is the registration Form:  <br/>
![image](https://github.com/user-attachments/assets/9fe9e8ba-6daf-4b6b-900e-9dc24e2b2ee3)<br />

This is the registered people in the database  <br/>

![image](https://github.com/user-attachments/assets/7b551ce0-f84e-4835-aed1-115d4907b433)


<br />

This is the page after Login page is has 4 options, the getme button will retrieve the user's information from database, the UPDATE button will
allow you to make changes on the user's information, DELETE button will remove the user from the database, HOME take yu to the login page.<br/>

![image](https://github.com/user-attachments/assets/c4f6de01-9869-41b3-b173-c834f854df59)

<br />

This is for the GETME button, but first you have to input the name of the user. </br>
![image](https://github.com/user-attachments/assets/372e14f6-28d2-46fe-ad5c-85f7a39a90ed)

<br />
This is for the UPDATE button, but first you have to input the name of the user. </br>
![image](https://github.com/user-attachments/assets/90b3becb-b54b-4f17-8ac8-8fe5ae89dfdf)
</br>

This will show when you click the CHECK button
![image](https://github.com/user-attachments/assets/32185343-c38a-4b06-8374-4ca61a74cdba)
</br>
<br/>
After you click the UPDATE button
![image](https://github.com/user-attachments/assets/1dd149b6-058e-43b1-a4a4-28cc0e0f47a5)

</br>



<br /> 

<br/>
This is for DELETE button
![image](https://github.com/user-attachments/assets/a5339656-857b-4c2d-be11-c1cd24732fac)
</br>
<br />
if User is not found in the database this will show up
![image](https://github.com/user-attachments/assets/a803a311-c45c-4225-b132-28087f2eddc5)

<br />

</br>

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




