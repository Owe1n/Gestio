package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class LoginController extends MainController {

	
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
			parent.launchAppAdmin("admin1");
			
		}else if(mdpInput.getText().equals("user") && emailInput.getText().equals("user")) {
			System.out.println("Connecté user");
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
