package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Utilisateur;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.UtilisateursModel;

public class UtilisateurCardController implements Initializable {

	private Utilisateur utilisateurModel;
	
	UtilisateurCardController(Utilisateur utilisateurModel){
		this.utilisateurModel = utilisateurModel;
	}
	
	private MainController parent;
	
	public void setParent(MainController parent) {
		this.parent = parent;
	}
	
	@FXML
	private Label nameUserCard;
	
	@FXML
	private Label emailUserCard;
	
	@FXML
	private Label passwordUserCard;
	
	@FXML
	private ImageView imageUserCard;
	
	@FXML
	public void handleClickDelete(MouseEvent e) {
		System.out.println("Profil Suprim�");
		UtilisateursModel userModel = new UtilisateursModel("Gestio");
		userModel.deleteUtilisateur(utilisateurModel);
		
	}
	
	@FXML
	public void handleClickEdit(MouseEvent e) {
		parent.editUtilisateur(utilisateurModel);
	}
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/Utilisateurs.fxml"
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
		nameUserCard.setText(this.utilisateurModel.getFullName());
		emailUserCard.setText(this.utilisateurModel.getEmail());
		passwordUserCard.setText(this.utilisateurModel.getPassword());
		
		Image userImg = new Image(getClass().getResourceAsStream(this.utilisateurModel.getImageUserPath()));
		
		imageUserCard.setImage(userImg);
		
		
	}

}
