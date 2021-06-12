package tools;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Connect {
     /**
     * Connect to a sample database
     */
	protected String url;
	
	public Connection conn;
	
	public Connect(String name_of_db){
		conn = null;
        String localDir = System.getProperty("user.dir");
       
        this.url = "jdbc:sqlite:" + localDir + "\\src\\db\\"+ name_of_db +".db";
        try {
            conn = DriverManager.getConnection(this.url);  
            System.out.println("Connection to SQLite has been established.");
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
	}
    public void selectAll(){
        String sql = "SELECT * FROM utilisateurs";
        
        try (Connection conn = this.conn;
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
           
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
 
    public  void createNewTable(String name, ArrayList<String> fields,  ArrayList<String> types, ArrayList<String> specs) {
               
        // SQL statement for creating a new table
      
        String sql = "CREATE TABLE IF NOT EXISTS " + name +" (\n";
        for (int i = 0; i < fields.size(); i++) {
            String field = fields.get(i);
            String type = types.get(i);
            String spec = specs.get(i);
            
            sql += " " + field +" " + type + " " + spec;
            
            if( i < fields.size() - 1 ) {
            	sql += ",\n";
            }
          }
        sql += ");";
        System.out.println(sql);
        try (
                Statement stmt = this.conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Insert a new row into the warehouses table
     *
     * @param name
     * @param capacity
     */
    public void insert(String name, String values) {

        
        
      
        String sql = "INSERT INTO " + name + " VALUES("+values+")";
       
        
    
        System.out.println(sql);

        try (
                Statement pstmt = this.conn.createStatement()) {
            
            pstmt.executeUpdate(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    
    

    

}