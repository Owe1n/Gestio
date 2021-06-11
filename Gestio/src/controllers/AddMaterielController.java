package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Materiel;
import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.MaterielModel;
import model.UtilisateursModel;


public class AddMaterielController extends MainController {
	
	private Materiel materielModel;
	
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
		System.out.println("Annuler");
	}
		
	@FXML
	public void handleClickValider(ActionEvent e) {
		MaterielModel matModel = new MaterielModel("Gestio");
		if(matModel.getMaterielById(Integer.parseInt(textFieldId.getText())) != null ) {
			System.out.println("Materiel exist");
			Materiel materiel = new Materiel(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldMaterielImgPath.getText(),Integer.parseInt(textFieldBon.getText()),Integer.parseInt(textFieldMoyen.getText()),Integer.parseInt(textFieldMauvais.getText()));
			matModel.editMateriel(materiel);
		}else {
			System.out.println("Materiel doas not exist");
			Materiel materiel = new Materiel(Integer.parseInt(textFieldId.getText()),textFieldName.getText(),textFieldMaterielImgPath.getText(),Integer.parseInt(textFieldBon.getText()),Integer.parseInt(textFieldMoyen.getText()),Integer.parseInt(textFieldMauvais.getText()));
			matModel.addMateriel(materiel);
		}
		
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
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		textFieldId.setText(String.valueOf(this.materielModel.getId()));
		textFieldName.setText(this.materielModel.getName());
		textFieldBon.setText(String.valueOf(this.materielModel.getNbBon()));
		textFieldMoyen.setText(String.valueOf(this.materielModel.getNbMoyen()));
		textFieldMauvais.setText(String.valueOf(this.materielModel.getNbMauvais()));
		textFieldMaterielImgPath.setText(this.materielModel.getImagePath());
		
	}
	
	
	
	
}
