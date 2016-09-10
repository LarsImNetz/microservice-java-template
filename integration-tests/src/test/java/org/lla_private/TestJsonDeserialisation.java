package org.lla_private;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class TestJsonDeserialisation {

	private static ObjectMapperHelper OBJECTMAPPER;

	@BeforeClass
	public static void setUp() {
		OBJECTMAPPER = new ObjectMapperHelper();
	}

	@Test
	public void testClassBDeserialize() {
		final String json = "{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"plz\":12345}";

		final Object obj = OBJECTMAPPER.createObject(json, SimpleDTO.class);
		Assert.assertNotNull(obj);

		final SimpleDTO s = (SimpleDTO) obj;

		Assert.assertEquals(1968, s.getGeburtstag().getYear());
		Assert.assertEquals(1, s.getGeburtstag().getMonthValue());
		Assert.assertEquals(4, s.getGeburtstag().getDayOfMonth());

		Assert.assertEquals("Lars", s.getVorname());
		Assert.assertEquals("Langhans", s.getNachname());

		Assert.assertEquals(null, s.getStrasse());
		Assert.assertEquals(null, s.getHausnummer());
		Assert.assertEquals(Integer.valueOf(12345), s.getPlz());
		Assert.assertEquals(null, s.getOrt());
	}
}
