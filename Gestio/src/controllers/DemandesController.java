package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class DemandesController extends MainController {
	

	@FXML
	protected void valideBtnClick(ActionEvent e) {
		System.out.println(((Control)e.getSource()).getId());
		System.out.println("Validé");
		
	}
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("curent page : Demandes ");
		
	}
	
	
	
	
}
