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
import javafx.scene.layout.AnchorPane;


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
	public void handleClickValider(ActionEvent e) {
		System.out.println("Valide");
		
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
		
		textFieldName.setText(this.materielModel.getName());
		textFieldBon.setText(String.valueOf(this.materielModel.getNbBon()));
		textFieldMoyen.setText(String.valueOf(this.materielModel.getNbMoyen()));
		textFieldMauvais.setText(String.valueOf(this.materielModel.getNbMauvais()));
		textFieldMaterielImgPath.setText(this.materielModel.getImagePath());
		
	}
	
	
	
	
}
