package org.homelinux.moonserver;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.homelinux.moonserver.bean.Bean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

@Path("/buchen")
public class BuchenAktualisierung {

	private static Logger LOGGER = LoggerFactory.getLogger(BuchenAktualisierung.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@GET
	@Path("{kundenNr}/{plz}")
	@Produces(MediaType.APPLICATION_JSON)
	public String updateRemainingLeads(@PathParam("kundenNr") final Integer kundenNr, @PathParam("plz") final Integer plz) {
		LOGGER.debug("updateRemainingLeads(" + kundenNr + ", " + plz + ") was called and returned ");
		Bean bean = new Bean();
		return createJsonString(bean);
	}

	// TODO: doppelt!
	private String createJsonString(final Object list) {
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
