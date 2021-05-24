package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController extends MainController {
	
	@FXML
	protected void loginBtnClick(ActionEvent e) {
		System.out.println("Clicked on the Login ! ");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("curent page : Login page ");
		
	}
	
}
