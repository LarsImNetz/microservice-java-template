package org.lla_private;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

final class LocalDateSerializer extends JsonSerializer<LocalDate> {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yyyy");

	@Override
	public void serialize(final LocalDate value, final JsonGenerator gen, final SerializerProvider serializers) throws IOException {
		gen.writeString(Objects.isNull(value)
				? ""
				: value.format(FORMATTER));
	}

}