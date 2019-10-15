package com.kodekonveyor.market.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.market.login.RegisterTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(JUnitPlatform.class)
public class HttpPostServiceTest {

	@InjectMocks
	HttpPostService httpPostService;
	@Mock
	FormEntityCreatorService formEntityCreatorService;
	@Mock
	HttpPostFactory httpPostFactory;
	@Mock
	HttpClientFactory httpClientFactory;

	HttpPostFactoryStubs httpPostFactoryStubs = new HttpPostFactoryStubs();
	HttpClientFactoryStubs httpClientFactoryStubs = new HttpClientFactoryStubs();

	@BeforeEach
	private void setUp() throws ClientProtocolException, IOException {

		httpPostFactoryStubs.behaviour(httpPostFactory);
		httpClientFactoryStubs.behaviour(httpClientFactory);
		FormEntityCreatorStubs.behaviour(formEntityCreatorService);
		HttpPostServiceLegacyStubs.behaviour(httpClientFactoryStubs, httpPostFactoryStubs);
	}

	@Test
	void returns_the_data_replied_by_the_post() throws IOException, URISyntaxException {
		String result = httpPostService.call(RegisterTestData.CALLBACK_URI, HttpTestData.CONTENT);
		assertEquals(HttpTestData.SERVICE_REPLY, result);
	}

	@Test
	void posts_to_the_url_given() throws IOException, URISyntaxException {
		httpPostService.call(RegisterTestData.CALLBACK_URI, HttpTestData.CONTENT);
		Mockito.verify(httpClientFactoryStubs.current).execute(httpPostFactoryStubs.current);
	}

}
