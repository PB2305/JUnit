package com.yash.beverages;

public enum Tea {

	TEA(5, 1), WATER(60, 5), MILK(40, 4), SUGAR(15, 2);

	private final int consumption;
	private final int waste;

	Tea(int consumption, int waste) {
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
