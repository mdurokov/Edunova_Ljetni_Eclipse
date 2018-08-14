
package durokovic.ljetnizadatak.controller;

import durokovic.ljetnizadatak.model.RoundType;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class RoundTypeController {
    private Connection conn;
    
    public RoundTypeController() throws Exception {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/durokovic/ljetnizadatak/properties/database.properties"));

            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String db = props.getProperty("db");

            conn = DriverManager.getConnection(db, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public List<RoundType> getAllRoundTypes() throws Exception {
        List<RoundType> format = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from roundTypes;");
            
            while (rs.next()) {                
                RoundType tempRoundType = convertRowsToRountType(rs);
                format.add(tempRoundType);
            }
            return format;
        } finally {
            close(stmt, rs);
        }
    }
    
    public void addRoundType(int rank, String name, String cellName, int finale) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into roundTypes (rank, name, cellName, final) values (?,?,?,?);");
            
            stmt.setInt(1, rank);
            stmt.setString(2, name);
            stmt.setString(3, cellName);
            stmt.setInt(4, finale);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void updateRoundType(int id, int rank, String name, String cellName, int finale) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update roundTypes set rank = ?, name = ?, cellName = ?, final = ? where id = ?;");
            
            stmt.setInt(1, rank);
            stmt.setString(2, name);
            stmt.setString(3, cellName);
            stmt.setInt(4, finale);
            stmt.setInt(5, id);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void deleteRoundType(int id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from roundTypes where id = ?;");
            
            stmt.setInt(1, id);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    private static void close(Connection myConn, Statement myStmt, ResultSet myRs) throws SQLException {

        if (myRs != null) {
                myRs.close();
        }

        if (myStmt != null) {

        }

        if (myConn != null) {
                myConn.close();
        }
    }
    
    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);		
    }
    
    private void close(Statement myStmt) throws SQLException {
        close(null, myStmt, null);		
    }
    
    public RoundType convertRowsToRountType(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        int rank = rs.getInt("rank");
        String name = rs.getString("name");
        String cellName = rs.getString("cellName");
        int finale = rs.getInt("final");
        
        
        RoundType tempEvent = new RoundType(id, rank, name, cellName, finale);
        return tempEvent;
    }
}
