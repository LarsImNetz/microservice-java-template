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

@Path("/abfrage")
public class Abfrage {

	private static Logger LOGGER = LoggerFactory.getLogger(Abfrage.class);

	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@GET
	@Path("list/{kundenNr}")
	@Produces(MediaType.APPLICATION_JSON)
	public String getKontingente(@PathParam("kundenNr") final Integer kundenNr) {
		Bean bean = new Bean();
		bean.setA("Hello World");
		LOGGER.debug("getKontingente(" + kundenNr + ") was called and returned a bean");
		return createJsonString(bean);
	}

	@GET
	@Path("available/{kundenNr}")
	@Produces(MediaType.APPLICATION_JSON)
	public String hasAnyKontingent(@PathParam("kundenNr") final Integer kundenNr) {
		LOGGER.debug("hasAnyKontingent(" + kundenNr + ") was called and returned ");
		return createJsonString("a");
	}

	@GET
	@Path("available/{kundenNr}/{plz}")
	@Produces(MediaType.APPLICATION_JSON)
	public String hasKontingent(@PathParam("kundenNr") final Integer kundenNr, @PathParam("plz") final Integer plz) {
		LOGGER.debug("hasKontingent(" + kundenNr + ", " + plz + ") was called and returned");

		return createJsonString(kundenNr + " " + plz);
	}

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
