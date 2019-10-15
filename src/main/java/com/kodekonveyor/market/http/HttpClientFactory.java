package com.kodekonveyor.market.http;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

@Service
public class HttpClientFactory {

	private final CloseableHttpClient httpClient = HttpClients.createDefault();
	
	public CloseableHttpClient call() {
		return httpClient;
	}

}
