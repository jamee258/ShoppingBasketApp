package com.supermarket.view;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;

import com.supermarket.dbutils.ShoppingItemDAO;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * 
 * @author M.J.I
 *
 */
public class MainApp extends Application {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Application.launch(args);
		// ShoppingItemDAO.displayProducts("Beans");

	}

	@Override
	public void start(Stage stage) throws IOException {

		// Create the FXMLLoader
		FXMLLoader loader = new FXMLLoader();
		// Path to the FXML File
		String fxmlDocPath = "C:\\Users\\ISLAMM\\git\\ShoppingBasket\\Shopping\\src\\com\\islam\\muhammed\\supermarket\\view\\ShoppingUI.fxml";
		FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

		// Create the Pane and all Details
		AnchorPane root = (AnchorPane) loader.load(fxmlStream);

		// Create the Scene
		Scene scene = new Scene(root);
		// Set the Scene to the Stage
		stage.setScene(scene);
		// Set the Title to the Stage
		stage.setTitle("Jamee's Shopping Basket");
		// Display the Stage
		stage.show();
	}
}