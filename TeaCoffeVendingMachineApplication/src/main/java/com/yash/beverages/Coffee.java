package com.yash.beverages;

public enum Coffee {
	COFFEE(4, 1), WATER(20, 3), MILK(80, 8), SUGAR(15, 2);

	private final int consumption;
	private final int waste;

	Coffee(int consumption, int waste) {
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
