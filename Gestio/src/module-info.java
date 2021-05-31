module Gestio {
	requires java.sql;
	requires javafx.controls;
	requires javafx.fxml;
	
	requires transitive javafx.base;
	requires transitive javafx.graphics;
	
	opens controllers to javafx.fxml;
	exports gestio to javafx.graphics;
}