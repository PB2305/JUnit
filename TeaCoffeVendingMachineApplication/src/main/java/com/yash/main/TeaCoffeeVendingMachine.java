package com.yash.main;

import java.util.logging.Logger;

import com.yash.beverages.Drink;
import com.yash.container.Container;
import com.yash.container.ScannerService;
import com.yash.util.TeaCoffeeVendingMachineOperationsImpl;

public class TeaCoffeeVendingMachine {
	Logger logger = Logger.getLogger(TeaCoffeeVendingMachine.class.getName());
	Container container = new Container();
	TeaCoffeeVendingMachineOperationsImpl machine = new TeaCoffeeVendingMachineOperationsImpl();
	ScannerService scanner = new ScannerService();

	public void displayOptions(Boolean flag) {

		Integer choice = 0;
		Integer drinkQuantity = 0;
		Integer containerChoice = 0;
		Boolean isContinueRefill = true;
		Integer refillContainerAmount = 0;
		Integer refillCount = 0;

		do {
			logger.info("===============================================================================");
			logger.info("\nPlease enter your choice:" + "\n1: Tea " + "\n2: Black Tea " + "\n3: Coffee "
					+ "\n4: Black Coffee " + "\n5: Total Sale Report " + "\n6: Container Status "
					+ "\n7: Refill Container " + "\n8: Reset Container " + "\n9: Exit TCVM");

			choice = scanner.nextInt();
			switch (choice) {
			case 1:
				logger.info("Please enter how many cup of Tea do you want?");
				drinkQuantity = scanner.nextInt();
				logger.info("Preparing " + drinkQuantity + " cup of Tea");
				machine.prepareDrink(container, Drink.TEA.name(), drinkQuantity);
				logger.info("Your Tea is prepared");
				break;
			case 2:
				logger.info("Please enter how many cup of Black Tea do you want?");
				drinkQuantity = scanner.nextInt();
				logger.info("Preparing " + drinkQuantity + " cup of Black Tea");
				machine.prepareDrink(container, Drink.BLACK_TEA.name(), drinkQuantity);
				logger.info("Your Black tea is prepared");
				break;
			case 3:
				logger.info("Please enter how many cup of Coffee do you want?");
				drinkQuantity = scanner.nextInt();
				logger.info("Preparing " + drinkQuantity + " cup of Coffee");
				machine.prepareDrink(container, Drink.COFFEE.name(), drinkQuantity);
				logger.info("Your Coffee is prepared");
				break;
			case 4:
				logger.info("Please enter how many cup of Black Coffee do you want?");
				drinkQuantity = scanner.nextInt();
				logger.info("Preparing " + drinkQuantity + " cup of Black Coffee");
				machine.prepareDrink(container, Drink.BLACK_COFFEE.name(), drinkQuantity);
				logger.info("Your drink is prepared");
				break;
			case 5:
				machine.getTotalSaleReport(container);
				break;
			case 6:
				machine.getContainerStatus(container);
				break;
			case 7:
				do {
					logger.info("\nWhich container do you want to refill:" + "\n\n1: Tea Container "
							+ "\n2: Coffee Container " + "\n3: Sugar Container " + "\n4: Water Container "
							+ "\n5: Milk Container ");
					containerChoice = scanner.nextInt();
					logger.info("Please Enter Container Amount : ");
					refillContainerAmount = scanner.nextInt();
					refillCount = machine.refillContainer(container, containerChoice, refillContainerAmount);
					logger.info("Container Refill Count is : " + refillCount);
					logger.info("Do you want to continue if yes enter 1 else 0");
					isContinueRefill = scanner.nextInt() == 1 ? true : false;
				} while (isContinueRefill);
				break;
			case 8:
				container.fillContainer();
				logger.info("Successfully filled all containers");
				break;
			case 9:
				flag = false;
				logger.info("Exit TCVM");
				break;
			default:
				logger.info("Invalid input..Please make valid containar choice.");
			}
		} while (flag);
	}
}
