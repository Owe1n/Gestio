package controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import model.UtilisateursModel;

public class AddUtilisateurController extends MainController {

	private Utilisateur utilisateurModel;
	private MainController parent;

	AddUtilisateurController(Utilisateur utilisateurModel) {
		this.utilisateurModel = utilisateurModel;
	}

	AddUtilisateurController() {
		Utilisateur nullUser = new Utilisateur();
		this.utilisateurModel = nullUser;
	}

	@FXML
	private TextField textFieldId;

	@FXML
	private TextField textFieldPrenom;

	@FXML
	private TextField textFieldNom;

	@FXML
	private TextField textFieldEmail;

	@FXML
	private TextField textFieldMdp;

	@FXML
	private TextField textFieldUserImgPath;

	@FXML
	private CheckBox isAdmin;

	@FXML
	private Button validerBtn;

	@FXML
	public void handleAnnulerAjout(MouseEvent e) {
		System.out.println("Annuler");
		parent.pageUtilisateurs();
	}

	@FXML
	public void handleClickValider(ActionEvent e) {
		UtilisateursModel modelUser = new UtilisateursModel("Gestio");
		if (textFieldId.getText() == "") {
			textFieldId.setText("-1");
		}
		File tempFile = new File(System.getProperty("user.dir") + "\\src" + textFieldUserImgPath.getText());
		System.out.print(tempFile);
		boolean exists = tempFile.exists();

		if (textFieldPrenom.getText() == null) {
			parent.popup("Erreur Prenom", "Il faut definir un prenom a votre utilisateur.", 2);
			return;
		}

		if (textFieldNom.getText() == null) {
			parent.popup("Erreur Nom", "Il faut definir un nom a votre utilisateur.", 2);
			return;
		}

		if (textFieldEmail.getText() == null) {
			parent.popup("Erreur Email", "Il faut definir un email a votre utilisateur.", 2);
			return;
		}

		if (textFieldMdp.getText() == null) {
			parent.popup("Erreur mot de passe", "Il faut definir un mot de passe a votre utilisateur.", 2);
			return;
		}

		if (!exists || textFieldUserImgPath.getText() == "") {
			parent.popup("Erreur image", "Le chemins de l'image n'existe pas.", 2);
			return;
		}

		if (modelUser.getUtilisateurById(Integer.parseInt(textFieldId.getText())).getFirstName() != null) {
			parent.popup("Utilisateur modifie", "L'utilisateur a ete modifie avec succes !", 0);
			int authority = 0;
			if (isAdmin.isSelected() == true) {
				authority = 1;
			}
			;
			Utilisateur user = new Utilisateur(Integer.parseInt(textFieldId.getText()), textFieldPrenom.getText(),
					textFieldNom.getText(), textFieldEmail.getText(), textFieldMdp.getText(),
					textFieldUserImgPath.getText(), authority);
			modelUser.editUtilisateur(user);
		} else {
			parent.popup("Utilisateur ajoute", "L'utilisateur a ete ajoute avec succes !", 0);
			int authority = 0;
			if (isAdmin.isSelected() == true) {
				authority = 1;
			}
			;
			Utilisateur user = new Utilisateur(Integer.parseInt(textFieldId.getText()), textFieldPrenom.getText(),
					textFieldNom.getText(), textFieldEmail.getText(), textFieldMdp.getText(),
					textFieldUserImgPath.getText(), authority);
			modelUser.addUtilisateur(user);
		}
		parent.pageUtilisateurs();

	}

	public void addUserInformation(String Prenom, String nom, String email, String mdp, String userImgPath) {

		textFieldPrenom.setText(Prenom);
		textFieldNom.setText(nom);
		textFieldEmail.setText(email);
		textFieldMdp.setText(mdp);
		textFieldUserImgPath.setText(userImgPath);
	}

	public AnchorPane build() {
		AnchorPane card = null;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/AddUtilisateur.fxml"));
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
		// TODO Auto-generated method stub

		if (this.utilisateurModel.getId() != -1) {
			textFieldId.setText(String.valueOf(this.utilisateurModel.getId()));
		}
		textFieldPrenom.setText(this.utilisateurModel.getFirstName());
		textFieldNom.setText(this.utilisateurModel.getLastName());
		textFieldEmail.setText(this.utilisateurModel.getEmail());
		textFieldMdp.setText(this.utilisateurModel.getPassword());
		textFieldUserImgPath.setText(this.utilisateurModel.getImageUserPath());

		if (this.utilisateurModel.getAutorithyLevel() == 1) {
			isAdmin.setSelected(true);
		}

	}

	public void setParent(MainController parent) {
		this.parent = parent;
	}

}
