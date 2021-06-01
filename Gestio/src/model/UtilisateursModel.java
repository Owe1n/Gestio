package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gestio.Utilisateur;

import tools.Connect;

public class UtilisateursModel extends Connect {

	
	public ArrayList<Utilisateur> utilisateurs;
	
	
	
	
	
	public UtilisateursModel(String name_of_db) {
		super(name_of_db);
		this.utilisateurs = getAllUtilisateur(); 
	}
	public ArrayList<Utilisateur> getAllUtilisateur(){
		String sql = "SELECT * FROM utilisateur";
		ArrayList<Utilisateur> utilisateursTemp = new ArrayList<Utilisateur>();
        try (Connection conn = DriverManager.getConnection(this.url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
               
                Utilisateur user = new Utilisateur(rs.getString("firstname"), rs.getString("lastname"), rs.getString("email"), rs.getString("password"), rs.getString("imgSrc"), rs.getInt("autorithyLevel"));
                utilisateursTemp.add(user);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
       
        
        return utilisateursTemp;
	}
	public Utilisateur getUtilisateurById(int id) {
		
		String sql = "SELECT * FROM utilisateur WHERE id_utilisateur = "+ id +"";
		Utilisateur user = new Utilisateur();
		try (Connection conn =  DriverManager.getConnection(this.url);  
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	          
				user.setFirstName(rs.getString("firstname"));
				user.setLastName(rs.getString("lastname"));
				user.setEmail(rs.getString("email"));
				user.setPassword(rs.getString("password"));
				user.setImageUserPath(rs.getString("imgSrc"));
				user.setAutorithyLevel(rs.getInt("autorithyLevel"));
			
	        
	           
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		
		return user;
		
	}
	
	public void addUtilisateur(Utilisateur user){
		
	}

	public void deleteUtilisateur(Utilisateur user){
		
	}
	
	public void editUtilisateur(Utilisateur user){
		
	}
}
