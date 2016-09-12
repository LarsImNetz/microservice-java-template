package org.lla_private;

import java.time.LocalDate;
import java.time.Month;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class ITestSimpleDTOMultiple {

	private static ObjectMapperHelper OBJECTMAPPER;

	@BeforeClass
	public static void setUp() {
		OBJECTMAPPER = new ObjectMapperHelper();
	}

	/*
	 * Long run Integration Test
	 * create a class object, fill it with data
	 * run some times and create json objects of it.
	 * all the time change a value
	 * from interest is the time to work
	 */
	@Test
	public void testClassSimpleSerializeMultiple() {
		final SimpleDTO s = new SimpleDTO();
		s.setVorname("Lars");
		s.setNachname("Langhans");
		final LocalDate geburtstag = LocalDate.of(1968, Month.JANUARY, 4);
		s.setGeburtstag(geburtstag);
		s.setStrasse("Eine Strasse");
		s.setHausnummer("1");
		int plz = 10001;
		s.setPlz(plz);
		s.setOrt("Ein Ort");

		long start = System.currentTimeMillis();
		int count = 0;
		for (plz = 1; plz <= 100000; plz++) {
			s.setPlz(plz);
			final String json = OBJECTMAPPER.createJsonString(s);
			Assert.assertNotNull(json);
			++count;
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Serialize Time: " + end + "ms of " + count + " iterations.");
	}

	/*
	 * Long run Integration Test
	 * create a json object by hand and fill it with data
	 * run some times and create java object out of the json object.
	 * all the time check the java object is not null
	 * from interest is the time to work
	 */
	@Test
	public void testClassSimpleDeserializeMultiple() {

		long start = System.currentTimeMillis();
		int count = 0;
		for (int plz = 1; plz <= 100000; plz++) {
			final StringBuilder builder = new StringBuilder()
					.append("{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"strasse\":\"Eine Strasse\",\"hausnummer\":\"1\",\"plz\":")
					.append(plz).append(",\"ort\":\"Ein Ort\"}");

			final Object obj = OBJECTMAPPER.createObject(builder.toString(), SimpleDTO.class);
			Assert.assertNotNull(obj);
			++count;
		}
		long end = System.currentTimeMillis() - start;
		System.out.println("Deserialize Time: " + end + "ms of " + count + " iterations.");
	}

	@Test
	public void testClassSerializeDeserialize() {
		/* Erstellen eines Objektes mit new und füttern der Daten */
		final SimpleDTO s = new SimpleDTO();
		s.setVorname("Lars");
		s.setNachname("Langhans");
		final LocalDate geburtstag = LocalDate.of(1968, Month.JANUARY, 4);
		s.setGeburtstag(geburtstag);
		s.setStrasse("Eine Strasse");
		s.setHausnummer("1");
		int plz = 10001;
		s.setPlz(plz);
		s.setOrt("Ein Ort");

		/* Objekt Serialisieren */
		final String json = OBJECTMAPPER.createJsonString(s);

		/* Ein ähnliches Objekt mittels JSON erstellen */
		final StringBuilder builder = new StringBuilder()
				.append("{\"vorname\":\"Lars\",\"nachname\":\"Langhans\",\"geburtstag\":\"04.01.1968\",\"strasse\":\"Eine Strasse\",\"hausnummer\":\"1\",\"plz\":")
				.append(plz).append(",\"ort\":\"Ein Ort\"}");

		String expectedJson = builder.toString();

		Assert.assertEquals(expectedJson, json);

		/* Mittels OBJECTMAPPER aus JSON ein Object machen */
		final Object obj = OBJECTMAPPER.createObject(builder.toString(), SimpleDTO.class);

		/* Java CAST aus dem Object ein SimpleDTO machen */
		final SimpleDTO s2 = (SimpleDTO) obj;

		Assert.assertEquals(Integer.valueOf(10001), s2.getPlz());
		Assert.assertEquals("Lars", s2.getVorname());
	}

}
