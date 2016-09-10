package org.homelinux.moonserver;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.Assert;
import org.junit.Test;

public class TestLocalDate {

	@Test
	public void testGermanFormat() {
		final String date = "04.01.1970";
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

		final LocalDate ldt = LocalDate.parse(date, FORMATTER);

		Assert.assertEquals(1970, ldt.getYear());
		Assert.assertEquals(1, ldt.getMonthValue());
		Assert.assertEquals(4, ldt.getDayOfMonth());
	}

	@Test
	public void testISO() {
		final String date = "1970-01-04";
		final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

		final LocalDate ldt = LocalDate.parse(date, FORMATTER);

		Assert.assertEquals(1970, ldt.getYear());
		Assert.assertEquals(1, ldt.getMonthValue());
		Assert.assertEquals(4, ldt.getDayOfMonth());
	}

}
