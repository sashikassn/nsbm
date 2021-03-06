package nsbm.models;

import nsbm.controllers.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class course {
    private String code,name,faculty;
    private int credits,numberofyears,can_extend;
    private Connection con=ConnectionManager.getConnection();
    public course(String code,String faculty,String name, int credits,int numberofyears, int can_extend){
        this.code=code;
        this.faculty=faculty;
        this.name=name;
        this.credits=credits;
        this.numberofyears=numberofyears;
        this.can_extend=can_extend;
    }
    public void setCode(String code){
        this.code=code;
    }
    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setCredits(int credits){
        this.credits=credits;
    }
    public void setNumberofyears(int numberofyears){
        this.numberofyears=numberofyears;
    }
    public void setCan_extend(int can_extend){
        this.can_extend=can_extend;
    }
    public String getCode(){
        return this.code;
    }
    public String getFaculty() {
        return faculty;
    }
    public String getName(){
        return this.name;
    }
    public int getNumberofyears() {
        return numberofyears;
    }
    public int getCan_extend() {
        return can_extend;
    }
    public int getCredits() {
        return credits;
    }
    public boolean save() throws SQLException {
        String query="INSERT INTO courses(code, name, credits, numberofyears, can_extend,faculty) values (?,?,?,?,?,?)";
        PreparedStatement insquery=con.prepareStatement(query);
        insquery.setString(1,this.code);
        insquery.setString(2,this.name);
        insquery.setInt(3,this.credits);
        insquery.setInt(4,this.numberofyears);
        insquery.setInt(5,this.can_extend);
        insquery.setString(6,this.faculty);
        int resutl=insquery.executeUpdate();
        return resutl>0;
    }
    public boolean update() throws SQLException{
        String query="UPDATE courses set code=?, name=?, credits=?, numberofyears=?, can_extend=?,faculty=? where code=?";
        PreparedStatement updatequery=con.prepareStatement(query);
        updatequery.setString(1,this.code);
        updatequery.setString(2,this.name);
        updatequery.setInt(3,this.credits);
        updatequery.setInt(4,this.numberofyears);
        updatequery.setInt(5,this.can_extend);
        updatequery.setString(6,this.faculty);
        updatequery.setString(7,this.code);
        int result=updatequery.executeUpdate();
        return result>0;
    }
    public void delete() throws SQLException{
        String query="DELETE FROM courses where code=?";
        PreparedStatement delquery=con.prepareStatement(query);
        delquery.setString(1,this.code);
        delquery.execute();
    }
    public static course find(String code) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query = "SELECT * FROM courses WHERE code=? LIMIT 1";
        PreparedStatement selectq=con.prepareStatement(query);
        selectq.setString(1,code);
        ResultSet result= selectq.executeQuery();
        String name=null, faculty=null;
        int credits=0,can_extend=0,numberofyears=0;
        while (result.next()){
            name=result.getString("name");
            faculty=result.getString("faculty");
            credits=result.getInt("credits");
            can_extend=result.getInt("can_extend");
            numberofyears=result.getInt("numberofyears");
        }
        return new course(code,faculty,name,credits,numberofyears,can_extend);
    }

    public static ArrayList<String> getall(String faculty_id) throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM courses where faculty=?";
        PreparedStatement findq=con.prepareStatement(query);
        findq.setString(1,faculty_id);
        ResultSet result=findq.executeQuery();
        ArrayList<String> arr = new ArrayList<String>();
        while (result.next()){
//            System.out.println(result.getString("code"));
            arr.add(result.getString("code"));
        }
        return arr;
    }
    public static ArrayList<course> getall() throws SQLException{
        Connection con=ConnectionManager.getConnection();
        String query="SELECT * FROM courses";
        PreparedStatement selectq=con.prepareStatement(query);
        ResultSet result=selectq.executeQuery();
        ArrayList<course> all=new ArrayList<>();
        while (result.next()){
            String insid = result.getString("code");
            all.add(find(insid));
        }
        return all;
    }
}
