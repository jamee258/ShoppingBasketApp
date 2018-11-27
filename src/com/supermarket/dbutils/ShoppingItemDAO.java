package com.supermarket.dbutils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.supermarket.domain.Item;

/**
 * 
 * @author M.J.I
 *
 */
public class ShoppingItemDAO {

	public static void displayProducts(String product) throws SQLException, ClassNotFoundException {

		List<String> itemData = new ArrayList<>();

		String queryString = "SELECT [ProductName], [UnitPrice] FROM Products WHERE ProductName = ?";

		// Check Connection
		System.out.println("Database Connecting...");
		DataRetriever.getConnection();
		System.out.println("-Database Connection Successful-");

		try (Connection conn = DataRetriever.getConnection()) {

			// Execute SQL SELECT Statement and store in ResultSet
			PreparedStatement ps = conn.prepareStatement(queryString);
			ps.setString(1, product);
			ResultSet rs = ps.executeQuery();

			// Extract data from ResultSet
			while (rs.next()) {
				String productName = rs.getString("ProductName");
				String latestPrice = rs.getString("UnitPrice");
				itemData.add(productName);
				itemData.add(latestPrice);

				System.out.println("Product Name: " + itemData.get(0) + " | Unit Price = " + itemData.get(1));
			}

		}

	}

	public static Item searchItem(String itemName) throws SQLException, ClassNotFoundException {

		Connection conn = null;

		// SQL Query String with Inner Joins to combine data from 3 tables
		String queryString = "SELECT dbo.Products.ProductName, dbo.Products.UnitPrice, dbo.Offers.OfferDescription, dbo.Offers.OfferId\r\n"
				+ "FROM dbo.Products\r\n"
				+ "INNER JOIN dbo.ProductOffers ON dbo.Products.ProductId = dbo.ProductOffers.ProductId\r\n"
				+ "INNER JOIN dbo.Offers ON dbo.ProductOffers.OfferId = dbo.Offers.OfferId\r\n"
				+ "WHERE dbo.Products.ProductName = '" + itemName + "'";

		try {
			// Check Connection
			System.out.println("Database Connecting...");
			DataRetriever.getConnection();
			System.out.println("-Database Connection Successful-");

			conn = DataRetriever.getConnection();
			// Execute SQL SELECT Statement and store in ResultSet
			PreparedStatement preparedStatement = conn.prepareStatement(queryString);
			ResultSet rs = preparedStatement.executeQuery();

			Item item = getItemFromResultSet(rs);

			return item;

		} catch (SQLException e) {
			System.out.println("While searching a product with name '" + itemName + "', an error occurred: " + e);
			throw e;
		} finally {
			if (conn != null)
				conn.close();
			System.out.println("--Connection Closed--");
		}
	}

	private static Item getItemFromResultSet(ResultSet rs) throws SQLException {

		Item itm = null;
		if (rs.next()) {
			itm = new Item();
			// itm.setProductId(rs.getInt("ProductId"));
			itm.setProductName(rs.getString("ProductName"));
			itm.setLatestPrice(rs.getBigDecimal("UnitPrice"));
			itm.setOfferDescription(rs.getString("OfferDescription"));
			itm.setOfferId(rs.getInt("OfferId"));
		}
		return itm;
	}

}