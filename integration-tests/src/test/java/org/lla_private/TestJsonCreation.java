package org.lla_private;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJsonCreation {

	private static ObjectMapperHelper OBJECTMAPPER;

	@BeforeClass
	public static void setUp() {
		OBJECTMAPPER = new ObjectMapperHelper();
	}

	@Test
	public void testValue() {
		final Integer value = Integer.valueOf(3);
		final String json = OBJECTMAPPER.createJsonString(value);
		Assert.assertEquals("3", json);
	}

	@Test
	public void testString() {
		final String hello = "Hello World!";
		final String json = OBJECTMAPPER.createJsonString(hello);
		Assert.assertEquals("\"Hello World!\"", json);
	}

	@Test
	public void testArray() {
		final List<String> list = Arrays.asList("a", "b");
		final String json = OBJECTMAPPER.createJsonString(list);
		Assert.assertEquals("[\"a\",\"b\"]", json);
	}

	// -------------------------------------------------------------

	private static class A {

		int eins;

		public A(final int eins) {
			this.eins = eins;
		}

		public int getEins() {
			return this.eins;
		}

		public void setEins(final int eins) {
			this.eins = eins;
		}
	}

	@Test
	public void testClassA() {
		final A a = new A(2);

		final String json = OBJECTMAPPER.createJsonString(a);
		Assert.assertEquals("{\"eins\":2}", json);
	}

}
