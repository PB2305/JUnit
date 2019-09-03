package com.yash.util;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.yash.beverages.BlackCoffee;
import com.yash.beverages.BlackTea;
import com.yash.beverages.Coffee;
import com.yash.beverages.Drink;
import com.yash.beverages.Tea;
import com.yash.container.Container;
import com.yash.exception.TeaCoffeeVendingMachineException;

public class TeaCoffeeVendingMachineOperationsImpl implements TeaCoffeeVendingMachineOperations {

	Logger logger = Logger.getLogger(TeaCoffeeVendingMachineOperationsImpl.class.getName());

	private int teaConsumptionAndWasteTotal = 0;
	private int coffeeConsumptionAndWasteTotal = 0;
	private int sugarConsumptionAndWasteTotal = 0;
	private int waterConsumptionAndWasteTotal = 0;
	private int milkConsumptionAndWasteTotal = 0;
	private Integer refillCounter = 0;

	public TeaCoffeeVendingMachineOperationsImpl() {
		System.setProperty("java.util.logging.SimpleFormatter.format", "%5$s%6$s%n\u001B[30m");
	}

	private Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();

	@Override
	public void prepareDrink(Container container, String drinkType, Integer quantity) {
		int teaContainer = container.getTeaContainer();
		int coffeeContainer = container.getCoffeeContainer();
		int sugarContainer = container.getSugarContainer();
		int waterContainer = container.getWaterContainer();
		int milkContainer = container.getMilkContainer();

		calculateTotalConsumptionAndWasteOfMaterial(drinkType, quantity);
		checkIsEnoughMaterialAvailableInContainer(teaContainer, coffeeContainer, sugarContainer, waterContainer,
				milkContainer);

		container.setTeaContainer(teaContainer - teaConsumptionAndWasteTotal);
		container.setCoffeeContainer(coffeeContainer - coffeeConsumptionAndWasteTotal);
		container.setSugarContainer(sugarContainer - sugarConsumptionAndWasteTotal);
		container.setWaterContainer(waterContainer - waterConsumptionAndWasteTotal);
		container.setMilkContainer(milkContainer - milkConsumptionAndWasteTotal);

		if (drinkTypeAndQuantity.containsKey(drinkType)) {
			drinkTypeAndQuantity.put(drinkType, drinkTypeAndQuantity.get(drinkType) + quantity);
		} else {
			drinkTypeAndQuantity.put(drinkType, quantity);
		}

	}

	private void calculateTotalConsumptionAndWasteOfMaterial(String drinkType, Integer quantity) {
		if (drinkType.equalsIgnoreCase(Drink.TEA.name())) {
			teaConsumptionAndWasteTotal = (Tea.TEA.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTotal = (Tea.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTotal = (Tea.WATER.getConsumptionAndWasteTotal()) * quantity;
			milkConsumptionAndWasteTotal = (Tea.MILK.getConsumptionAndWasteTotal()) * quantity;

		} else if (drinkType.equalsIgnoreCase(Drink.BLACK_TEA.name())) {
			teaConsumptionAndWasteTotal = (BlackTea.BLACK_TEA.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTotal = (BlackTea.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTotal = (BlackTea.WATER.getConsumptionAndWasteTotal()) * quantity;

		} else if (drinkType.equalsIgnoreCase(Drink.COFFEE.name())) {
			coffeeConsumptionAndWasteTotal = (Coffee.COFFEE.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTotal = (Coffee.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTotal = (Coffee.WATER.getConsumptionAndWasteTotal()) * quantity;
			milkConsumptionAndWasteTotal = (Coffee.MILK.getConsumptionAndWasteTotal()) * quantity;

		} else {
			coffeeConsumptionAndWasteTotal = (BlackCoffee.BLACK_COFFEE.getConsumptionAndWasteTotal()) * quantity;
			sugarConsumptionAndWasteTotal = (BlackCoffee.SUGAR.getConsumptionAndWasteTotal()) * quantity;
			waterConsumptionAndWasteTotal = (BlackCoffee.WATER.getConsumptionAndWasteTotal()) * quantity;
		}
	}

	private void checkIsEnoughMaterialAvailableInContainer(int teaContainer, int coffeeContainer, int sugarContainer,
			int waterContainer, int milkContainer) {
		if (teaContainer < teaConsumptionAndWasteTotal) {
			throw new TeaCoffeeVendingMachineException("Insufficient tea in Container please fill the container");
		}
		if (coffeeContainer < coffeeConsumptionAndWasteTotal) {
			throw new TeaCoffeeVendingMachineException("Insufficient coffee in Container please fill the container");
		}
		if (sugarContainer < sugarConsumptionAndWasteTotal) {
			throw new TeaCoffeeVendingMachineException("Insufficient sugar in Container please fill the container");
		}
		if (waterContainer < waterConsumptionAndWasteTotal) {
			throw new TeaCoffeeVendingMachineException("Insufficient water in Container please fill the container");
		}
		if (milkContainer < milkConsumptionAndWasteTotal) {
			throw new TeaCoffeeVendingMachineException("Insufficient milk in Container please fill the container");
		}
	}

	public void getTotalSaleReport(Container container) {
		Map<String, Integer> drinkTypeAndPrice = calculateTotalDrinkSaleAndWasteMaterial(drinkTypeAndQuantity);

		Integer totalDrinkQuantity = drinkTypeAndQuantity.entrySet().stream()
				.collect(Collectors.summingInt(x -> x.getValue()));
		Integer totalDrinkSaleCost = drinkTypeAndPrice.entrySet().stream()
				.collect(Collectors.summingInt(x -> x.getValue()));
		logger.info("\n---------------- Total Tea-Coffee Sale (Cup and Cost) Report -----------------\n");
		logger.info("Total Drink Quantity Sale is " + totalDrinkQuantity + " and Total Cost is " + totalDrinkSaleCost);
	}

	public Map<String, Integer> calculateTotalDrinkSaleAndWasteMaterial(Map<String, Integer> drinkTypeAndQuantity) {
		Map<String, Integer> drinkTypeAndPrice = new HashMap<>();

		int teaWastage = 0;
		int coffeeWastage = 0;
		int sugarWastage = 0;
		int waterWastage = 0;
		int milkWastage = 0;

		if (drinkTypeAndQuantity.containsKey("TEA")) {
			drinkTypeAndPrice.put("TEA", drinkTypeAndQuantity.get("TEA") * Drink.TEA.getPrice());

			teaWastage += drinkTypeAndQuantity.get("TEA") * Tea.TEA.getWasteMaterial();
			sugarWastage += drinkTypeAndQuantity.get("TEA") * Tea.SUGAR.getWasteMaterial();
			waterWastage += drinkTypeAndQuantity.get("TEA") * Tea.WATER.getWasteMaterial();
			milkWastage += drinkTypeAndQuantity.get("TEA") * Tea.MILK.getWasteMaterial();
		}

		if (drinkTypeAndQuantity.containsKey("BLACK_TEA")) {
			drinkTypeAndPrice.put("BLACK_TEA", drinkTypeAndQuantity.get("BLACK_TEA") * Drink.BLACK_TEA.getPrice());

			teaWastage += drinkTypeAndQuantity.get("BLACK_TEA") * BlackTea.BLACK_TEA.getWasteMaterial();
			sugarWastage += drinkTypeAndQuantity.get("BLACK_TEA") * BlackTea.SUGAR.getWasteMaterial();
			waterWastage += drinkTypeAndQuantity.get("BLACK_TEA") * BlackTea.WATER.getWasteMaterial();
		}

		if (drinkTypeAndQuantity.containsKey("COFFEE")) {
			drinkTypeAndPrice.put("COFFEE", drinkTypeAndQuantity.get("COFFEE") * Drink.COFFEE.getPrice());

			coffeeWastage += drinkTypeAndQuantity.get("COFFEE") * Coffee.COFFEE.getWasteMaterial();
			sugarWastage += drinkTypeAndQuantity.get("COFFEE") * Coffee.SUGAR.getWasteMaterial();
			waterWastage += drinkTypeAndQuantity.get("COFFEE") * Coffee.WATER.getWasteMaterial();
			milkWastage += drinkTypeAndQuantity.get("COFFEE") * Coffee.MILK.getWasteMaterial();
		}

		if (drinkTypeAndQuantity.containsKey("BLACK_COFFEE")) {
			drinkTypeAndPrice.put("BLACK_COFFEE",
					drinkTypeAndQuantity.get("BLACK_COFFEE") * Drink.BLACK_COFFEE.getPrice());

			coffeeWastage += drinkTypeAndQuantity.get("BLACK_COFFEE") * BlackCoffee.BLACK_COFFEE.getWasteMaterial();
			sugarWastage += drinkTypeAndQuantity.get("BLACK_COFFEE") * BlackCoffee.SUGAR.getWasteMaterial();
			waterWastage += drinkTypeAndQuantity.get("BLACK_COFFEE") * BlackCoffee.WATER.getWasteMaterial();
		}

		logger.info("\n------------------------ Total Drink-wise Sale Report ------------------------\n");
		drinkTypeAndPrice.forEach((k, v) -> logger
				.info(k + " : Total sale is " + drinkTypeAndQuantity.get(k) + " and total cost of sale is " + v));

		logger.info("\n--------------------------- Waste Material Report ----------------------------");
		logger.info("\nTotal Tea Wastage : " + teaWastage + " gm" + "\nTotal Coffee Wastage : " + coffeeWastage + " gm"
				+ "\nTotal Sugar Wastage : " + sugarWastage + " gm" + "\nTotal Water Wastage : " + waterWastage + " ml"
				+ "\nTotal Milk Wastage : " + milkWastage + " ml" + "\n");

		return drinkTypeAndPrice;
	}

	public void getContainerStatus(Container container) {
		logger.info("\n============ Container Status Report (Quantity of Material Present) ==========\n");
		logger.info("Tea Container Status : " + container.getTeaContainer() + "\nCoffee Container Status :"
				+ container.getCoffeeContainer() + "\nSugar Container Status : " + container.getSugarContainer()
				+ "\nWater Container Status : " + container.getWaterContainer() + "\nMilk Container Status : "
				+ container.getMilkContainer());
	}

	public Integer refillContainer(Container container, Integer containerChoice, Integer refillContainerAmount) {
		switch (containerChoice) {
		case 1:
			if (refillContainerAmount <= 2000 - container.getTeaContainer()) {
				container.setTeaContainer(container.getTeaContainer() + refillContainerAmount);
				refillCounter += 1;
			} else {
				throw new TeaCoffeeVendingMachineException("Entered Amount Is Greater Than Tea Container Capacity");
			}
			break;
		case 2:
			if (refillContainerAmount <= 2000 - container.getCoffeeContainer()) {
				container.setCoffeeContainer(container.getCoffeeContainer() + refillContainerAmount);
				refillCounter += 1;
			} else {
				throw new TeaCoffeeVendingMachineException("Entered Amount Is Greater Than Coffee Container Capacity");
			}
			break;
		case 3:
			if (refillContainerAmount <= 8000 - container.getSugarContainer()) {
				container.setSugarContainer(container.getSugarContainer() + refillContainerAmount);
				refillCounter += 1;
			} else {
				throw new TeaCoffeeVendingMachineException("Entered Amount Is Greater Than Sugar Container Capacity");
			}
			break;
		case 4:
			if (refillContainerAmount <= 15000 - container.getWaterContainer()) {
				container.setWaterContainer(container.getWaterContainer() + refillContainerAmount);
				refillCounter += 1;
			} else {
				throw new TeaCoffeeVendingMachineException("Entered Amount Is Greater Than Water Container Capacity");
			}
			break;

		case 5:
			if (refillContainerAmount <= 10000 - container.getMilkContainer()) {
				container.setMilkContainer(container.getMilkContainer() + refillContainerAmount);
				refillCounter += 1;
			} else {
				throw new TeaCoffeeVendingMachineException("Entered Amount Is Greater Than Milk Container Capacity");
			}
			break;
		default:
			logger.info("Invalid input please make valid container choice");
		}
		return refillCounter;
	}
}
