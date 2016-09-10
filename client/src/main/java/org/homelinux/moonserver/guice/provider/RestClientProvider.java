package org.homelinux.moonserver.guice.provider;

import com.google.inject.Provider;
import com.sun.jersey.api.client.Client;

public final class RestClientProvider implements Provider<Client> {

	private static final int HTTP_TIMEOUT_IN_MS = 25;

	@Override
	public Client get() {
		final Client restClient = Client.create();

		restClient.setConnectTimeout(HTTP_TIMEOUT_IN_MS);
		restClient.setReadTimeout(HTTP_TIMEOUT_IN_MS);

		return restClient;
	}

}
