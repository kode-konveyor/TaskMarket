package com.kodekonveyor.market.http;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.mockito.Mockito;

public class HttpClientFactoryStubs {

	public List<CloseableHttpClient> entities = new ArrayList<>();
	public CloseableHttpClient current;

	public void behaviour(HttpClientFactory httpClientFactory) throws ClientProtocolException, IOException {
		current = Mockito.mock(CloseableHttpClient.class);
		entities.add(current);
		Mockito.doReturn(current).when(httpClientFactory).call();
	}

}
