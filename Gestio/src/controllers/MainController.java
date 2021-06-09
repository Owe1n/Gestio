package controllers;


import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import gestio.Demande;
import gestio.Materiel;
import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
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
	private TextField emailInput;
	
	@FXML
	private Label userName;
	
	@FXML
	private ImageView userImgView;
	
	@FXML
	private HBox disconected;
	
	
	
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
	
	private void addUtilisateur() {
		fp.getChildren().clear();
		
		//AddUtilisateurController addUser = new AddUtilisateurController("Owein", "Gourneau", "o.g@gmail.com", "123","/img/user1.png");
		Utilisateur monUtilisateur = new Utilisateur("firstName", "lastName", "email@email.com", "12345password", "/img/User1.png", 1);
		AddUtilisateurController addUser = new AddUtilisateurController(monUtilisateur);
		//addUser = Si on doit modifier un user
		//addUser = Si on doit ajouter un tout nouveau user
		
		fp.getChildren().add(new AddUtilisateurController().build());
		
	}
	
	public void launchAppAdmin(String userName) {
		enableBtnNav();
		this.userName.setText(userName);
		
		resetStyleNavBtn();
		demandesBtn.getStyleClass().add("selectedBtn");
	
		fp.getChildren().clear();
	
		
		DemandesModel demandeModel = new DemandesModel("Gestio");			
		for(Demande d: demandeModel.demandes) {
			fp.getChildren().add(new DemandeCardController(d).build());
		}
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
	

	private void loadPageLogin(String page) {
		fp.getChildren().clear();
		
		Parent root = null;
		try {
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page+".fxml"));
			root = (Parent)fxmlLoader.load();
			LoginController loginController = fxmlLoader.<LoginController>getController();
			loginController.setParent(this);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		fp.getChildren().add(root);
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		disableBtnNav();
		//loadPageLogin("/vues/Login");

		addUtilisateur();

	}
	

	private void resetStyleNavBtn(){
		materielBtn.getStyleClass().remove("selectedBtn");
		utilisateurBtn.getStyleClass().remove("selectedBtn");
		demandesBtn.getStyleClass().remove("selectedBtn");
		//retourslBtn.getStyleClass().remove("selectedBtn");
	}
	
	private void disableBtnNav(){
		materielBtn.setDisable(true);
		utilisateurBtn.setDisable(true);
		demandesBtn.setDisable(true);
		disconected.setDisable(true);
		
		materielBtn.setVisible(false);
		utilisateurBtn.setVisible(false);
		demandesBtn.setVisible(false);
		disconected.setVisible(false);
	}
	
	private void enableBtnNav(){
		materielBtn.setDisable(false);
		utilisateurBtn.setDisable(false);
		demandesBtn.setDisable(false);
		disconected.setDisable(false);
		
		materielBtn.setVisible(true);
		utilisateurBtn.setVisible(true);
		demandesBtn.setVisible(true);
		disconected.setVisible(true);
	}
	
}
