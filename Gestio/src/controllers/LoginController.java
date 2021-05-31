package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class LoginController extends MainController {
	
	@FXML
	private TextField emailInput;
	
	@FXML
	private TextField mdpInput;
	
	@FXML
	protected void handleClickLogin(ActionEvent e) {
		
		
		if(mdpInput.getText().equals("admin") && emailInput.getText().equals("admin")) {
			System.out.println("Connect� !!");
		}else {
			System.out.println("Erreur");
		}
		
	}
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("curent page : Login page ");
		
	}
}
