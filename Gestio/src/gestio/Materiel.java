package gestio;

public class Materiel {

	private String name;
	private String imagePath;
	private int nbBon;
	private int nbMoyen;
	private int nbMauvais;
	
	public Materiel(String name, String imagePath, int nbBon, int nbMoyen, int nbMauvais){
		this.setName(name);
		this.setImagePath(imagePath);
		this.setNbBon(nbBon);
		this.setNbMoyen(nbMoyen);
		this.setNbMauvais(nbMauvais);
	 }
	public Materiel() {};
	
	public int getTotalItem() {
		return getNbBon()+getNbMoyen()+getNbMauvais();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public int getNbBon() {
		return nbBon;
	}

	public void setNbBon(int nbBon) {
		this.nbBon = nbBon;
	}

	public int getNbMoyen() {
		return nbMoyen;
	}

	public void setNbMoyen(int nbMoyen) {
		this.nbMoyen = nbMoyen;
	}

	public int getNbMauvais() {
		return nbMauvais;
	}

	public void setNbMauvais(int nbMauvais) {
		this.nbMauvais = nbMauvais;
	}
	
	
	
	
	
}
