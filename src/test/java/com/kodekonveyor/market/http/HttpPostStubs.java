package com.kodekonveyor.market.http;

import java.io.IOException;
import java.net.URISyntaxException;

import org.mockito.Mockito;

import com.kodekonveyor.market.http.HttpPostService;
import com.kodekonveyor.market.login.RegisterTestData;

public class HttpPostStubs {

	public static void behaviour(HttpPostService httpPostService) throws IOException, URISyntaxException {
		Mockito.doReturn(RegisterTestData.TOKEN_RETURN)
			.when(httpPostService)
				.call(Mockito.eq(RegisterTestData.TOKEN_URI),Mockito.any());
	}
}