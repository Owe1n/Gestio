package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController implements Initializable {

	
	@FXML
	private TextField emailInput;
	
	@FXML
	private TextField mdpInput;
	
	private MainController parent;
	
	public void setParent(MainController parent) {
		this.parent = parent;
	}
	
	@FXML
	protected void handleClickLogin(ActionEvent e) {
		
		if(mdpInput.getText().equals("admin") && emailInput.getText().equals("admin")) {
			System.out.println("Connecté Admin !!");
			Utilisateur monUtilisateur = new Utilisateur("firstName", "lastName", "email@email.com", "12345password", "/img/User2.png", 1);
			parent.launchAppAdmin(monUtilisateur);
			
		}else if(mdpInput.getText().equals("user") && emailInput.getText().equals("user")) {
			System.out.println("Connecté user");
			Utilisateur monUtilisateur = new Utilisateur("Owein", "GGGGourneau", "email@email.com", "12345password", "/img/User1.png", 1);
			parent.launchAppUser(monUtilisateur);
		}else {
			System.out.println("Erreur");
		}
		
	}
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/Login.fxml"
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
		System.out.println("curent page : Login page ");
		
	}
}
