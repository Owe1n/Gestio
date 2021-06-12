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
import model.DemandesModel;

public class MaterielUserCardController implements Initializable {

	private Materiel materielModel;
	private MainController parent;
	
	MaterielUserCardController(Materiel materielModel){
		this.materielModel = materielModel;
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
	
	@FXML
	private Label labelQuantite;
	
	private Integer counterQuantite;
	
	@FXML
	private void handleValiderDemande(MouseEvent e){
		System.out.println("Commande Valide");
		String quantity = labelQuantite.getText();
		DemandesModel demMod = new DemandesModel("Gestio");
		System.out.println(parent.user.getId());
		demMod.addDemand(parent.user, materielModel, Integer.parseInt(quantity));
		parent.launchAppUser(parent.user);
		parent.popup("Demande effectue", "La demmande a ete effectue avec succes !", 0);
	}
	
	@FXML
	private void handleAddQuantite(MouseEvent e){
		
		labelQuantite.setText(String.valueOf(++counterQuantite));
		
		if(counterQuantite > this.materielModel.getTotalItem()){
			counterQuantite = this.materielModel.getTotalItem();
			labelQuantite.setText(String.valueOf(counterQuantite));
		}
	
	}
	
	@FXML
	private void handleRemoveQuantite(MouseEvent e){
		labelQuantite.setText(String.valueOf(--counterQuantite));
		
		if(counterQuantite < 0) {
			counterQuantite = 0;
			labelQuantite.setText(String.valueOf(counterQuantite));
		}
		
	}
	

	
	public AnchorPane build() {
		AnchorPane card = null;		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/MaterielUtilisateur.fxml"
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
		labelQuantite.setText("0");
		
		
		Image materielImg = new Image(getClass().getResourceAsStream(this.materielModel.getImagePath()));
		//materielImageView.setImage(materielImg);
		
		ImagePattern pattern = new ImagePattern(materielImg);
		imgContainer.setFill(pattern);
				
		
		counterQuantite = 0;
				
	}

	public void setParent(MainController parent) {
		this.parent = parent;
	}

}