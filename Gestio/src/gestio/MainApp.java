package gestio;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {
	 public static void main(String[] args) {
	        launch(args);
	    }

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		
		// TODO Auto-generated method stub
		Parent loginPage = FXMLLoader.load(getClass().getResource("/vues/Main.fxml"));
		
		Scene scene = new Scene(loginPage);
		
		primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/img/logoApp.png")));
		primaryStage.setTitle("Gestio");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
}
