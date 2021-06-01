package gestio;

import java.util.ArrayList;
import java.util.HashMap;

public class Demande {

	private Utilisateur user;
	private HashMap<Materiel, Integer> materiels; 
	private int typeDemande;
	
	public Demande(Utilisateur user, int typeDemande){
		this.user = user;
		this.typeDemande = typeDemande;
		this.materiels = new HashMap<Materiel, Integer>();
	}
	public Demande() {};

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public HashMap<Materiel, Integer> getMateriels() {
		return materiels;
	}

	public void setMateriels(HashMap<Materiel, Integer> materiels) {
		this.materiels = materiels;
	}
	
	public void addMateriel(Materiel mat, Integer quantite) {
		materiels.put(mat, quantite);
	}

	public int getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(int typeDemande) {
		this.typeDemande = typeDemande;
	}
	
}
