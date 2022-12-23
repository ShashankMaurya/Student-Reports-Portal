/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package student.login.dashboard;

//import java.text.SimpleDateFormat;

/**
 *
 * @author shashank
 */
public class Report {
    
    String lname, fname, ph, email, qual, special, exp, institute, dob, yop;
//    SimpleDateFormat sdf;
    int pass_marks, obtained_marks, total_marks;
    
    Report(){
        lname="";
        fname="";
        ph="";
        email="";
        qual="";
        special="";
        exp="";
        institute="";
        pass_marks=0;
        obtained_marks=0;
        total_marks=0;
        dob="";
        yop="";
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getYop() {
        return yop;
    }

    public void setYop(String yop) {
        this.yop = yop;
    }
    
    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getQual() {
        return qual;
    }

    public void setQual(String qual) {
        this.qual = qual;
    }

    public String getSpecial() {
        return special;
    }

    public void setSpecial(String special) {
        this.special = special;
    }

    public String getExp() {
        return exp;
    }

    public void setExp(String exp) {
        this.exp = exp;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

    public int getPass_marks() {
        return pass_marks;
    }

    public void setPass_marks(int pass_marks) {
        this.pass_marks = pass_marks;
    }

    public int getObtained_marks() {
        return obtained_marks;
    }

    public void setObtained_marks(int obtained_marks) {
        this.obtained_marks = obtained_marks;
    }

    public int getTotal_marks() {
        return total_marks;
    }

    public void setTotal_marks(int total_marks) {
        this.total_marks = total_marks;
    }
    
}
