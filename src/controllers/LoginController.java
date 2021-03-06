package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import model.UtilisateursModel;

public class LoginController implements Initializable {

	@FXML
	private TextField emailInput;

	@FXML
	private TextField mdpInput;

	private MainController parent;

	public void setParent(MainController parent) {
		this.parent = parent;
	}

	@FXML
	protected void handleClickLogin(ActionEvent e) {
		UtilisateursModel userMod = new UtilisateursModel("Gestio");

		Utilisateur user = userMod.getUtilisateurByEmail(emailInput.getText());

		if (mdpInput.getText().equals(user.getPassword()) && emailInput.getText().equals(user.getEmail())
				&& user.getAutorithyLevel() == 1) {
			parent.popup("Connexion valide",
					"Bonjour " + user.getFullName() + ", vous etes maintenant connect� � Gestion !", 0);
			parent.launchAppAdmin(user);

		} else if (mdpInput.getText().equals(user.getPassword()) && emailInput.getText().equals(user.getEmail())
				&& user.getAutorithyLevel() == 0) {
			parent.popup("Connexion valide",
					"Bonjour " + user.getFullName() + ", vous etes maintenant connect� � Gestion !", 0);
			parent.launchAppUser(user);
		} else {
			System.out.println("Erreur");
			parent.popup("Erreur de connexion", "Identifiant ou mot de passe incorrecte", 2);
		}

	}

	public AnchorPane build() {
		AnchorPane card = null;

		FXMLLoader loader = new FXMLLoader(getClass().getResource("/vues/Login.fxml"));
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
		System.out.println("curent page : Login page ");

	}
}
