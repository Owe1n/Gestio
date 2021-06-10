package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Materiel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import model.MaterielModel;
import model.UtilisateursModel;

public class MaterielAdminCardController implements Initializable {

	private Materiel materielModel;
	private MainController parent;
	
	MaterielAdminCardController(Materiel materielModel){
		this.materielModel = materielModel;
	}
	
	
	public void setParent(MainController parent) {
		this.parent = parent;
	}
	
	@FXML
	private Rectangle imgContainer;
	
	@FXML
	private Label nameMat;
	
	@FXML
	private Label labelBon;

	@FXML
	private Label labelMoyen;

	@FXML
	private Label labelMauvais;
	
	private Integer counterBon;
	private Integer counterMoyen;
	private Integer counterMauvais;
	
	/*@FXML
	private ImageView materielImageView;*/
	
	@FXML
	private void handleDeleteMateriel(MouseEvent e){
		System.out.println("Delete");
		MaterielModel matModel = new MaterielModel("Gestio");
		matModel.deleteMateriel(materielModel);
		//reload the page
	}
	
	@FXML
	private void handleEditMateriel(MouseEvent e){
		parent.editMateriel(materielModel);
	}
	
	@FXML
	private void handleAddBonMateriel(MouseEvent e){
		labelBon.setText(String.valueOf(++counterBon));
		//@TODO requete BDD
	}
	
	@FXML
	private void handleAddMoyenMateriel(MouseEvent e){
		labelMoyen.setText(String.valueOf(++counterMoyen));
		//@TODO requete BDD
	}
	
	@FXML
	private void handleAddMauvaisMateriel(MouseEvent e){
		labelMauvais.setText(String.valueOf(++counterMauvais));
		//@TODO requete BDD
	}
	
	@FXML
	private void handleRemoveBonMateriel(MouseEvent e){
		labelBon.setText(String.valueOf(--counterBon));
		
		if(counterBon < 0) {
			counterBon = 0;
		}
		//@TODO requete BDD
	}
	
	@FXML
	private void handleRemoveMoyenMateriel(MouseEvent e){
		labelMoyen.setText(String.valueOf(--counterMoyen));
		
		if(counterMoyen < 0) {
			counterMoyen = 0;
		}
		//@TODO requete BDD
	}
	
	@FXML
	private void handleRemoveMauvaisMateriel(MouseEvent e){
		labelMauvais.setText(String.valueOf(--counterMauvais));
		
		if(counterMauvais < 0) {
			counterMauvais = 0;
		}
		//@TODO requete BDD
	}
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/Materiel.fxml"
			    )
			);
		loader.setController(this);
		
		try {
			card = loader.load();
			return card;
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	return card;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		nameMat.setText(this.materielModel.getName());
		labelBon.setText(String.valueOf(this.materielModel.getNbBon()));
		labelMoyen.setText(String.valueOf(this.materielModel.getNbMoyen()));
		labelMauvais.setText(String.valueOf(this.materielModel.getNbMauvais()));
		
		
		Image materielImg = new Image(getClass().getResourceAsStream(this.materielModel.getImagePath()));
		//materielImageView.setImage(materielImg);
		
		ImagePattern pattern = new ImagePattern(materielImg);
		imgContainer.setFill(pattern);
				
		
		counterBon = this.materielModel.getNbBon();
		counterMoyen = this.materielModel.getNbMoyen();
		counterMauvais = this.materielModel.getNbMauvais();
	}

}