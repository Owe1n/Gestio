package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;

import gestio.Materiel;
import gestio.Utilisateur;
import tools.Connect;
import gestio.Demande;
import model.UtilisateursModel;
import model.MaterielModel;
public class DemandesModel extends Connect {

	
	public ArrayList<Demande> demandes;
	
	
	public DemandesModel(String name_of_db) {
		super(name_of_db);
		// TODO Auto-generated constructor stub
		this.demandes = getAllDemands(0);
	}
	
	public void getDemandsByUser(Utilisateur user,int typeDemande){
		
	}
	
	
	public ArrayList<Demande>  getAllDemands(int typeDemande){
		UtilisateursModel userModel = new UtilisateursModel("Gestio");
		MaterielModel materielModel = new MaterielModel("Gestio");
		
		ArrayList<Demande> demandes = new ArrayList<Demande>();
		
		//RECUPERER TOUS LES IDS
		ArrayList<Integer> ids = new ArrayList<Integer>();
		String sql = "SELECT distinct d.id_utilisateur  \r\n"
				+ "FROM demande d";
		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt  = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql))
		{	
			while (rs.next()) 
			{
				ids.add(rs.getInt("id_utilisateur"));
			}
			
		} catch (SQLException e) 
		{
			System.out.println(e.getMessage());
		}
		
		
		
		//CREER LES DEMANDES
		for (Integer i : ids) {
			Demande demande = new Demande();
			demande.setUser(userModel.getUtilisateurById(i));
			HashMap<Materiel, Integer> materiels = new HashMap<Materiel, Integer>();
			String sql2 = "SELECT * \r\n"
    				+ "FROM demande d\r\n"
    				+ "	INNER JOIN utilisateur u ON ( d.id_utilisateur = u.id_utilisateur  )  \r\n"
    				+ "	INNER JOIN materiel m ON ( d.id_materiel = m.id_materiel  )  \r\n"
    				+ "WHERE d.id_utilisateur = "+ i ;
    	    
    		try (Connection conn = DriverManager.getConnection(this.url); Statement stmt2  = conn.createStatement(); ResultSet rs2= stmt2.executeQuery(sql2)){
    			while (rs2.next()) 
    			{	
    				
    				int id_materiel = rs2.getInt("id_materiel");
    				int quantity_materiel =  rs2.getInt("quantity_bon");
    				
    				Materiel materiel = materielModel.getMaterielById(id_materiel);
    				materiels.put(materiel, quantity_materiel);
    			}
    	    }catch (SQLException e) {
                System.out.println(e.getMessage());
            }
    		demande.setMateriels(materiels);
    		demandes.add(demande);
		}
		
		return demandes;
		
		
	}
	
	public void addDemand(Utilisateur user, ArrayList<Materiel> materiels){
	
	}


	public void validerDemande(Demande dem) {}
	public void refuserDemande(Demande dem) {}
	
	
}
