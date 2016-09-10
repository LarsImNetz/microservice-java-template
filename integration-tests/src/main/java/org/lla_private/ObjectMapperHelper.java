package org.lla_private;

import java.io.IOException;
import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

final class ObjectMapperHelper {

	private static final Logger LOGGER = LoggerFactory.getLogger(ObjectMapperHelper.class);

	private final ObjectMapper OBJECT_MAPPER;

	public ObjectMapperHelper() {
		OBJECT_MAPPER = new ObjectMapper();
		// FORMATTER:OFF
		final SimpleModule module = new SimpleModule()
				.addSerializer(LocalDate.class, new LocalDateSerializer())
				.addDeserializer(LocalDate.class,	new LocalDateDeserializer());
		// FORMATTER:ON
		OBJECT_MAPPER.registerModule(module);
	}

	public String createJsonString(final Object list) {
		String jsonString = "";
		try {
			jsonString = OBJECT_MAPPER.writer().writeValueAsString(list);
		}
		catch (final Exception e) {
			LOGGER.warn("Could not serialize bean into JSON string", e);
			return "";
		}

		return jsonString;
	}

	public <T> Object createObject(final String json, final Class<T> resultClass) {
		try {
			final T result = OBJECT_MAPPER.readValue(json, resultClass);
			return result;
		}
		catch (final JsonMappingException e) {
			LOGGER.error(e.getMessage());
		}
		catch (final JsonParseException e) {
			LOGGER.error(e.getMessage());

		}
		catch (final IOException e) {
			LOGGER.error(e.getMessage());
		}
		return null;
	}
}
