package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Materiel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MaterielModel;


public class AddMaterielController extends MainController {
	
	private Materiel materielModel;
	MainController parent;
	
	AddMaterielController(Materiel materielModel){
		this.materielModel = materielModel;
	}
	
	AddMaterielController(){
		Materiel nullMateriel = new Materiel();
		this.materielModel = nullMateriel;
	}
	
	@FXML
	private TextField textFieldId;
	
	@FXML
	private TextField textFieldName;
	
	@FXML
	private TextField textFieldBon;
	
	@FXML
	private TextField textFieldMoyen;
	
	@FXML
	private TextField textFieldMauvais;
	
	@FXML
	private TextField textFieldMaterielImgPath;
	
	@FXML
	public void handleAnnulerAjout(MouseEvent e) {
		parent.pageMateriel();
	}
		
	@FXML
	public void handleClickValider(ActionEvent e) {
		MaterielModel matModel = new MaterielModel("Gestio");
	
		if(matModel.getMaterielById(Integer.parseInt(textFieldId.getText())).getName() != null ) {
			parent.popup("Materiel modifie", "Le materiel a ete modifie avec succes !", 0);
			Materiel materiel = new Materiel(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldMaterielImgPath.getText(),Integer.parseInt(textFieldBon.getText()),Integer.parseInt(textFieldMoyen.getText()),Integer.parseInt(textFieldMauvais.getText()));
			matModel.editMateriel(materiel);
		}else {
			parent.popup("Materiel ajoute", "Le materiel a ete cree avec succes !", 0);
			Materiel materiel = new Materiel(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldMaterielImgPath.getText(),Integer.parseInt(textFieldBon.getText()),Integer.parseInt(textFieldMoyen.getText()),Integer.parseInt(textFieldMauvais.getText()));
			matModel.addMateriel(materiel);
		}
		parent.pageMateriel();
		
	}
	
	public void addMaterielInformation(String name, Integer bon, Integer moyen, Integer mauvais, String materielImgPath) {
		textFieldName.setText(name);
		textFieldBon.setText(String.valueOf(bon));
		textFieldMoyen.setText(String.valueOf(moyen));
		textFieldMauvais.setText(String.valueOf(mauvais));
		textFieldMaterielImgPath.setText(materielImgPath);
	}
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/AddMateriel.fxml"
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
	public void setParent(MainController parent) {
		this.parent = parent;
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		if(this.materielModel.getId() != -1) {
			textFieldId.setText(String.valueOf(this.materielModel.getId()));
			textFieldName.setText(this.materielModel.getName());
			textFieldBon.setText(String.valueOf(this.materielModel.getNbBon()));
			textFieldMoyen.setText(String.valueOf(this.materielModel.getNbMoyen()));
			textFieldMauvais.setText(String.valueOf(this.materielModel.getNbMauvais()));
			textFieldMaterielImgPath.setText(this.materielModel.getImagePath());
		}
		
	}
	
	
	
	
}
