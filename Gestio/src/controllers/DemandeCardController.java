package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

import gestio.Demande;
import gestio.Materiel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import model.DemandesModel;

public class DemandeCardController implements Initializable {
	
	private Demande demandeModel;
	
	private MainController parent;
	
	DemandeCardController(Demande demandeModel){
		this.demandeModel = demandeModel;
	}
	
	@FXML
	private Label demandeName;
	
	@FXML
	private Label demandeEmail;
	
	@FXML
	private FlowPane listeMaterielsContainer;
	
	@FXML
	private ImageView userImgView;
	
	private HashMap<Materiel, ArrayList<Integer>> listMateriels;
	
	
	public AnchorPane build() {
		AnchorPane card = null;
		
			
		FXMLLoader loader = new FXMLLoader(
			    getClass().getResource(
			        "/vues/Demandes.fxml"
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

	@FXML
	public void handleClickValider(ActionEvent e) {
		System.out.println("Valide");
		DemandesModel demMod = new DemandesModel("Gestio");
		demMod.validerDemande(demandeModel);
		parent.pageDemandes();
		
		
	}

	
	@FXML
	public void handleClickRefuser(MouseEvent e) {
		System.out.println("Refuse");
		DemandesModel demMod = new DemandesModel("Gestio");
		demMod.validerDemande(demandeModel);
		parent.pageDemandes();
	}
	


	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		demandeName.setText(this.demandeModel.getUser().getFirstName());
		demandeEmail.setText(this.demandeModel.getUser().getEmail());
		
		demandeName.setPadding(new Insets(0, 0, 0, 15)); 
		demandeEmail.setPadding(new Insets(0, 0, 0, 15)); 
		
		Image userImg = new Image(getClass().getResourceAsStream(this.demandeModel.getUser().getImageUserPath()));
	
		userImgView.setImage(userImg);
		
		listMateriels = this.demandeModel.getMateriels();
		
		
		for (Map.Entry<Materiel, ArrayList<Integer>> item : listMateriels.entrySet()) {
			VBox matItem = new VBox(); 
			
			matItem.setSpacing(-10);
			matItem.setPadding(new Insets(10, 10, 10, 10)); 
			matItem.setAlignment(Pos.BASELINE_CENTER);
			
			/*@TODO, modifier �a par une img*/
			Image matImg = new Image(getClass().getResourceAsStream(item.getKey().getImagePath()));
			ImageView matImgView = new ImageView();
			matImgView.setImage(matImg);
			matImgView.setFitHeight(50);
			matImgView.setPreserveRatio(true);
		
			Label matQuantite = new Label();	
			
			Integer quant = 0;
			for(int i = 0; i < item.getValue().size(); i++) {
				quant+= item.getValue().get(i);
			}
			
			matQuantite.setText(String.valueOf(quant));
			matQuantite.getStyleClass().add("demandeCount");
			
			matItem.getChildren().add(matImgView);
			matItem.getChildren().add(matQuantite);
			
			listeMaterielsContainer.getChildren().add(matItem);
		}
		
	}

	public void setParent(MainController parent) {
		this.parent = parent;
	}
	

}
