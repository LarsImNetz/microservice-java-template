package org.lla_private;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestSimpleDTO {

	private static ObjectMapperHelper OBJECTMAPPER;

	@BeforeClass
	public static void setUp() {
		OBJECTMAPPER = new ObjectMapperHelper();
	}

	/**
	 * Simply create a empty class and create a json object of it.
	 */
	@Test
	public void testClassSimpleDTOEmpty() {
		final SimpleDTO simple = new SimpleDTO();

		final String json = OBJECTMAPPER.createJsonString(simple);
		Assert.assertEquals(
				"{\"vorname\":null,\"nachname\":null,\"geburtstag\":null,\"strasse\":null,\"hausnummer\":null,\"plz\":null,\"ort\":null}",
				json);
	}

	/**
	 * Simply create a class, fill it with data and create a json object of it.
	 */
	@Test
	public void testClassSimpleSerialize() {
		final SimpleDTO s = new SimpleDTO();
		s.setVorname("Lars");
		s.setNachname("Langhans");
		final LocalDate geburtstag = LocalDate.of(1968, Month.JANUARY, 4);
		s.setGeburtstag(geburtstag);
		s.setStrasse("Eine Strasse");
		s.setHausnummer("1");
		s.setPlz(12345);
		s.setOrt("Ein Ort");

		final String json = OBJECTMAPPER.createJsonString(s);
		Assert.assertEquals(
				"{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"strasse\":\"Eine Strasse\",\"hausnummer\":\"1\",\"plz\":12345,\"ort\":\"Ein Ort\"}",
				json);
	}

}
