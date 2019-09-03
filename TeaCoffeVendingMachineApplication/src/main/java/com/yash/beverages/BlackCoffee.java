package com.yash.beverages;

public enum BlackCoffee {
	BLACK_COFFEE(3, 0), WATER(100, 12), SUGAR(15, 2);

	private final int consumption;
	private final int waste;

	BlackCoffee(int consumption, int waste) {
		this.consumption = consumption;
		this.waste = waste;
	}

	public int getWasteMaterial() {
		return waste;
	}

	public int getConsumptionAndWasteTotal() {
		return consumption + waste;
	}
}
