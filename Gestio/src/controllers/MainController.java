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
import javafx.scene.control.Button;
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
	private Button materielBtn;	
	
	@FXML
	private Button utilisateurBtn;
	
	@FXML
	private Button demandesBtn;

	
	@FXML
	private void pageMateriel(MouseEvent event) {
		resetStyleNavBtn();
		materielBtn.getStyleClass().add("selectedBtn");
		fp.getChildren().clear();
		Materiel matBdd1 = new Materiel("Souris", "/img/Souris.jpg", 21, 23, 7);
		Materiel matBdd2 = new Materiel("Clavier","/img/Clavier.jpg", 14, 15, 8);
		
		MaterielAdminCardController mat1 = new MaterielAdminCardController(matBdd1);
		MaterielAdminCardController mat2 = new MaterielAdminCardController(matBdd2);
		
		/*@TODO, changer ca en BDD*/
		
		fp.getChildren().add(mat1.build());
		fp.getChildren().add(mat2.build());
		//loadPage("/vues/Materiel");
		
	}
	
	
	@FXML
	private void pageUtilisateurs(MouseEvent event) {
		resetStyleNavBtn();
		utilisateurBtn.getStyleClass().add("selectedBtn");
		
		fp.getChildren().clear();
		
		/*@TODO, changer ca en BDD*/
		Utilisateur monUtilisateur = new Utilisateur("firstName", "lastName", "email@email.com", "12345password", "/img/User1.png", 1);
		Utilisateur monUtilisateur2 = new Utilisateur("aeaeazeazeaze", "aeaeaze", "email@eazeazeazeazemail.com", "12345password", "/img/User1.png", 1);
		
		UtilisateurCardController user1 = new UtilisateurCardController(monUtilisateur);
		UtilisateurCardController user2 = new UtilisateurCardController(monUtilisateur2);
		/*@TODO, changer ca en BDD*/
		
		fp.getChildren().add(user1.build());
		fp.getChildren().add(user2.build());
		//loadPage("/vues/Utilisateurs");
	}
	
	@FXML
	private void pageDemandes(MouseEvent event) {	
			resetStyleNavBtn();
			demandesBtn.getStyleClass().add("selectedBtn");
		
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
	private void resetStyleNavBtn(){
		materielBtn.getStyleClass().remove("selectedBtn");
		utilisateurBtn.getStyleClass().remove("selectedBtn");
		demandesBtn.getStyleClass().remove("selectedBtn");
		//retourslBtn.getStyleClass().remove("selectedBtn");
	}
}
