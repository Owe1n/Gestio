package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


public class AddUtilisateurController extends MainController {
	
	private Utilisateur utilisateurModel;
	
	AddUtilisateurController(Utilisateur utilisateurModel){
		this.utilisateurModel = utilisateurModel;
	}
	
	AddUtilisateurController(){
		Utilisateur nullUser = new Utilisateur();
		this.utilisateurModel = nullUser;
	}
	
	@FXML
	private TextField textFieldPrenom;
	
	@FXML
	private TextField textFieldNom;
	
	@FXML
	private TextField textFieldEmail;
	
	@FXML
	private TextField textFieldMdp;
	
	@FXML
	private TextField textFieldUserImgPath;

	@FXML
	private CheckBox isAdmin;
	
	@FXML
	private Button validerBtn;
		
	@FXML
	public void handleClickValider(ActionEvent e) {
		System.out.println("Valide");
		
	}
	
	public void addUserInformation(String Prenom, String nom, String email, String mdp, String userImgPath) {
		textFieldPrenom.setText(Prenom);
		textFieldNom.setText(nom);
		textFieldEmail.setText(email);
		textFieldMdp.setText(mdp);
		textFieldUserImgPath.setText(userImgPath);
	}
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/AddUtilisateur.fxml"
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
		
		textFieldPrenom.setText(this.utilisateurModel.getFirstName());
		textFieldNom.setText(this.utilisateurModel.getLastName());
		textFieldEmail.setText(this.utilisateurModel.getEmail());
		textFieldMdp.setText(this.utilisateurModel.getPassword());
		textFieldUserImgPath.setText(this.utilisateurModel.getImageUserPath());
		
		
		if(this.utilisateurModel.getAutorithyLevel() == 1) {
			isAdmin.setSelected(true);
		}

		
	}
	
	
	
	
}
