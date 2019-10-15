package com.kodekonveyor.market.login;

import static org.mockito.Mockito.times;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(JUnitPlatform.class)
public class LoginServletTest {

	@InjectMocks
	LoginService loginServlet;
	
	@Mock
	private HttpServletResponse resp;

	@Test
	public void if_there_is_an_authenticated_user_and_a_next_parameter_then_redirect_to_that_url() throws IOException, ServletException {
		loginServlet.call(RegisterTestData.LOGIN_SERVLET_REQUEST_WITH_USER_AND_NEXT, resp);
		Mockito.verify(resp).sendRedirect(RegisterTestData.NEXT_VALUE);
		Mockito.verify(resp, times(1)).sendRedirect(Mockito.anyString());
	}

	@Test
	public void if_there_is_no_next_parameter_then_redirect_to_noNextUrl_context_parameter() throws IOException, ServletException {
		loginServlet.call(RegisterTestData.LOGIN_SERVLET_REQUEST_WITHOUT_USER_WITHOUT_NEXT, resp);
		Mockito.verify(resp).sendRedirect(RegisterTestData.NO_NEXT_URL);
		Mockito.verify(resp, times(1)).sendRedirect(Mockito.anyString());
	}

	@Test
	public void if_there_is_no_authenticated_user_and_there_is_next_parameter_then_redirect_to_the_right_auth_url() throws IOException, ServletException {
		loginServlet.call(RegisterTestData.LOGIN_SERVLET_REQUEST_WITHOUT_USER_WITH_NEXT, resp);
		Mockito.verify(resp).sendRedirect(RegisterTestData.RIGHT_REDIRECT_URI);
		Mockito.verify(resp, times(1)).sendRedirect(Mockito.anyString());
	}


}
