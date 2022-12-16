/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.login;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author shashank
 */
public class Run_query {
    
    String jdbc_link="jdbc:mysql://localhost:3306/";
    String jdbc_name="root";
    String jdbc_pass="260101";
    
    // make all queries here as strings
    String login_query="SELECT * FROM student.student_login WHERE user_id = ? AND password = ?";
    String signup_query="INSERT INTO student.student_login (user_id, password, name, email) VALUES (?,?,?,?);";

//    Run_query()
//    {
//        try {
//            Connection con=DriverManager.getConnection(jdbc_link,jdbc_name,jdbc_pass);
//        } catch (SQLException ex) {
//            Logger.getLogger(Run_query.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public Connection get_conn()
    {
        Connection con=null;
        try {
            con=DriverManager.getConnection(jdbc_link,jdbc_name,jdbc_pass);
        } catch (SQLException ex) {
            Logger.getLogger(Run_query.class.getName()).log(Level.SEVERE, null, ex);
        }
        return con;
    }
    
    public Student run_login_query(String userid, String pass){
        
//        Connection con;
//        PreparedStatement stmt;
        ResultSet rs=null;
//        boolean b=false;
        Student student=new Student();
        
            try(Connection con=get_conn(); PreparedStatement ps=con.prepareStatement(login_query);){
                ps.setString(1, userid);
                ps.setString(2, pass);
                
                rs=ps.executeQuery();
                
                if(rs.next())
                {
//                    b=true;
                    student.setName(rs.getString("name"));
                    student.setUserid(rs.getString("user_id"));
                    student.setEmail(rs.getString("email"));
                    student.setPass(rs.getString("password"));
                }
                con.close();
//                return rs;
            }
            catch(Exception E)
            {
                E.printStackTrace();
            }
//            stmt=con.prepareStatement(query);
//            rs=stmt.executeQuery();
//            con.close();
//            return rs;
            return student;
    }
    
    public int run_signup_query(String userid, String pass, String name, String email){
        
//        ResultSet rs=null;
//        boolean b=false;
        int flag=0;  // 0: Failed, 1: Successful, -1: SQLIntegrityConstraintViolationException
        
            try(Connection con=get_conn(); PreparedStatement ps=con.prepareStatement(signup_query);){
                ps.setString(1, userid);
                ps.setString(2, pass);
                ps.setString(3, name);
                ps.setString(4, email);
                
                int n=ps.executeUpdate();
                flag=1;
//                b = true;
                
                con.close();
//                return rs;
            }
            catch(java.sql.SQLIntegrityConstraintViolationException E)
            {
//                b=false;
                flag=-1;
            }
            catch(Exception E)
            {
                E.printStackTrace();
//                b=false;
            }
//            stmt=con.prepareStatement(query);
//            rs=stmt.executeQuery();
//            con.close();
//            return rs;
             return flag;
    }
    
}
