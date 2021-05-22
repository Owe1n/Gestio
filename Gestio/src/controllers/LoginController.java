package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class LoginController extends MainController {
	
	@FXML
	protected void handleClickBtn(ActionEvent e) {
		System.out.println("Clicked babe Login ! ");
	}
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Hello babe Login ! ");
		
	}
}
