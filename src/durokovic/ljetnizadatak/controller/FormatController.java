
package durokovic.ljetnizadatak.controller;

import durokovic.ljetnizadatak.model.Format;
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

public class FormatController {
    private Connection conn;
    
    public FormatController() throws Exception {
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
    
    public List<Format> getAllFormats() throws Exception {
        List<Format> format = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from formats;");
            
            while (rs.next()) {                
                Format tempFormat = convertRowsToFormats(rs);
                format.add(tempFormat);
            }
            return format;
        } finally {
            close(stmt, rs);
        }
    }
    
    public void addFormat(String name) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into formats (name) values (?);");
            
            stmt.setString(1, name);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void updateFormat(int id, String name) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update formats set name = ? where id = ?;");
            
            stmt.setString(1, name);
            stmt.setInt(2, id);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void deleteFormat(int id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from formats where id = ?;");
            
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
    
    public Format convertRowsToFormats(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        
        Format tempEvent = new Format(id, name);
        return tempEvent;
    }

}
