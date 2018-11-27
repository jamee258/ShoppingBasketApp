package com.supermarket.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingBasket {

	private Item basketItems;
	private BigDecimal basketTotal; // Current total price of the items in the basket.
	private int numberOfItems; // Total number of items on order in the basket.

	List<Item> shoppingList = new ArrayList<>();

	public ShoppingBasket(Item item) {
		printBasket(item);
		// addItem(item);
	}

	public void addProduct(int productId, String productName, int numberOfItems) {
		shoppingList.add(new Item());
		numberOfItems++;
	}

	public void removeProduct(Item item) {
		Iterator<Item> iterator = shoppingList.iterator();
		Item it = iterator.next();
		if (it.getProductName().equals(item))
			shoppingList.remove(item);
	}

	// Clear all contents of the Basket
	public void clearBasket(List<Item> item) {
		item.clear();
		numberOfItems = 0;
	}

	// Print all contents of the basket
	public void printBasket(Item item) {
		for (Item i : shoppingList) {
			System.out.println(i);
		}
		String list = shoppingList.toString();
		System.out.println(list);
	}

	// Getters & Setters
	public Item getBasketItems() {
		return basketItems;
	}

	public void setBasketItems(Item basketItems) {
		this.basketItems = basketItems;
	}

	public BigDecimal getBasketTotal() {
		return basketTotal;
	}

	public void setBasketTotal(BigDecimal basketTotal) {
		this.basketTotal = basketTotal;
	}

	public int getNumberOfItems() {
		return numberOfItems;
	}

	public void setNumberOfItems(int numberOfItems) {
		this.numberOfItems = numberOfItems;
	}

}