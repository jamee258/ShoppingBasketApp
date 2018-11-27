package com.supermarket.dbutils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataRetriever {

	private static Connection conn;

	static final String hostName = "localhost";
	static final String sqlInstanceName = "SQL2014";
	static final String database = "Supermarket";
	static final String db_userid = "JUser123";
	static final String db_password = "Config123!";

	static String dbConnectionUrl;

	// TODO - Try/Catch Block
	public static Connection getConnection() throws ClassNotFoundException, SQLException {
		return getJdbcSqlConnection(database, db_userid, db_password);
	}

	// TODO - Try/Catch Block
	public static Connection getJdbcSqlConnection(String database, String userName, String password)
			throws ClassNotFoundException, SQLException {
		try {
			// Register JDBC Driver
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

			// Open a connection
			// Move to configuration file later
			// SQL Server Authentication Connection URL:
			dbConnectionUrl = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" + sqlInstanceName
					+ ";databaseName=" + database;

			conn = DriverManager.getConnection(dbConnectionUrl, userName, password);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;

	}

	// public static void dbConnect() {
	// try {
	//
	// // Register JDBC Driver
	// Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	//
	// // Open a connection
	// // Move to configuration file later
	// // SQL Server Authentication Connection URL:
	// dbConnectionUrl = "jdbc:sqlserver://" + hostName + ":1433" + ";instance=" +
	// sqlInstanceName
	// + ";databaseName=" + database;
	//
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// }

	// public static void main(String[] args) {
	// DataRetriever connServer = new DataRetriever();
	// try {
	// displayProducts();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// connServer.toString();
	// }

}