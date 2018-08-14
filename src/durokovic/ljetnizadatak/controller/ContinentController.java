package durokovic.ljetnizadatak.controller;

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

import javax.swing.JOptionPane;

import com.mysql.cj.jdbc.exceptions.CommunicationsException;

import durokovic.ljetnizadatak.model.Continent;

public class ContinentController {
private Connection conn;

    public ContinentController() throws Exception {
        try {
            Properties props = new Properties();
            props.load(new FileInputStream("src/durokovic/ljetnizadatak/properties/database.properties"));
            
            String user = props.getProperty("user");
            String password = props.getProperty("password");
            String db = props.getProperty("db");
            
            conn = DriverManager.getConnection(db, user, password);
            
        } catch (CommunicationsException e) {
            JOptionPane.showMessageDialog(null,"Error: Cannot connect to database. \n Closing app!", "MYSQL Connection Error", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
    
    public List<Continent> getAllContinents() throws Exception{
        List<Continent> continent = new ArrayList<>();
        Statement stmt = null;
        ResultSet rs = null;
        
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery("select * from continents");
            
            while (rs.next()){
                Continent tempContinent = convertRowsToContinent(rs);
                continent.add(tempContinent);
            }
            return continent;
        } finally {
           close(stmt, rs);
        }
    }
    
    public void addContinent(String name, String recordName, String latitude, String longitude, int zoom) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("insert into continents (name, recordName, latitude, longitude, zoom) values (?,?,?,?,?);");
            
            stmt.setString(1, name);
            stmt.setString(2, recordName);
            stmt.setString(3, latitude);
            stmt.setString(4, longitude);
            stmt.setInt(5, zoom);
            
            stmt.executeUpdate();
        } finally {
            close(stmt);
        }
    }
    
    public void updateContinent(int id, String name, String recordName, String latitude, String longitude, int zoom) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("update continents set name=?, recordName=?, latitude=?, longitude=?, zoom=? where id=?;");
            
            stmt.setString(1, name);
            stmt.setString(2, recordName);
            stmt.setString(3, latitude);
            stmt.setString(4, longitude);
            stmt.setInt(5, zoom);
            stmt.setInt(6, id);

            stmt.executeUpdate();
            
        }finally {
            close(stmt);
        }           
    }
    
    public void deleteContinent(int id) throws SQLException{
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement("delete from continents where id = ?;");
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
   
    private Continent convertRowsToContinent(ResultSet rs) throws SQLException{
        int id = rs.getInt("id");
        String name = rs.getString("name");
        String recordName = rs.getString("recordName");
        String latitude = rs.getString("latitude");
        String longitude = rs.getString("longitude");
        int zoom = rs.getInt("zoom");
        
        Continent tempContinent = new Continent(id, name, recordName, latitude, longitude, zoom);
        return tempContinent;
    }
}
