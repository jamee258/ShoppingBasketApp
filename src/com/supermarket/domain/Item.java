package com.supermarket.domain;

import java.math.BigDecimal;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * 
 * @author M.J.I
 *
 */
public class Item {

	// Item Information fields
	private IntegerProperty productId;
	private StringProperty productName;
	private SimpleObjectProperty<BigDecimal> latestPrice;
	private IntegerProperty quantity;
	private SimpleObjectProperty<BigDecimal> totalPrice;
	// private SimpleObjectProperty<BigDecimal> totalPrice = latestPrice
	// .multiply(new SimpleObjectProperty<BigDecimal>(quantity));

	// Item Offer fields
	private IntegerProperty offerId;
	private StringProperty offerDescription;
	private IntegerProperty discountPercentage;

	public Item() {
		this.productId = new SimpleIntegerProperty();
		this.productName = new SimpleStringProperty();
		this.latestPrice = new SimpleObjectProperty<>();
		this.quantity = new SimpleIntegerProperty();
		this.totalPrice = new SimpleObjectProperty<>();
		this.offerId = new SimpleIntegerProperty();
		this.offerDescription = new SimpleStringProperty();
		this.discountPercentage = new SimpleIntegerProperty();
	}

	public Item(String productName, BigDecimal latestPrice) {
		this.productName = new SimpleStringProperty(productName);
		this.latestPrice = new SimpleObjectProperty<BigDecimal>(latestPrice);
	}

	public Item(int productId, String productName, int quantity) {
		this.productId = new SimpleIntegerProperty(productId);
		this.productName = new SimpleStringProperty(productName);
		this.quantity = new SimpleIntegerProperty(quantity);
	}

	public void addItems(int numberOfItemsToAdd) {
		this.quantity = new SimpleIntegerProperty(numberOfItemsToAdd);
	}

	// Getters & Setters

	// Product ID
	public int getProductId() {
		return productId.get();
	}

	public void setProductId(int productId) {
		this.productId.set(productId);
	}

	public IntegerProperty productIdProperty() {
		return productId;
	}

	// Product Name
	public String getProductName() {
		return productName.get();
	}

	public void setProductName(String productName) {
		this.productName.set(productName);
	}

	public StringProperty productNameProperty() {
		return productName;
	}

	// Latest Price
	public Object getLatestPrice() {
		return latestPrice.get();
	}

	public void setLatestPrice(BigDecimal latestPrice) {
		this.latestPrice.set(latestPrice);
	}

	public SimpleObjectProperty<BigDecimal> latestPriceProperty() {
		return latestPrice;
	}

	// Quantity
	public int getQuantity() {
		return quantity.get();
	}

	public void setQuantity(int quantity) {
		this.quantity.set(quantity);
	}

	public IntegerProperty quantityProperty() {
		return quantity;
	}

	// Total Price
	public Object getTotalPrice() {
		return totalPrice.get();
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice.set(totalPrice);
	}

	public SimpleObjectProperty<BigDecimal> totalPriceProperty() {
		return totalPrice;
	}

	// Offer ID
	public int getOfferId() {
		return offerId.get();
	}

	public void setOfferId(int offerId) {
		this.offerId.set(offerId);
	}

	public IntegerProperty offerIdProperty() {
		return offerId;
	}

	// Offer Description
	public String getOfferDescription() {
		return offerDescription.get();
	}

	public void setOfferDescription(String offerDescription) {
		this.offerDescription.set(offerDescription);
	}

	public StringProperty offerDescriptionProperty() {
		return offerDescription;
	}

	// Discount Percentage
	public int getDiscountPercentage() {
		return discountPercentage.get();
	}

	public void setDiscountPercentage(int discountPercentage) {
		this.discountPercentage.set(discountPercentage);
	}

	public IntegerProperty discountPercentageProperty() {
		return discountPercentage;
	}

	@Override
	public String toString() {
		return "Product Name: " + productName + "Unit Price = " + latestPrice;
	}

}