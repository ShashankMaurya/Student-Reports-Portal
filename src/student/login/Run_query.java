/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.login;

import student.login.dashboard.*;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Year;
import java.util.ArrayList;
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
    
    String create_query="INSERT INTO student.student_report (lname, fname, dob, ph_no, email, qual_type, special, yop, institute, pass_marks, obtain_marks, total_marks, exp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);";
    String edit_fetch_query="SELECT * FROM student.student_report WHERE email = ?";
    String edit_update_query="UPDATE student.student_report SET lname=?, fname=?, dob=?, ph_no=?, email=?, qual_type=?, special=?, yop=?, institute=?, pass_marks=?, obtain_marks=?, total_marks=?, exp=? WHERE email=?";

    String select_all_query="SELECT * FROM student.student_report";
    String select_filter_query="SELECT * FROM student.student_report WHERE qual_type=?";
    
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
    
    public int run_create_query(String lname, String fname, String dob, String ph, String email, String qual_type, String special, String expert, String yop, String institute, int pass_marks, int obtained_marks, int total_marks) throws ParseException{
        
        int flag = 0;
//        Year y=Year.of(Integer.parseInt(yop));
//        Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dob);
         try(Connection con = get_conn(); PreparedStatement ps=con.prepareStatement(create_query);){
//             INSERT INTO student.student_report (lname, fname, dob, ph_no, email, qual_type, special, yop, institute, pass_marks, obtain_marks, total_marks, exp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);
             ps.setString(1, lname);
             ps.setString(2, fname);
//             ps.setDate(3, (java.sql.Date)((Date) new SimpleDateFormat("dd/MM/yyyy").parse(dob)));
             ps.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(dob).getTime()));
             ps.setString(4, ph);
             ps.setString(5, email);
             ps.setString(6, qual_type);
             ps.setString(7, special);
//             ps.setDate(8, (java.sql.Date)((Date) new SimpleDateFormat("yyyy").parse(yop)));
//             ps.setDate(8, new java.sql.Date(new SimpleDateFormat("yyyy").parse(yop).getTime()));
//             ps.setDate(8, new java.sql.Date(Long.parseLong(yop)));
             ps.setObject(8, Year.of(Integer.parseInt(yop)).getValue());
             ps.setString(9, institute);
             ps.setInt(10, pass_marks);
             ps.setInt(11, obtained_marks);
             ps.setInt(12, total_marks);
             ps.setString(13, expert);
             
             int n=ps.executeUpdate();
             flag=1;
             con.close();
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
        return flag;
    }
    
    public Report run_edit_fetch_query(String email){
        
        ResultSet rs=null;
        Report report=new Report();
        
        try(Connection con=get_conn(); PreparedStatement ps=con.prepareStatement(edit_fetch_query);){
            ps.setString(1, email);
            
            rs=ps.executeQuery();
                
            if(rs.next())
            {
                report.setLname(rs.getString("lname"));
                report.setFname(rs.getString("fname"));
                report.setDob(rs.getDate("dob").toString());
                report.setPh(rs.getString("ph_no"));
                report.setEmail(rs.getString("email"));
                report.setQual(rs.getString("qual_type"));
                report.setSpecial(rs.getString("special"));
                report.setYop(rs.getObject("yop").toString());
                report.setInstitute(rs.getString("institute"));
                report.setPass_marks(rs.getInt("pass_marks"));
                report.setObtained_marks(rs.getInt("obtain_marks"));
                report.setTotal_marks(rs.getInt("total_marks"));
                report.setExp(rs.getString("exp"));
            }
            con.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        return report;
    } 
    
    public int run_edit_update_query(Report r, String pk_email) throws ParseException{
        
//        UPDATE student.student_report SET lname=?, fname=?, dob=?, ph_no=?, email=?, qual_type=?, special=?, yop=?, institute=?, pass_marks=?, obtain_marks=?, total_marks=?, exp=? WHERE email=?

        int flag = 0;
//        Year y=Year.of(Integer.parseInt(r.getYop()));
//        Date date = (Date) new SimpleDateFormat("dd/MM/yyyy").parse(dob);
        try(Connection con = get_conn(); PreparedStatement ps=con.prepareStatement(edit_update_query);){
//             INSERT INTO student.student_report (lname, fname, dob, ph_no, email, qual_type, special, yop, institute, pass_marks, obtain_marks, total_marks, exp) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?);
             ps.setString(1, r.getLname());
             ps.setString(2, r.getFname());
//             ps.setDate(3, (java.sql.Date)((Date) new SimpleDateFormat("dd/MM/yyyy").parse(dob)));
             ps.setDate(3, new java.sql.Date(new SimpleDateFormat("yyyy-MM-dd").parse(r.getDob()).getTime()));
             ps.setString(4, r.getPh());
             ps.setString(5, r.getEmail());
             ps.setString(6, r.getQual());
             ps.setString(7, r.getSpecial());
//             ps.setDate(8, (java.sql.Date)((Date) new SimpleDateFormat("yyyy").parse(yop)));
//             ps.setDate(8, new java.sql.Date(new SimpleDateFormat("yyyy").parse(yop).getTime()));
//             ps.setDate(8, new java.sql.Date(Long.parseLong(yop)));
             ps.setObject(8, Year.of(Integer.parseInt(r.getYop())).getValue());
             ps.setString(9, r.getInstitute());
             ps.setInt(10, r.getPass_marks());
             ps.setInt(11, r.getObtained_marks());
             ps.setInt(12, r.getTotal_marks());
             ps.setString(13, r.getExp());
             ps.setString(14, pk_email);
             
             int n=ps.executeUpdate();
             flag=1;
             con.close();
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
        return flag;
    }
    
    public ArrayList<Report> run_select_all_query(){
        
        ResultSet rs=null;
        ArrayList<Report> r = new ArrayList<>();
        
        try(Connection con=get_conn(); PreparedStatement ps=con.prepareStatement(select_all_query);){
            
            rs=ps.executeQuery();
                
            while(rs.next())
            {
                Report report=new Report();
                report.setLname(rs.getString("lname"));
                report.setFname(rs.getString("fname"));
                report.setDob(rs.getDate("dob").toString());
                report.setPh(rs.getString("ph_no"));
                report.setEmail(rs.getString("email"));
                report.setQual(rs.getString("qual_type"));
                report.setSpecial(rs.getString("special"));
                report.setYop(rs.getObject("yop").toString());
                report.setInstitute(rs.getString("institute"));
                report.setPass_marks(rs.getInt("pass_marks"));
                report.setObtained_marks(rs.getInt("obtain_marks"));
                report.setTotal_marks(rs.getInt("total_marks"));
                report.setExp(rs.getString("exp"));
                
                r.add(report);
            }
            con.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        return r;
    }
    
    public ArrayList<Report> run_filter_query(String course){
        
        ResultSet rs=null;
        ArrayList<Report> r = new ArrayList<>();
        
        try(Connection con=get_conn(); PreparedStatement ps=con.prepareStatement(select_filter_query);){
            
            ps.setString(1, course);
            rs=ps.executeQuery();
                
            while(rs.next())
            {
                Report report=new Report();
                report.setLname(rs.getString("lname"));
                report.setFname(rs.getString("fname"));
                report.setDob(rs.getDate("dob").toString());
                report.setPh(rs.getString("ph_no"));
                report.setEmail(rs.getString("email"));
                report.setQual(rs.getString("qual_type"));
                report.setSpecial(rs.getString("special"));
                report.setYop(rs.getObject("yop").toString());
                report.setInstitute(rs.getString("institute"));
                report.setPass_marks(rs.getInt("pass_marks"));
                report.setObtained_marks(rs.getInt("obtain_marks"));
                report.setTotal_marks(rs.getInt("total_marks"));
                report.setExp(rs.getString("exp"));
                
                r.add(report);
            }
            con.close();
        }
        catch(Exception E)
        {
            E.printStackTrace();
        }
        return r;
    }
    
}
