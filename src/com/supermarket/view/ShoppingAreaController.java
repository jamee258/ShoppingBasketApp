package com.supermarket.view;

import java.math.BigDecimal;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.supermarket.dbutils.ShoppingItemDAO;
import com.supermarket.domain.Item;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Spinner;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * 
 * @author M.J.I
 *
 */
public class ShoppingAreaController {

	ObservableList<Item> itmData = FXCollections.observableArrayList();
	int numberOfItems = 0;
	BigDecimal totalPrice;

	// Discount-tracking variables
	int itemsWithId1, itemsWithId2, itemsWithId3, itemsWithId4 = 0;

	ObservableList<String> productList = FXCollections.observableArrayList("Beans", "Corn Flakes",
			"Cheddar Cheese (250g)", "Chicken (5 Slices)", "Milk (2 Litre Semi-Skimmed)", "Milk (1 Litre Semi-Skimmed)",
			"Milk (0.5 Litre Semi-Skimmed)", "Milk (2 Litre Skimmed)", "Milk (1 Litre Skimmed)",
			"Milk (0.5 Litre Skimmed)", "Margerine (500g)", "Butter (500g)", "Eggs (Pack of 6)", "Toothpaste");

	public ShoppingAreaController() {
	}

	// Basket Table
	@FXML
	private TableView<Item> itemTable;

	@FXML
	private TableColumn<Item, String> itemColumn;

	@FXML
	private TableColumn<Item, String> quantityColumn;

	@FXML
	private TableColumn<Item, String> offerColumn;

	@FXML
	private TableColumn<Item, String> unitPriceColumn;

	@FXML
	private TableColumn<Item, String> totalPriceColumn;

	// User-selected fields
	@FXML
	private Label itemLabel;

	@FXML
	private Label quantityLabel;

	@FXML
	private TextArea offersLabel;

	@FXML
	private TextField totalPriceLabel;

	@FXML
	private TextField poundSymbol;

	@FXML
	private TextField latestPriceLabel;

	@FXML
	private ComboBox<String> productBox;

	@FXML
	private ComboBox<Integer> quantityBox;

	@FXML
	private Spinner<Integer> quantitySpinner;

	@FXML
	private ListView<Item> productInfoList;

	@FXML
	private TextField numberOfItemsLabel;

	@FXML
	private Button removeButton;

	@FXML
	private Button exitButton;

	@FXML
	private Button clearButton;

	@FXML
	MainApp mainApp;

	@FXML
	private TextField inputText;

	@FXML
	private TextArea outputText;

	@FXML
	private URL location;

	@FXML
	private ResourceBundle resources;

	@FXML
	private void initialize() {
		productBox.setItems(productList);

		offersLabel.setWrapText(true);

		numberOfItemsLabel.setText("0");

		totalPrice = BigDecimal.ZERO;

		itemColumn.setCellValueFactory(cellData -> cellData.getValue().productNameProperty());
		quantityColumn.setCellValueFactory(cellData -> cellData.getValue().quantityProperty().asString());
		offerColumn.setCellValueFactory(cellData -> cellData.getValue().offerDescriptionProperty());
		unitPriceColumn.setCellValueFactory(cellData -> cellData.getValue().latestPriceProperty().asString());
		totalPriceColumn.setCellValueFactory(cellData -> cellData.getValue().totalPriceProperty().asString());

		poundSymbol.setEditable(false);
		latestPriceLabel.setEditable(false);
		numberOfItemsLabel.setEditable(false);
		totalPriceLabel.setEditable(false);
	}

	@FXML
	private void printOutput() {
		outputText.setText(inputText.getText());
	}

	// Close application on-click of exit button
	@FXML
	public void handleExitButtonAction(ActionEvent event) {
		Stage stage = (Stage) exitButton.getScene().getWindow();
		stage.close();
	}

	@FXML
	private void showItemDetails(Item item) {
		if (item != null) {
			// Fill the labels with info from the item object
			latestPriceLabel.setText(item.getLatestPrice().toString());
			offersLabel.setText("-" + item.getOfferDescription() + "-");
		} else {
			latestPriceLabel.setText("");
			offersLabel.setText("");
		}
	}

	// Populate user-selected fields
	@FXML
	private void fillFields(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		try {
			Item item = ShoppingItemDAO.searchItem(productBox.getValue());
			latestPriceLabel.setText("£" + item.getLatestPrice());
			offersLabel.setText(item.getOfferDescription());
		} catch (NullPointerException npe) {
			System.out.println("Selected item does not have a related offer");
		}
	}

	@FXML
	private void showQuantity() {
		int productQuantity = quantitySpinner.getValue();
		System.out.println(productQuantity);
	}

	// Dynamically search item details
	@FXML
	private void searchProduct(ActionEvent actionEvent) throws ClassNotFoundException, SQLException {
		Item item2 = new Item();
		item2 = ShoppingItemDAO.searchItem(productBox.getValue());
		int productQuantity = quantitySpinner.getValue();
		BigDecimal tableTotalPrice = (BigDecimal.valueOf(productQuantity))
				.multiply(((BigDecimal) item2.getLatestPrice()));
		BigDecimal finalAmount = applyDiscount(item2);
		item2.setQuantity(productQuantity);
		item2.setTotalPrice(finalAmount);
		BigDecimal totalPrice2 = finalAmount.add(totalPrice);
		System.out.println(totalPrice2);
		totalPriceLabel.setText(finalAmount.toString());
		populateAndShowItem(item2);
	}

	// BOGOF, Percentages & TFTPOT Discount
	@FXML
	private BigDecimal applyDiscount(Item item) {
		int productQuantity = item.getQuantity();
		int offerId = item.getOfferId();
		BigDecimal latestPrice = (BigDecimal) item.getLatestPrice();
		BigDecimal totalPrice = (BigDecimal.valueOf(productQuantity)).multiply(((BigDecimal) latestPrice));

		// BOGOF
		if (offerId == 1) {
			itemsWithId1 += productQuantity;
			if ((itemsWithId1 % 2) == 0) {
				int itemsHalved = itemsWithId1 /= 2;
				BigDecimal value = BigDecimal.valueOf(itemsHalved).multiply(latestPrice);
				BigDecimal finalTotalPrice = latestPrice.subtract(value);

				return finalTotalPrice;
			}
		}
		// 10% Discount
		else if (offerId == 2) {
			itemsWithId2 += productQuantity;
			BigDecimal finalTotalPrice2 = totalPrice.multiply(new BigDecimal(0.1));

			return finalTotalPrice2;
		}
		// TFTPOT
		else {

		}
		return latestPrice;
	}

	@FXML
	private void searchItemForTable(ActionEvent actionEvent) {
		try {
			Item item = ShoppingItemDAO.searchItem(productBox.getValue());
			// Populate Item Table
			populateItem(item);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	// Populate Item Object
	@FXML
	private void populateItem(Item item) {
		itmData.add(item);
		itemTable.setItems(itmData);
	}

	// Populate Item for Table
	@FXML
	private void populateAndShowItem(Item item) {
		if (item != null) {
			populateItem(item);
			quantitySpinner.setVisible(true);
			int quantity = quantitySpinner.getValue();
			numberOfItems += quantity;
			numberOfItemsLabel.setText(String.valueOf(numberOfItems));

		} else {
			System.out.println("This item does not exist\n");
		}
	}

	@FXML
	private void setItemInfoToPriceArea(Item item) {
		latestPriceLabel.setText(item.getLatestPrice().toString());
	}

	@FXML
	private void removeItemFromTable() {
		String currentQuantityString = numberOfItemsLabel.getText();
		int currentQuantity = Integer.parseInt(currentQuantityString);
		removeButton.setOnAction(e -> {
			Item selectedItem = itemTable.getSelectionModel().getSelectedItem();
			try {
				int selectedItemQuantity = selectedItem.getQuantity();
				int latestItemQuantity = currentQuantity - selectedItemQuantity;
				numberOfItemsLabel.setText(String.valueOf(latestItemQuantity));
				itemTable.getItems().remove(selectedItem);
			} catch (NullPointerException npe) {
				System.out.println(
						"Either you have not selected an item, or there are currently no items in the basket.");
			}
		});
	}

	@FXML
	private void clearBasket() {
		for (int i = 0; i < itemTable.getItems().size(); i++) {
			itemTable.getItems().clear();
		}
		numberOfItems = 0;
		numberOfItemsLabel.setText("0");
		totalPrice = BigDecimal.ZERO;
		totalPriceLabel.setText(totalPrice.toString() + ".00");
	}
}