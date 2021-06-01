package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gestio.Demande;
import gestio.Materiel;
import gestio.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import model.DemandesModel;
public class MainController implements Initializable {
	
	
	@FXML
	private BorderPane bp;
	@FXML
	private FlowPane fp;

	
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
		
			fp.getChildren().clear();
		
			
			DemandesModel demandeModel = new DemandesModel("Gestio");			
			for(Demande d: demandeModel.demandes) {
				fp.getChildren().add(new DemandeCardController(d).build());
			}
		
			
			
			
		
		
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
		fp.getChildren().clear();
		
		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page+".fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		fp.getChildren().add(root);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		
	}

}
