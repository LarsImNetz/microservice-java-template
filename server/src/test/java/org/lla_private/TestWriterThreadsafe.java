package org.lla_private;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class TestWriterThreadsafe {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestWriterThreadsafe.class);

	@Test
	public void test() throws Exception {
		for (int i = 0; i < 1000; i++) {
			new Thread(new MyThread());
		}
		Thread.sleep(5 * 1000);
	}

	private static class MyThread implements Runnable {

		@Override
		public void run() {
			List<String> list = Arrays.asList("a", "b");
			createJsonString(list);
		}
	}

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	public static String createJsonString(final Object list) {
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

}
