
package durokovic.ljetnizadatak.controller;

import durokovic.ljetnizadatak.model.Event;
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


public class EventController {

    private Connection conn;
    
    public EventController() throws Exception {
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
    
    public List<Event> getAllEvents() throws Exception{
        List<Event> event = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("Select * from events");
            
            while (rs.next()) {                
                Event tempEvent = convertRowsToEvent(rs);
                event.add(tempEvent);
            }
            return event;
        } finally {
            close(stmt,rs);
        }
    } 
    
    public void addEvent(String name, int rank, String format, String cellName) throws Exception{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into events (name, rank, format, cellName) values (?,?,?,?);");
            
            stmt.setString(1, name);
            stmt.setInt(2, rank);
            stmt.setString(3, format);
            stmt.setString(4, cellName);
            
            stmt.executeUpdate();
        }
        finally {
            close(stmt);
        }
    }
    
    public void updateEvent(int id, String name, int rank, String format, String cellName) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update events set name = ?, rank = ?, format = ?, cellName = ? where id = ?;");
            
            stmt.setString(1, name);
            stmt.setInt(2, rank);
            stmt.setString(3, format);
            stmt.setString(4, cellName);
            stmt.setInt(5, id);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void deleteEvent(int id) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from events where id = ?;");
            
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
    public Event convertRowsToEvent(ResultSet rs) throws SQLException {
        int id = rs.getInt("id");
        String name = rs.getString("name");
        int rank = rs.getInt("rank");
        String format = rs.getString("format");
        String cellName = rs.getString("cellName");
        
        Event tempEvent = new Event(id, name, rank, format, cellName);
        return tempEvent;
    }
}
