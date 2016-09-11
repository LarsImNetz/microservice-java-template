package org.lla_private.guice.provider;

import javax.ws.rs.client.ClientBuilder;

import com.google.inject.Provider;

public final class RestClientProvider implements Provider<ClientBuilder> {

	private static final int HTTP_TIMEOUT_IN_MS = 25;

	@Override
	public ClientBuilder get() {
		final ClientBuilder restClient = ClientBuilder.newBuilder();
//		restClient.setConnectTimeout(HTTP_TIMEOUT_IN_MS);
//		restClient.setReadTimeout(HTTP_TIMEOUT_IN_MS);

		return restClient;
	}

}
