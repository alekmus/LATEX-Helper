/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.dao;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Aleksi
 */

public class MathDao implements Dao {
    private String dbloc;
    
    
    public MathDao(String databaselocation) {
        this.dbloc = "jdbc:sqlite:" + databaselocation;
    }
    
    @Override
    public String find(String str) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            System.out.println(e);
        }
        String result = "";
        
        try {
            Connection connection = DriverManager.getConnection(this.dbloc);
            
            
            String query = "Select * FROM mathformulas WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, str);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                result += rs.getString("formula");
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    public ArrayList<String> notations() throws Exception {
        Class.forName("org.sqlite.JDBC");
        ArrayList<String> nots = new ArrayList();
        
        try (Connection connection = DriverManager.getConnection(this.dbloc)) {
            String query = "Select * FROM mathformulas ORDER BY name ASC";
            ResultSet rs = connection.createStatement().executeQuery(query);
            
            while (rs.next()) {
                nots.add(rs.getString("name"));
            }
        }
        
        return nots;
    }
    
}
