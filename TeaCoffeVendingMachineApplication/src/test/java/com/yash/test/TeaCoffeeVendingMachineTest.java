package com.yash.test;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.beverages.Drink;
import com.yash.container.Container;
import com.yash.container.ScannerService;
import com.yash.main.TeaCoffeeVendingMachine;
import com.yash.util.TeaCoffeeVendingMachineOperationsImpl;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineTest {

	@InjectMocks
	TeaCoffeeVendingMachine machine;

	@Mock
	TeaCoffeeVendingMachineOperationsImpl machineImpl;

	@Mock
	Container container;

	@Mock
	ScannerService scanner;

	@Test
	public void shouldRunTeaChoiceSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(1, 2);
		doNothing().when(machineImpl).prepareDrink(container, Drink.TEA.name(), 2);
		machine.displayOptions(false);
		verify(machineImpl).prepareDrink(container, Drink.TEA.name(), 2);
	}

	@Test
	public void shouldRunBlackTeaChoiceSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(2, 2);
		doNothing().when(machineImpl).prepareDrink(container, Drink.BLACK_TEA.name(), 2);
		machine.displayOptions(false);
		verify(machineImpl).prepareDrink(container, Drink.BLACK_TEA.name(), 2);
	}

	@Test
	public void shouldRunCoffeeChoiceSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(3, 2);
		doNothing().when(machineImpl).prepareDrink(container, Drink.COFFEE.name(), 2);
		machine.displayOptions(false);
		verify(machineImpl).prepareDrink(container, Drink.COFFEE.name(), 2);
	}

	@Test
	public void shouldRunBlackCoffeeChoiceSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(4, 2);
		doNothing().when(machineImpl).prepareDrink(container, Drink.BLACK_COFFEE.name(), 2);
		machine.displayOptions(false);
		verify(machineImpl).prepareDrink(container, Drink.BLACK_COFFEE.name(), 2);
	}

	@Test
	public void shouldRunTotalSaleReportSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(5);
		doNothing().when(machineImpl).getTotalSaleReport(container);
		machine.displayOptions(false);
		verify(machineImpl).getTotalSaleReport(container);
	}

	@Test
	public void shouldRunContainerStatusSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(6);
		doNothing().when(machineImpl).getContainerStatus(container);
		machine.displayOptions(false);
		verify(machineImpl).getContainerStatus(container);
	}

	@Test
	public void shouldRunRefillContainerSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(7, 1, 200);
		when(machineImpl.refillContainer(container, 1, 200)).thenReturn(1);
		machine.displayOptions(false);
		verify(machineImpl).refillContainer(container, 1, 200);
	}

	@Test
	public void shouldRunRefillContainerWhileCondition() {
		when(scanner.nextInt()).thenReturn(7, 0, 200, 1, 0);
		when(machineImpl.refillContainer(container, 0, 200)).thenReturn(0);
		machine.displayOptions(false);
		verify(machineImpl).refillContainer(container, 0, 200);
	}

	@Test
	public void shouldRunFillContainerChoiceSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(8);
		doNothing().when(container).fillContainer();
		machine.displayOptions(false);
		verify(container).fillContainer();
	}

	@Test
	public void shouldRunExitTCVMSwitchCaseBlock() {
		when(scanner.nextInt()).thenReturn(9);
		machine.displayOptions(false);
	}

	@Test
	public void shouldRunDefaultSwitchCaseBlockIfChoiceIsInvalid() {
		when(scanner.nextInt()).thenReturn(10);
		machine.displayOptions(false);
	}
}
