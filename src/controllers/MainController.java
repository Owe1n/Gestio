package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gestio.Demande;
import gestio.Materiel;
import gestio.Utilisateur;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import model.DemandesModel;
import model.MaterielModel;
import model.UtilisateursModel;

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

	Utilisateur user;

	@FXML
	private Label userName;

	@FXML
	private ImageView userImgView;

	@FXML
	private HBox disconected;

	@FXML
	private Button btnAjout;

	@FXML
	private AnchorPane popupCard;

	@FXML
	private TextFlow popupCardTitle;

	@FXML
	private TextFlow popupCardTextFlow;

	@FXML
	private void handleClickAjout(MouseEvent e) {
		// Ajouter Utilisateur
		// Ajouter Materiel

		if (btnAjout.getText().equals("Ajouter Utilisateur")) {
			addUtilisateur();
			disableAddButton();
		} else if(btnAjout.getText().equals("Rafraichir")) {
			fp.getChildren().clear();

			MaterielModel materielModel = new MaterielModel("Gestio");
			for (Materiel m : materielModel.materiels) {
				MaterielUserCardController materielCardController = new MaterielUserCardController(m);
				materielCardController.setParent(this);
				fp.getChildren().add(materielCardController.build());
			}
		} else {
			addMateriel();
			disableAddButton();
		}

	}

	@FXML
	private void handleDisconected(MouseEvent e) {
		disableBtnNav();
		popup("Deconnexion", "Vous vous etes bien deconnecte.", 0);
		loadPageLogin("/vues/Login");
	}

	@FXML
	void pageMateriel() {
		resetStyleNavBtn();
		materielBtn.getStyleClass().add("selectedBtn");
		fp.getChildren().clear();

		MaterielModel materielModel = new MaterielModel("Gestio");
		for (Materiel m : materielModel.materiels) {
			MaterielAdminCardController materielCardController = new MaterielAdminCardController(m);
			materielCardController.setParent(this);
			fp.getChildren().add(materielCardController.build());
		}
		btnAjout.setText("Ajouter Materiel");
		enableAddButton();

	}

	@FXML
	void pageUtilisateurs() {
		resetStyleNavBtn();
		utilisateurBtn.getStyleClass().add("selectedBtn");

		fp.getChildren().clear();

		UtilisateursModel utilisateursModel = new UtilisateursModel("Gestio");
		for (Utilisateur u : utilisateursModel.utilisateurs) {
			UtilisateurCardController userCardController = new UtilisateurCardController(u);
			userCardController.setParent(this);
			fp.getChildren().add(userCardController.build());

		}
		btnAjout.setText("Ajouter Utilisateur");
		enableAddButton();

		// loadPage("/vues/Utilisateurs");
	}

	@FXML
	void pageDemandes() {
		resetStyleNavBtn();
		demandesBtn.getStyleClass().add("selectedBtn");

		fp.getChildren().clear();

		DemandesModel demandeModel = new DemandesModel("Gestio");
		for (Demande d : demandeModel.demandes) {
			DemandeCardController ctrl = new DemandeCardController(d);
			ctrl.setParent(this);
			fp.getChildren().add(ctrl.build());
		}

		disableAddButton();

	}

	@FXML
	private void pageRetours(MouseEvent event) {
		loadPage("/vues/Retours");
	}

	@FXML
	private void pageLogin(MouseEvent event) {
		loadPage("/vues/Login");
	}

	public void addUtilisateur() {
		fp.getChildren().clear();
		AddUtilisateurController ctrll = new AddUtilisateurController();
		ctrll.setParent(this);
		fp.getChildren().add(ctrll.build());

	}

	public void editUtilisateur(Utilisateur user) {
		fp.getChildren().clear();

		AddUtilisateurController editUser = new AddUtilisateurController(user);
		editUser.setParent(this);
		fp.getChildren().add(editUser.build());

	}

	public void addMateriel() {
		fp.getChildren().clear();
		AddMaterielController ctrll = new AddMaterielController();
		ctrll.setParent(this);
		fp.getChildren().add(ctrll.build());

	}

	public void editMateriel(Materiel mat) {
		fp.getChildren().clear();
		AddMaterielController ctrll = new AddMaterielController(mat);
		ctrll.setParent(this);
		fp.getChildren().add(ctrll.build());

	}

	public void launchAppUser(Utilisateur user) {
		eneableDisconected();
		this.user = user;
		this.userName.setText(user.getFullName());
		Image userImg = new Image(getClass().getResourceAsStream(user.getImageUserPath()));
		this.userImgView.setImage(userImg);

		fp.getChildren().clear();

		MaterielModel materielModel = new MaterielModel("Gestio");
		for (Materiel m : materielModel.materiels) {
			MaterielUserCardController materielCardController = new MaterielUserCardController(m);
			materielCardController.setParent(this);
			fp.getChildren().add(materielCardController.build());
		}
		
		btnAjout.setText("Rafraichir");
		enableAddButton();

	}

	public void launchAppAdmin(Utilisateur user) {
		enableBtnNav();

		this.userName.setText(user.getFullName());
		Image userImg = new Image(getClass().getResourceAsStream(user.getImageUserPath()));
		this.userImgView.setImage(userImg);

		resetStyleNavBtn();
		demandesBtn.getStyleClass().add("selectedBtn");

		fp.getChildren().clear();

		DemandesModel demandeModel = new DemandesModel("Gestio");
		for (Demande d : demandeModel.demandes) {
			DemandeCardController ctrl = new DemandeCardController(d);
			ctrl.setParent(this);
			fp.getChildren().add(ctrl.build());
		}
	}

	private void loadPage(String page) {

		fp.getChildren().clear();

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource(page + ".fxml"));
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
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(page + ".fxml"));
			root = (Parent) fxmlLoader.load();
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

		popupCard.setTranslateX(320);
		loadPageLogin("/vues/Login");
		// loadPage("/vues/AddUtilisateur");

		// fp.getChildren().add(addMatC.build());

	}

	public void popup(String title, String text, Integer type) {
		// type 0 = bien = green
		// type 1 = moyen = orange
		// type 2 = mauvais = red

		popupCardTitle.getChildren().clear();
		Text t1 = new Text(title);

		if (type == 0) {
			t1.setFill(Color.web("#56d862"));
		} else if (type == 1) {
			t1.setFill(Color.web("#ffa412"));
		} else if (type == 2) {
			t1.setFill(Color.web("#ff3f0b"));
		}
		popupCardTitle.getChildren().add(t1);

		popupCardTextFlow.getChildren().clear();
		Text t2 = new Text(text);
		popupCardTextFlow.getChildren().add(t2);

		// annimation

		popupCard.setTranslateX(320);

		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(200));
		transition.setToX(0);
		transition.setNode(popupCard);
		transition.play();
		popupRetour();

	}

	private void popupRetour() {
		popupCard.setTranslateX(0);
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.millis(200));
		transition.setDelay(Duration.millis(3700));
		transition.setToX(320);
		transition.setNode(popupCard);
		transition.play();
	}

	private void resetStyleNavBtn() {
		materielBtn.getStyleClass().remove("selectedBtn");
		utilisateurBtn.getStyleClass().remove("selectedBtn");
		demandesBtn.getStyleClass().remove("selectedBtn");
		// retourslBtn.getStyleClass().remove("selectedBtn");
	}

	private void disableBtnNav() {
		materielBtn.setDisable(true);
		utilisateurBtn.setDisable(true);
		demandesBtn.setDisable(true);

		materielBtn.setVisible(false);
		utilisateurBtn.setVisible(false);
		demandesBtn.setVisible(false);

		disableDisconected();
		disableAddButton();
	}

	private void disableDisconected() {
		disconected.setDisable(true);
		disconected.setVisible(false);

	}

	private void eneableDisconected() {
		disconected.setDisable(false);
		disconected.setVisible(true);

	}

	private void enableBtnNav() {
		materielBtn.setDisable(false);
		utilisateurBtn.setDisable(false);
		demandesBtn.setDisable(false);
		disconected.setDisable(false);

		materielBtn.setVisible(true);
		utilisateurBtn.setVisible(true);
		demandesBtn.setVisible(true);
		disconected.setVisible(true);

		eneableDisconected();
	}

	void disableAddButton() {
		btnAjout.setDisable(true);
		btnAjout.setVisible(false);
	}

	private void enableAddButton() {
		btnAjout.setDisable(false);
		btnAjout.setVisible(true);
	}

}
