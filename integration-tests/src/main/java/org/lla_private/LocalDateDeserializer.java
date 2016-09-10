package org.lla_private;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

final class LocalDateDeserializer extends JsonDeserializer<LocalDate> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public LocalDate deserialize(final JsonParser p, final DeserializationContext ctxt) throws IOException, JsonProcessingException {
		final String fromJson = p.getValueAsString();
		final LocalDate date = LocalDate.parse(fromJson, FORMATTER);
		return date;
	}

}
