package com.kodekonveyor.market.http;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.mockito.Mockito;

public class HttpPostServiceLegacyStubs {

	public static void behaviour(HttpClientFactoryStubs httpClientFactoryStubs, HttpPostFactoryStubs httpPostFactoryStubs) throws ClientProtocolException, IOException {

		CloseableHttpResponse response = Mockito.mock(CloseableHttpResponse.class);
		Mockito.doReturn(response).when(httpClientFactoryStubs.current).execute(httpPostFactoryStubs.current);
		HttpEntity responseEntity = Mockito.mock(HttpEntity.class);
		Mockito.doReturn(responseEntity).when(response).getEntity();
		InputStream responseInputStream =  new ByteArrayInputStream(HttpTestData.SERVICE_REPLY.getBytes());
		Mockito.doReturn(responseInputStream).when(responseEntity).getContent();
		Mockito.doReturn((long)HttpTestData.SERVICE_REPLY.length()).when(responseEntity).getContentLength();
	}

}
