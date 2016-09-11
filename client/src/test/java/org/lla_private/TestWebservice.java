package org.lla_private;

import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.lla_private.guice.annotation.WebserviceRestClient;
import org.mockito.Mockito;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

public class TestWebservice {

	private Webservice kontingente;
	private WebserviceTestModule testModule;

	@Before
	public void before() {
		testModule = new WebserviceTestModule();
		final Injector injector = Guice.createInjector(testModule);

		kontingente = new Webservice();
		injector.injectMembers(kontingente);
	}

//	private static class WebserviceTestModule extends AbstractModule {
//
//		public Builder builderMock;
//		public ClientResponse clientResponseMock;
//
//		@Override
//		protected void configure() {
//			final ClientBuilder clientMock = Mockito.mock(ClientBuilder.class);
//			
//			final WebTarget webResourceMock = Mockito.mock(WebTarget.class);
//			builderMock = Mockito.mock(Builder.class);
//			clientResponseMock = Mockito.mock(ClientResponse.class);
//
//			// Gesamtaufruf: response =
//			// restClient.resource(...).accept(...).get(...);
//			// client.resource() -> webResource
//			Mockito.doReturn(webResourceMock).when(clientMock).resource(Mockito.any(URI.class));
//			// webResource.accept() -> builder
//			Mockito.doReturn(builderMock).when(webResourceMock).accept(Mockito.anyString());
//			// builder.get() -> clientResponse
//			Mockito.doReturn(clientResponseMock).when(builderMock).get(Mockito.<Class<?>> any());
//			// clientResponse.getEntity() -> ""
//			Mockito.doReturn("").when(clientResponseMock).getEntity(Mockito.<Class<?>> any());
//
//			// Gesamtaufruf: response =
//			// restClient.resource(...).type(...).accept(...).post(..., ...);
//			Mockito.doReturn(clientResponseMock).when(builderMock).post(Mockito.<Class<?>> any(), Mockito.anyString());
//
//			bind(ClientBuilder.class).annotatedWith(WebserviceRestClient.class).toInstance(clientMock);
//		}
//
//	}
	private static class WebserviceTestModule extends AbstractModule {

		@Override
		protected void configure() {
			final Client clientMock = Mockito.mock(Client.class);
			
			// final WebTarget webResourceMock = Mockito.mock(WebTarget.class);
			bind(Client.class).annotatedWith(WebserviceRestClient.class).toInstance(clientMock);
		}

	}

	
	@Test
	public void testCreateUriWithKundenNr_with_KundenNr() {
		final URI uri = kontingente.createUriWithKundenNr(1799);
		Assert.assertEquals(Webservice.QA_REST_URL + Webservice.REQUEST_PATH + "1799", uri.toString());
	}

	@Test
	public void testCreateUriWithKundenNr_with_KundenNr_null() {
		final URI uri = kontingente.createUriWithKundenNr(null);
		Assert.assertNull(uri);
	}

	@Test
	public void testCreateUriWithKundenNrAndPlz_with_KundenNr_and_Plz() {
		final URI uri = kontingente.createUriWithKundenNrAndPlz(1799, 4);
		Assert.assertEquals(Webservice.QA_REST_URL + Webservice.REQUEST_PATH + "1799/4", uri.toString());
	}

	@Test
	public void testCreateUriWithKundenNrAndPlz_with_KundenNr_null() {
		final URI uri = kontingente.createUriWithKundenNrAndPlz(null, 1);
		Assert.assertNull(uri);
	}

	@Test
	public void testCreateDecrementUriWithKundenNrAndPlz_with_KundenNr_and_Plz() {
		final URI uri = kontingente.createDecrementUriWithKundenNrAndPlz(1799, 4);
		Assert.assertEquals(Webservice.QA_REST_URL + Webservice.UPDATE_PATH + "1799/4", uri.toString());
	}

	@Test
	public void testCreateDecrementUriWithKundenNrAndPlz_with_KundenNr_null() {
		final URI uri = kontingente.createDecrementUriWithKundenNrAndPlz(null, 1);
		Assert.assertNull(uri);
	}

//	@Test
//	public void testHasKontingent_Available_true() {
//		Mockito.doReturn("{\"available\":true,\"reason\":\"reason\"}").when(testModule.clientResponseMock).getEntity(String.class);
//		final String actual = kontingente.hasKontingent(1799, 40000);
//		Assert.assertEquals("has Kontingent", actual);
//	}

}
