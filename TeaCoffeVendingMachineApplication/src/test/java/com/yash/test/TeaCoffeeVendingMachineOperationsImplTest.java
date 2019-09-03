package com.yash.test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.yash.beverages.Drink;
import com.yash.container.Container;
import com.yash.exception.TeaCoffeeVendingMachineException;
import com.yash.util.TeaCoffeeVendingMachineOperationsImpl;

@RunWith(MockitoJUnitRunner.class)
public class TeaCoffeeVendingMachineOperationsImplTest {

	@InjectMocks
	TeaCoffeeVendingMachineOperationsImpl machineImpl;

	@Mock
	Container container;

	@Rule
	public ExpectedException exception = ExpectedException.none();

	@Test
	public void prepareTeaWhenRequiredContainersHaveSufficientMaterial() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);
		machineImpl.prepareDrink(container, Drink.TEA.name(), 2);

		verify(container).getTeaContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
		verify(container).getMilkContainer();
	}

	@Test
	public void prepareBlackTeaWhenRequiredContainersHaveSufficientMaterial() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		machineImpl.prepareDrink(container, Drink.BLACK_TEA.name(), 2);
		machineImpl.prepareDrink(container, Drink.BLACK_TEA.name(), 2);

		verify(container, times(2)).getTeaContainer();
		verify(container, times(2)).getWaterContainer();
		verify(container, times(2)).getSugarContainer();
	}

	@Test
	public void prepareCoffeeWhenRequiredContainersHaveSufficientMaterial() {
		when(container.getCoffeeContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);
		machineImpl.prepareDrink(container, Drink.COFFEE.name(), 2);

		verify(container).getCoffeeContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
		verify(container).getMilkContainer();
	}

	@Test
	public void prepareBlackCoffeeWhenRequiredContainersHaveSufficientMaterial() {
		when(container.getCoffeeContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		machineImpl.prepareDrink(container, Drink.BLACK_COFFEE.name(), 2);

		verify(container).getCoffeeContainer();
		verify(container).getWaterContainer();
		verify(container).getSugarContainer();
	}

	@Test
	public void shouldThrowExceptionWhenTeaIsLessThanRequired() {
		exception.expect(TeaCoffeeVendingMachineException.class);
		exception.expectMessage("Insufficient tea in Container please fill the container");

		when(container.getTeaContainer()).thenReturn(0);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);
		machineImpl.prepareDrink(container, Drink.TEA.name(), 2);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionWhenCoffeeIsLessThanRequired() {
		when(container.getCoffeeContainer()).thenReturn(0);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);
		machineImpl.prepareDrink(container, Drink.COFFEE.name(), 2);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionWhenSugarIsLessThanRequired() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(0);
		when(container.getWaterContainer()).thenReturn(15000);
		machineImpl.prepareDrink(container, Drink.BLACK_TEA.name(), 2);

	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionWhenWaterIsLessThanRequired() {
		when(container.getCoffeeContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(0);
		machineImpl.prepareDrink(container, Drink.BLACK_COFFEE.name(), 2);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionWhenMilkIsLessThanRequired() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(0);
		machineImpl.prepareDrink(container, Drink.TEA.name(), 2);
	}

	@Test
	public void shouldReturnTeaAndPriceBasedOnQuantity() {
		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("TEA", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("TEA", 20);

		Map<String, Integer> actual = machineImpl.calculateTotalDrinkSaleAndWasteMaterial(drinkTypeAndQuantity);

		assertEquals(expected.get("TEA"), actual.get("TEA"));
	}

	@Test
	public void shouldReturnBlackTeaAndPriceBasedOnQuantity() {
		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("BLACK_TEA", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("BLACK_TEA", 10);

		Map<String, Integer> actual = machineImpl.calculateTotalDrinkSaleAndWasteMaterial(drinkTypeAndQuantity);

		assertEquals(expected.get("BLACK_TEA"), actual.get("BLACK_TEA"));
	}

	@Test
	public void shouldReturnCoffeeAndPriceBasedOnQuantity() {
		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("COFFEE", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("COFFEE", 30);

		Map<String, Integer> actual = machineImpl.calculateTotalDrinkSaleAndWasteMaterial(drinkTypeAndQuantity);

		assertEquals(expected.get("COFFEE"), actual.get("COFFEE"));
	}

	@Test
	public void shouldReturnBlackCoffeeAndPriceBasedOnQuantity() {
		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("BLACK_COFFEE", 2);

		Map<String, Integer> expected = new HashMap<>();
		expected.put("BLACK_COFFEE", 20);

		Map<String, Integer> actual = machineImpl.calculateTotalDrinkSaleAndWasteMaterial(drinkTypeAndQuantity);

		assertEquals(expected.get("BLACK_COFFEE"), actual.get("BLACK_COFFEE"));
	}

	@Test
	public void shouldReturnDrinkSaleAndWasteMaterialReport() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getCoffeeContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("TEA", 2);
		drinkTypeAndQuantity.put("BLACK_TEA", 3);
		drinkTypeAndQuantity.put("COFFEE", 4);
		drinkTypeAndQuantity.put("BLACK_COFFEE", 5);

		machineImpl.prepareDrink(container, Drink.TEA.name(), 2);
		machineImpl.prepareDrink(container, Drink.BLACK_TEA.name(), 3);
		machineImpl.prepareDrink(container, Drink.COFFEE.name(), 4);
		machineImpl.prepareDrink(container, Drink.BLACK_COFFEE.name(), 5);

		machineImpl.getTotalSaleReport(container);
	}

	@Test
	public void shouldReturnContainerStatus() {
		when(container.getTeaContainer()).thenReturn(2000);
		when(container.getCoffeeContainer()).thenReturn(2000);
		when(container.getSugarContainer()).thenReturn(8000);
		when(container.getWaterContainer()).thenReturn(15000);
		when(container.getMilkContainer()).thenReturn(10000);

		Map<String, Integer> drinkTypeAndQuantity = new HashMap<>();
		drinkTypeAndQuantity.put("TEA", 2);
		drinkTypeAndQuantity.put("BLACK_TEA", 3);
		drinkTypeAndQuantity.put("COFFEE", 4);
		drinkTypeAndQuantity.put("BLACK_COFFEE", 5);

		machineImpl.prepareDrink(container, Drink.TEA.name(), 2);
		machineImpl.prepareDrink(container, Drink.BLACK_TEA.name(), 3);
		machineImpl.prepareDrink(container, Drink.COFFEE.name(), 4);
		machineImpl.prepareDrink(container, Drink.BLACK_COFFEE.name(), 5);

		machineImpl.getContainerStatus(container);
	}

	@Test
	public void shouldRefillTeaContainerIfEnteredTeaAmountIsLessThanTeaContainerCapacity() {
		when(container.getTeaContainer()).thenReturn(200);

		Integer actual = machineImpl.refillContainer(container, 1, 150);
		Integer expected = 1;

		assertEquals(expected, actual);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionIfEnteredTeaAmountIsGreaterThanTeaContainerCapacity() {
		when(container.getTeaContainer()).thenReturn(2000);

		machineImpl.refillContainer(container, 1, 250);
	}

	@Test
	public void shouldRefillCoffeeContainerIfEnteredTeaAmountIsLessThanCoffeeContainerCapacity() {
		when(container.getCoffeeContainer()).thenReturn(200);

		Integer actual = machineImpl.refillContainer(container, 2, 150);
		Integer expected = 1;

		assertEquals(expected, actual);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionIfEnteredCoffeeAmountIsGreaterThanCoffeeContainerCapacity() {
		when(container.getCoffeeContainer()).thenReturn(2000);

		machineImpl.refillContainer(container, 2, 250);
	}

	@Test
	public void shouldRefillSugarContainerIfEnteredSugarAmountIsLessThanSugarContainerCapacity() {
		when(container.getSugarContainer()).thenReturn(200);

		Integer actual = machineImpl.refillContainer(container, 3, 100);
		Integer expected = 1;

		assertEquals(expected, actual);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionIfEnteredSugarAmountIsGreaterThanSugarContainerCapacity() {
		when(container.getSugarContainer()).thenReturn(8000);

		machineImpl.refillContainer(container, 3, 250);
	}

	@Test
	public void shouldRefillWaterContainerIfEnteredSugarAmountIsLessThanWaterContainerCapacity() {
		when(container.getWaterContainer()).thenReturn(200);

		Integer actual = machineImpl.refillContainer(container, 4, 100);
		Integer expected = 1;

		assertEquals(expected, actual);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionIfEnteredWaterAmountIsGreaterThanWaterContainerCapacity() {
		when(container.getWaterContainer()).thenReturn(15000);

		machineImpl.refillContainer(container, 4, 200);
	}

	@Test
	public void shouldRefillMilkContainerIfEnteredSugarAmountIsLessThanMilkContainerCapacity() {
		when(container.getMilkContainer()).thenReturn(1000);

		Integer actual = machineImpl.refillContainer(container, 5, 100);
		Integer expected = 1;

		assertEquals(expected, actual);
	}

	@Test(expected = TeaCoffeeVendingMachineException.class)
	public void shouldThrowExceptionIfEnteredMilkAmountIsGreaterThanMilkContainerCapacity() {
		when(container.getMilkContainer()).thenReturn(15000);

		machineImpl.refillContainer(container, 5, 200);
	}

	@Test
	public void shouldRunRefillContainerDefaultSwitchCaseBlockIfChoiceIsInvalid() {
		machineImpl.refillContainer(container, 0, 200);
	}

}
