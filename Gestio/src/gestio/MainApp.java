package gestio;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import tools.Connect;


public class MainApp extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Connect conn = new Connect("Gestio");
        
        ArrayList<String> fields = new ArrayList<String>();
	    ArrayList<String> type = new ArrayList<String>();
	    ArrayList<String> specs = new ArrayList<String>();
	    
	    fields.add("id_utilisateur");
	    type.add("integer");
	    specs.add("PRIMARY KEY AUTOINCREMENT");
	    
	    fields.add("nom_utilisateur");
	    type.add("string");
	    specs.add("NOT NULL");
	    
		conn.createNewTable("utilisateurs",fields,type,specs);

		

		
		conn.insert("utilisateurs", "1,'Jean'" );
		conn.insert("utilisateurs", "2,'Franck'" );
		//conn.selectAll();
		// TODO Auto-generated method stub
		Parent loginPage = FXMLLoader.load(getClass().getResource("/vues/Main.fxml"));
		
		Scene scene = new Scene(loginPage);
		
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
