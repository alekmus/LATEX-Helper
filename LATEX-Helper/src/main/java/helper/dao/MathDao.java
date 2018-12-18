
package helper.dao;

import java.sql.*;
import java.util.*;

/**
 * Provides a connection to the project database and pulls saved
 * LaTEX-formulas from the mathformulas table.
 * @author Aleksi
 */
public class MathDao implements Dao {
    private String dbase;
    
    public MathDao(String dbasename) {
        this.dbase = "jdbc:sqlite::resource:" + dbasename;
    }
    
    /**
     * Returns a LaTEX-formula based on a descriptive name.
     * @param form name of a formula
     * @return LaTEX-formula corresponding to the parameter
     */
    @Override
    public String find(String form) {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (Exception e) {
            
        }
        String result = "";
        try (Connection connection = DriverManager
                    .getConnection(dbase)) {
            String query = "Select * FROM mathformulas WHERE name = ?";
            PreparedStatement stmt = connection.prepareStatement(query);
            stmt.setString(1, form);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                result += rs.getString("formula");
            } 
        } catch (Exception e) {
            System.out.println(e);
        }
        return result;
    }
    
    /**
     * Returns the notations used as the formulas' names in the database.
     * @return A list of names for different formulas in the database
     * @throws Exception 
     */
    public ArrayList<String> notations() throws Exception {
        Class.forName("org.sqlite.JDBC");
        ArrayList<String> nots = new ArrayList();
        
        try (Connection connection = DriverManager
                .getConnection(dbase)) {
            String query = "Select * FROM mathformulas ORDER BY name ASC";
            ResultSet rs = connection.createStatement().executeQuery(query);
            
            while (rs.next()) {
                nots.add(rs.getString("name"));
            }
        }
        
        return nots;
    }
    
}
