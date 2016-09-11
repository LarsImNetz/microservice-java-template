package org.lla_private;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;
import org.lla_private.bean.Bean;
import org.lla_private.guice.annotation.WebserviceRestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;


public class Webservice implements IWebservice {

	public static final String QA_REST_URL = "http://qa.vgl_bv.hypoport.local:8167/baushup-webservice-server-lars-kontingent/";
	public static final String PROD_REST_URL = "http://rzsolv206.rz-hypoport.local:8167/baushup-webservice-server-lars-kontingent/";
	public static final String LOCAL_REST_URL = "http://localhost:8082/baushup-webservice-server-lars-kontingent/";

	static final String REQUEST_PATH = "kontingente/available/";
	static final String UPDATE_PATH = "buchen/";

	private static Logger LOGGER = LoggerFactory.getLogger(Webservice.class);
	private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

	@Inject
	@WebserviceRestClient
	private Client restClient;
	// Client client = ClientBuilder.newBuilder().build();
	private URI uri;

	public Webservice() {
		try {
			uri = new URI(QA_REST_URL);
		}
		catch (final URISyntaxException e) {
			LOGGER.error("Beim Setzen der Basis-URI ist ein Fehler aufgetreten", e);
			uri = null;
		}
	}

	public void setUri(final URI uri) {
		this.uri = uri;
	}

	URI createUriWithKundenNr(final Integer kundenNr) {
		if (kundenNr != null) {
			try {
				final String path = this.uri.getPath() + REQUEST_PATH + kundenNr;

				return new URI(this.uri.getScheme(), this.uri.getAuthority(), path, null, null);
			}
			catch (final URISyntaxException e) {
				LOGGER.error("Beim Erstellen der URL ist ein Fehler aufgetreten", e);
			}
		}

		return null;
	}

	URI createUriWithKundenNrAndPlz(final Integer kundenNr, final Integer plz) {
		if (kundenNr != null && plz != null) {
			final String plzAsString = String.valueOf(plz);
			try {
				final String path = this.uri.getPath() + REQUEST_PATH + kundenNr + "/" + plzAsString;

				return new URI(this.uri.getScheme(), this.uri.getAuthority(), path, null, null);
			}
			catch (final URISyntaxException e) {
				LOGGER.error("Beim Erstellen der URL ist ein Fehler aufgetreten", e);
			}
		}

		return null;
	}

	URI createDecrementUriWithKundenNrAndPlz(final Integer kundenNr, final Integer plz) {
		if (kundenNr != null && plz != null) {
			final String plzAsString = String.valueOf(plz);
			try {
				final String path = this.uri.getPath() + UPDATE_PATH + kundenNr + "/" + plzAsString;

				return new URI(this.uri.getScheme(), this.uri.getAuthority(), path, null, null);
			}
			catch (final URISyntaxException e) {
				LOGGER.error("Beim Erstellen der URL ist ein Fehler aufgetreten", e);
			}
		}

		return null;
	}

	@Override
	public String hasAnyKontingent(final Integer kundenNr) {
		// final URI uriWithPlz = createUriWithKundenNr(kundenNr);
		// final String entityString = getContentFromURL(uriWithPlz);

		return "hasAnyKontingent";
	}

	@Override
	public String hasKontingent(final Integer kundenNr, final Integer plz) {
		// final URI uriWithPlz = createUriWithKundenNrAndPlz(kundenNr, plz);
		// final String entityString = getContentFromURL(uriWithPlz);

		return "has Kontingent";
	}

	@Override
	public String decrementKontingent(final Integer kundenNr, final Integer plz) {
		// final URI uriWithPlz = createDecrementUriWithKundenNrAndPlz(kundenNr,
		// plz);
		// final String entityString = getContentFromURL(uriWithPlz);

		return "decrement";
	}

	Bean createLarsKontingentBean(final String entityString) {
		Bean response = null;
		try {
			response = OBJECT_MAPPER.readValue(entityString, Bean.class);
		}
		catch (final IOException e) {
			LOGGER.warn("Could not generate bean from response string", e);
		}
		return response;
	}

	String getContentFromURL(final URI uri) {
		String entityString = "";
//		try {
			final Response clientResponse = restClient.target(uri).request(MediaType.APPLICATION_JSON).get();
			entityString = clientResponse.readEntity(String.class);
//		}
//		catch (final ClientHandlerException e) {
//
//			LOGGER.warn("Exception during REST call to uri: " + uri, e);
//		}
//		catch (final UniformInterfaceException e) {
//
//			LOGGER.warn("Exception during REST call to uri: " + uri, e);
//		}
		return entityString;
	}

	// Pair<Boolean, String> isAvailableKontingent(final String entityString) {
	// try {
	// final KontingentAvailable response =
	// OBJECT_MAPPER.readValue(entityString, KontingentAvailable.class);
	// return new Pair<Boolean, String>(response.isAvailable(),
	// response.getReason());
	// }
	// catch (final IOException e) {
	// return new Pair<Boolean, String>(false, "no valid response");
	// }
	// }
	//
	// DecrementStatus parseDecrementStatus(final String entityString) {
	// try {
	// final UpdateSuccessful response = OBJECT_MAPPER.readValue(entityString,
	// UpdateSuccessful.class);
	// return new DecrementStatus(response.isSuccess(),
	// Betrag.valueOf(response.getPreis()));
	// }
	// catch (final IOException e) {
	// LOGGER.warn("Could not update kontingent für entityString = " +
	// entityString, e);
	// return new DecrementStatus(false, Betrag.ZERO);
	// }
	// }
}
