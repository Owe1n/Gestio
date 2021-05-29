package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
public class MainController implements Initializable {
	
	
	@FXML
	private BorderPane bp;
	@FXML
	private AnchorPane ap;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		System.out.println("Hello babe ! ");
		
	}
	
	@FXML
	private void pageMateriel(MouseEvent event) {
		loadPage("/vues/Materiel");
	}
	
	@FXML
	private void pageUtilisateurs(MouseEvent event) {
		loadPage("/vues/Utilisateurs");
	}
	
	@FXML
	private void pageDemandes(MouseEvent event) {
		loadPage("/vues/Demandes");
	}
	
	@FXML
	private void pageRetours(MouseEvent event) {
		loadPage("/vues/Retours");
	}	
	
	@FXML
	private void pageLogin(MouseEvent event) {
		loadPage("/vues/Login");
	}
	
	private void loadPage(String page) {
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		bp.setCenter(root);
	}

}
