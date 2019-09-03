package com.yash.beverages;

public enum Drink {
	TEA("TEA", 10), BLACK_TEA("BLACK_TEA", 5), COFFEE("COFFEE", 15), BLACK_COFFEE("BLACK_COFFEE", 10);

	private final int price;

	Drink(String name, int price) {
		this.price = price;
	}

	public int getPrice() {
		return price;
	}
}
