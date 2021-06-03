package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import gestio.Materiel;
import gestio.Utilisateur;
import tools.Connect;



public class MaterielModel extends Connect  {

	
	ArrayList<Materiel> materiels;
	public MaterielModel(String name_of_db) {
		super(name_of_db);
		this.materiels = getAllMateriel();
	}

	public ArrayList<Materiel> getAllMateriel(){
		String sql = "SELECT * FROM materiel";
		ArrayList<Materiel> materielTemp = new ArrayList<Materiel>();
        try (Connection conn = DriverManager.getConnection(this.url);
             Statement stmt  = conn.createStatement();
             ResultSet rs    = stmt.executeQuery(sql)){
            
            // loop through the result set
            while (rs.next()) {
           
                Materiel materiel = new Materiel(rs.getString("name"), rs.getString("imgSrc"), rs.getInt("nbBon"), rs.getInt("nbMoyen"), rs.getInt("nbMauvais"));
            
                materielTemp.add(materiel);
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            }
        return materielTemp;
        }
	public Materiel getMaterielById(int id) {
		
		String sql = "SELECT * FROM materiel WHERE id_materiel = "+ id + "";
		Materiel materiel = new Materiel();
		try (Connection conn =  DriverManager.getConnection(this.url);  
	             Statement stmt  = conn.createStatement();
	             ResultSet rs    = stmt.executeQuery(sql)){
	          
				materiel.setName(rs.getString("name"));
				materiel.setImagePath(rs.getString("imgSrc"));
				materiel.setNbBon(rs.getInt("nbBon"));
				materiel.setNbBon(rs.getInt("nbMoyen"));
				materiel.setNbBon(rs.getInt("nbMauvais"));
				
				
	        
	           
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
		
		
		return materiel;
		
	}
	
	public void addMateriel(Materiel mat){
		
	}

	public void deleteMateriel(Materiel mat){
		
	}
	
	public void editMateriel(Materiel mat){
		
	}
	
	
	public void addBonMateriel(Materiel mat){
		
	}
	
	public void addMoyenMateriel(Materiel mat){
		
	}
	
	public void addMauvaisMateriel(Materiel mat){
		
	}
	
	public void removeBonMateriel(Materiel mat){
		
	}
	
	public void removeMoyenMateriel(Materiel mat){
		
	}
	
	public void removeMauvaisMateriel(Materiel mat){
		
	}
	
	
}
