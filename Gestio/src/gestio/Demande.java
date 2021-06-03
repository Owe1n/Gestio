package gestio;

import java.util.ArrayList;
import java.util.HashMap;

public class Demande {

	private Utilisateur user;
	private HashMap<Materiel, ArrayList<Integer>> materiels; 
	private int typeDemande;
	
	public Demande(Utilisateur user, int typeDemande){
		this.user = user;
		this.typeDemande = typeDemande;
		this.materiels = new HashMap<Materiel, ArrayList<Integer>>();
	}
	
	public Demande() {}

	public Utilisateur getUser() {
		return user;
	}

	public void setUser(Utilisateur user) {
		this.user = user;
	}

	public HashMap<Materiel, ArrayList<Integer>> getMateriels() {
		return materiels;
	}

	public void setMateriels(HashMap<Materiel, ArrayList<Integer>> materiels) {
		this.materiels = materiels;
	}
	
	public void addMateriel(Materiel mat, Integer quantiteBon, Integer quantiteMoyen, Integer quantiteMauvais) {
		ArrayList<Integer> matQualite = new ArrayList<Integer>();
		matQualite.add(quantiteBon);
		matQualite.add(quantiteMoyen);
		matQualite.add(quantiteMauvais);
		//System.out.println(matQualite.toString());
		materiels.put(mat, matQualite);
	}

	public int getTypeDemande() {
		return typeDemande;
	}

	public void setTypeDemande(int typeDemande) {
		this.typeDemande = typeDemande;
	}
	
}
