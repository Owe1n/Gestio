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

public class MaterielAdminCardController implements Initializable {

	private Materiel materielModel;
	private MainController parent;
	private MaterielModel matModel = new MaterielModel("Gestio");
	
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
		parent.popup("Materiel supprime", "Attention vous avez suprime un materiel", 1);
		matModel.deleteMateriel(materielModel);
		parent.pageMateriel();
	}
	
	@FXML
	private void handleEditMateriel(MouseEvent e){
		parent.disableAddButton();

		parent.editMateriel(materielModel);
	}
	
	@FXML
	private void handleAddBonMateriel(MouseEvent e){
		matModel.addBonMateriel(materielModel);
		materielModel.setNbBon(materielModel.getNbBon()+1);
		labelBon.setText(String.valueOf(++counterBon));
		
	}
	
	@FXML
	private void handleAddMoyenMateriel(MouseEvent e){
		labelMoyen.setText(String.valueOf(++counterMoyen));
		matModel.addMoyenMateriel(materielModel);
		materielModel.setNbMoyen(materielModel.getNbMoyen()+1);
	}
	
	@FXML
	private void handleAddMauvaisMateriel(MouseEvent e){
		labelMauvais.setText(String.valueOf(++counterMauvais));
		matModel.addMauvaisMateriel(materielModel);
		materielModel.setNbMauvais(materielModel.getNbMauvais()+1);
	}
	
	@FXML
	private void handleRemoveBonMateriel(MouseEvent e){
		labelBon.setText(String.valueOf(--counterBon));
		
		if(counterBon < 0) {
			counterBon = 0;
			labelBon.setText(String.valueOf(counterBon));
		}
		matModel.removeBonMateriel(materielModel);
		materielModel.setNbBon(counterBon);
	}
	
	@FXML
	private void handleRemoveMoyenMateriel(MouseEvent e){
		labelMoyen.setText(String.valueOf(--counterMoyen));
		
		if(counterMoyen < 0) {
			counterMoyen = 0;
			labelMoyen.setText(String.valueOf(counterMoyen));
		}
		matModel.removeMoyenMateriel(materielModel);
		materielModel.setNbMoyen(counterMoyen);
	}
	
	@FXML
	private void handleRemoveMauvaisMateriel(MouseEvent e){
		labelMauvais.setText(String.valueOf(--counterMauvais));
		
		if(counterMauvais < 0) {
			counterMauvais = 0;
			labelMauvais.setText(String.valueOf(counterMauvais));
		}
		matModel.removeMauvaisMateriel(materielModel);
		materielModel.setNbMauvais(counterMauvais);
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