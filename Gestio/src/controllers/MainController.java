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
		
			/*@TODO, changer ca en BDD*/
			
			
			Materiel mat1 = new Materiel("souris", "/img/Sourris.jpg", 21, 23, 7);
			Materiel mat2 = new Materiel("Clavier","/img/Clavier.jpg", 14, 15, 8);
			
			
			Utilisateur monUtilisateur = new Utilisateur("firstName", "lastName", "email@email.com", "12345password", "/img/Ecran.jpg", 1);
			Utilisateur monUtilisateur2 = new Utilisateur("aeaeazeazeaze", "aeaeaze", "email@eazeazeazeazemail.com", "12345password", "/img/Clavier.jpg", 1);
			
			Demande monModelDemande = new Demande(monUtilisateur, 0);
			
			monModelDemande.addMateriel(mat1, 5);
			monModelDemande.addMateriel(mat2, 10);
			
			Demande monModelDemande2 = new Demande(monUtilisateur2, 0);
			monModelDemande2.addMateriel(mat1, 65);

			
			DemandeCardController first = new DemandeCardController(monModelDemande);
			DemandeCardController second = new DemandeCardController(monModelDemande2);
			/*@TODO, changer ca en BDD*/
		
			fp.getChildren().add(first.build());
			
			fp.getChildren().add(second.build());
			
			
		
		
		
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
