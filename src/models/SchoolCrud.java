/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Tomeu
 */
public class SchoolCrud {

    private Connection conn;
    private Statement stat;
    private ResultSet rs;
    List<School> schoolList;

    public SchoolCrud() {
        this.getConnection();
        this.schoolList = new ArrayList<School>();
    }

    public void getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/schoolsystem", "root", "");
            stat = conn.createStatement();

        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

    }

    public List getAllSchools() {
        try {
            String query = "Select * from schools";
            rs = stat.executeQuery(query);
            while (rs.next()) {
                School school = new School();
                school.setId(rs.getInt("id"));
                school.setName(rs.getString("name"));
                school.setStudents(rs.getInt("students"));
                school.setPrice(rs.getInt("price"));
                schoolList.add(school);
                System.out.print(schoolList);
            }

        } catch (Exception ex) {
            System.out.print(ex);

        }
        return schoolList;
    }

    public void insertSchool(School school) {
        try {
            PreparedStatement ps = conn.prepareStatement("insert into schools(name,students,price) values (?,?,?) ");
            ps.setString(1, school.getName());
            ps.setInt(2, school.getStudents());
            ps.setInt(3, school.getPrice());
            ps.executeUpdate();
        } catch (Exception ex) {
            ex.getMessage();

        }
    }

    public void deleteSchool(int id) {
        try {
            PreparedStatement preparedStatement = conn.prepareStatement("delete from users where userid=?");
            // Parameters start with 1
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (Exception ex) {
            ex.getMessage();
        }

    }

    public void addSchool(School sc) {
        schoolList.add(sc);
    }

    public List getSchoolList() {
        return schoolList;
    }

}
