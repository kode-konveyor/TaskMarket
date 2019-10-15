package com.kodekonveyor.market.http;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.methods.HttpPost;
import org.mockito.Mockito;

public class HttpPostFactoryStubs {
	
	public List<HttpPost> entities = new ArrayList<>();
	public HttpPost current;

	public void behaviour(HttpPostFactory httpPostFactory) {
		current = Mockito.mock(HttpPost.class);
		entities.add(current);
		Mockito.doReturn(current).when(httpPostFactory).call();
	}

}
