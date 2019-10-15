package com.kodekonveyor.market.login;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.market.TestHelper;
import com.kodekonveyor.market.http.HttpPostService;
import com.kodekonveyor.market.http.HttpPostStubs;
import com.kodekonveyor.market.login.LoginCallbackService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(JUnitPlatform.class)
public class LoginCallbackServletTest {

	@InjectMocks
	LoginCallbackService loginCallbackServlet;
	@Mock
	HttpPostService httpPostService;
	
	HttpServletResponse resp = Mockito.mock(HttpServletResponse.class);
	
	@BeforeEach
	private void setUp() throws IOException, URISyntaxException {
		HttpPostStubs.behaviour(httpPostService);
	}

	@Test
	void if_there_is_no_code_parameter_then_redirects_to_noCode_context_parameter() throws IOException, URISyntaxException {
		loginCallbackServlet.call(RegisterTestData.CALLBACK_REQUEST, resp);
		Mockito.verify(resp).sendRedirect(RegisterTestData.NO_CODE_VALUE);
	}
	
	@Test
	void if_there_is_code_parameter_then_calls_the_url_in_token_url_context_parameter() throws IOException, URISyntaxException {
		loginCallbackServlet.call(RegisterTestData.CALLBACK_REQUEST_WITH_CODE, resp);
		ArgumentCaptor<URI> captor = ArgumentCaptor.forClass(URI.class);
		Mockito.verify(httpPostService).call(captor.capture(), Mockito.any());
		assertEquals(RegisterTestData.TOKEN_URI,captor.getValue());
		
	}

	@Test
	void the_token_url_is_called_with_the_code() throws IOException, URISyntaxException {
		loginCallbackServlet.call(RegisterTestData.CALLBACK_REQUEST_WITH_CODE, resp);
		@SuppressWarnings("unchecked")
		ArgumentCaptor<Map<String,String>> captor = ArgumentCaptor.forClass(Map.class);
		Mockito.verify(httpPostService).call(Mockito.any(),captor.capture());
		TestHelper.assertMapContainsAtKey(
				RegisterTestData.CODE_TOKEN_VALUE,
				LoginCallbackService.CODE_REQ_PARAM_NAME,
				captor.getValue());		
	}

}
