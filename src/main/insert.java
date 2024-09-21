
package main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class insert extends JFrame implements ActionListener {
    JLabel name = new JLabel("Enter Name");
   JTextField field2 = new JTextField(20);
   JLabel surname = new JLabel("Enter surname");   
   JTextField field3 = new JTextField(20);
   JLabel classs = new JLabel("Enter class");
   JTextField field4 = new JTextField(20);
   JButton insert = new JButton("insert");
    JButton homee = new JButton("Home");
    
   
   public insert(){
       add(name);
       add(field2);
       add(surname);
       add(field3);
       add(classs);
       add(field4);
       add(insert);
       add(homee);
       
       
       setSize(300,300);
       setLayout(new FlowLayout());
       insert.addActionListener(this);
       homee.addActionListener(this);
       
   }
    
    
    
    @Override
    public void actionPerformed(ActionEvent e) {
    String n = field2.getText();
    String q = field3.getText();
    String s = field4.getText();
    
     if(e.getSource()== homee){
          Main v =new Main();
          v.setVisible(true);
          dispose();
      }
    
     else  if(e.getSource()== insert){
    try{
       Connection con = DriverManager.getConnection("jdbc:mysql://localhost/classD","root","");
       Statement stmt = con.createStatement();
       
       String mydis = "Insert into testing values ('"+n+"','"+q+"','"+s+"')";
       int updateMe= stmt.executeUpdate(mydis);
        JOptionPane.showMessageDialog(this, "User data inserted successfully!");
      
       
       
    }   
    
    catch (SQLException ex) {
        
            System.out.print(ex);
        }
     }
        
         
        
    }
    
}
