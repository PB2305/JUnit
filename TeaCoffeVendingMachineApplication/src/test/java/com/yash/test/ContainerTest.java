package com.yash.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.yash.container.Container;

public class ContainerTest {
	Container container = new Container();

	@Test
	public void shouldFillTeaContainer() {
		container.setTeaContainer(2000);
		assertEquals(2000, container.getTeaContainer());
	}

	@Test
	public void shouldFillCoffeeContainer() {
		container.setCoffeeContainer(2000);
		assertEquals(2000, container.getCoffeeContainer());
	}

	@Test
	public void shouldFillSugarContainer() {
		container.setSugarContainer(8000);
		assertEquals(8000, container.getSugarContainer());
	}

	@Test
	public void shouldFillWaterContainer() {
		container.setWaterContainer(15000);
		assertEquals(15000, container.getWaterContainer());
	}

	@Test
	public void shouldFillMilkContainer() {
		container.setMilkContainer(10000);
		assertEquals(10000, container.getMilkContainer());
	}
}
