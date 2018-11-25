/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper.domain;

import java.sql.*;
import java.util.*;

/**
 *
 * @author Aleksi
 */

public class MathFormulaAccessor{
    private String dbloc;
    
    
    public MathFormulaAccessor(String databaselocation){
        this.dbloc = "jdbc:sqlite:"+databaselocation;
    }
    
    public String find(String str) throws Exception{
        Class.forName("org.sqlite.JDBC");
        String result = "";
        
        try(Connection connection = DriverManager.getConnection(this.dbloc)){
            
            String query = "Select * FROM mathformulas WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1,str);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                result += rs.getString("formula");
            }
            
        };
        
        
        return result;
    }
    
    public ArrayList<String> notations() throws Exception{
        Class.forName("org.sqlite.JDBC");
        ArrayList<String> nots = new ArrayList();
        
        try(Connection connection = DriverManager.getConnection(this.dbloc)){
            String query = "Select * FROM mathformulas";
            ResultSet rs = connection.createStatement().executeQuery(query);
            
            while(rs.next()){
                nots.add(rs.getString("name"));
            }
        }
        
        return nots;
    }
    
}
