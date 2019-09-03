package com.yash.container;

public class Container {
	private int teaContainer;
	private int coffeeContainer;
	private int sugarContainer;
	private int waterContainer;
	private int milkContainer;

	public Container() {
		fillContainer();
	}

	public int getTeaContainer() {
		return teaContainer;
	}

	public void setTeaContainer(int teaContainer) {
		this.teaContainer = teaContainer;
	}

	public int getCoffeeContainer() {
		return coffeeContainer;
	}

	public void setCoffeeContainer(int coffeeContainer) {
		this.coffeeContainer = coffeeContainer;
	}

	public int getSugarContainer() {
		return sugarContainer;
	}

	public void setSugarContainer(int sugarContainer) {
		this.sugarContainer = sugarContainer;
	}

	public int getWaterContainer() {
		return waterContainer;
	}

	public void setWaterContainer(int waterContainer) {
		this.waterContainer = waterContainer;
	}

	public int getMilkContainer() {
		return milkContainer;
	}

	public void setMilkContainer(int milkContainer) {
		this.milkContainer = milkContainer;
	}

	public void fillContainer() {
		teaContainer = 2000;
		coffeeContainer = 2000;
		sugarContainer = 8000;
		waterContainer = 15000;
		milkContainer = 10000;
	}
}
