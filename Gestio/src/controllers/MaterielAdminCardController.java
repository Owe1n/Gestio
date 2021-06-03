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

public class MaterielAdminCardController implements Initializable {

	private Materiel materielModel;
	
	MaterielAdminCardController(Materiel materielModel){
		this.materielModel = materielModel;
	}
	
	@FXML
	private Rectangle imgContainer;
	
	@FXML
	private Label nameMat;
	
	@FXML
	private Label countBon;

	@FXML
	private Label countMoyen;

	@FXML
	private Label countMauvais;
	
	/*@FXML
	private ImageView materielImageView;*/
	
	@FXML
	private void handleDeleteMateriel(MouseEvent e){
		System.out.println("Delete");
	}
	
	@FXML
	private void handleEditMateriel(MouseEvent e){
		System.out.println("Edit");
	}
	
	@FXML
	private void handleAddBonMateriel(MouseEvent e){
		System.out.println("bon +");
	}
	
	@FXML
	private void handleAddMoyenMateriel(MouseEvent e){
		System.out.println("moyen +");
	}
	
	@FXML
	private void handleAddMauvaisMateriel(MouseEvent e){
		System.out.println("mauvais +");
	}
	
	@FXML
	private void handleRemoveBonMateriel(MouseEvent e){
		System.out.println("bon -");
	}
	
	@FXML
	private void handleRemoveMoyenMateriel(MouseEvent e){
		System.out.println("moyen -");
	}
	
	@FXML
	private void handleRemoveMauvaisMateriel(MouseEvent e){
		System.out.println("mauvais -");
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
		countBon.setText(String.valueOf(this.materielModel.getNbBon()));
		countMoyen.setText(String.valueOf(this.materielModel.getNbMoyen()));
		countMauvais.setText(String.valueOf(this.materielModel.getNbMauvais()));
		
		
		Image materielImg = new Image(getClass().getResourceAsStream(this.materielModel.getImagePath()));
		//materielImageView.setImage(materielImg);
		
		ImagePattern pattern = new ImagePattern(materielImg);
		imgContainer.setFill(pattern);
				
	}

}