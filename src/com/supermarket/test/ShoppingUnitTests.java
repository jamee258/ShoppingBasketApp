package com.supermarket.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.supermarket.dbutils.ShoppingItemDAO;
import com.supermarket.domain.Item;
import com.supermarket.view.MainApp;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.stage.Stage;

/**
 * 
 * @author M.J.I
 *
 */
public class ShoppingUnitTests {

	private static Item item;

	@BeforeAll
	public static void init() {
		try {
			item = ShoppingItemDAO.searchItem("Chicken (5 Slices)");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void shouldCreateANewShoppingBasket() throws InterruptedException {
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				new JFXPanel();
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						try {
							new MainApp().start(new Stage());
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				});
			}

		});
		thread.start();
		Thread.sleep(10000);
	}

	@Test
	public void testSearchItem() throws ClassNotFoundException, SQLException {
		String productName = item.getProductName();
		String itemLatestPrice = item.getLatestPrice().toString();
		String productOfferDescription = item.getOfferDescription();

		assertEquals("Chicken (5 Slices)", productName);
		assertEquals("2.49", itemLatestPrice);
		assertEquals("Three for the price of two on selected sandwich fillers", productOfferDescription);

	}

	@Test
	public void testRemoveItem() {

	}

	@Test
	public void testClearBasket() {

	}

	@Test
	public void testIncrementValue() {

	}

}