package com.kodekonveyor.market.login;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.mockito.Mockito;

public class RegisterTestData {

	public static final String CLIENT_ID_VALUE = "clientIdValue";
	public static final String NO_NEXT_URL = "/?error=No next parameter given";
	public static final String GITHUB_AUTH_URL = "https://github.com/login/oauth/authorize";
	public static final String CALLBACK_URI_STRING = "https://market.kodekonveyor.com/dynamic/auth";
	public static final URI CALLBACK_URI = get_CALBACK_URI();
	public static final String NEXT_VALUE = "/hello";
	public static final String USER_NAME = "magwas";
	public static final String CODE_TOKEN_VALUE = "the_code_token";
	public static final String TOKEN_RETURN = "access_token=e72e16c7e42f292c6912e7710c838347ae178b4a&token_type=bearer";
	public static final String NO_CODE_VALUE = "/?error=Got back no code from github";
	public static final String TOKEN_URL_STRING = "https://github.com/login/oauth/access_token";
	public static final URI TOKEN_URI = get_TOKEN_URI();

	public static final String RIGHT_REDIRECT_URI = right_redirect_uri();

	public static final HttpServletRequest CALLBACK_REQUEST = 
			getReq();
	public static final HttpServletRequest CALLBACK_REQUEST_WITH_CODE = 
			callbackRequestContainsCodeParam();
	public static final HttpServletRequest LOGIN_SERVLET_REQUEST_WITH_USER_AND_NEXT = 
			getLoginServletRequestWitUserWithNext();
	public static final HttpServletRequest LOGIN_SERVLET_REQUEST_WITHOUT_USER_WITHOUT_NEXT = 
			getLoginServletRequest();
	public static final HttpServletRequest LOGIN_SERVLET_REQUEST_WITHOUT_USER_WITH_NEXT = 
			getLoginServletRequestWithoutUserWithNext();

	private static URI get_TOKEN_URI() {
		try {
			return new URI(TOKEN_URL_STRING);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}

	private static URI get_CALBACK_URI() {
		try {
			return new URI(CALLBACK_URI_STRING);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
	}
	private static HttpServletRequest callbackRequestContainsCodeParam() {
		HttpServletRequest req = getReq();
		Mockito.doReturn(CODE_TOKEN_VALUE).when(req).getParameter(LoginCallbackService.REQUESTPARAM_CODE);
		return req;
	}

	private static String right_redirect_uri() {
		try {
			return GITHUB_AUTH_URL +
					 "?client_id="+
					 CLIENT_ID_VALUE
					+ "&redirect_uri="
					+ URLEncoder.encode(CALLBACK_URI_STRING,"UTF-8");
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e);
		}
	}
	
	private static HttpServletRequest getLoginServletRequestWithoutUserWithNext() {
		HttpServletRequest req = getLoginServletRequest();
		Mockito.doReturn(NEXT_VALUE).when(req).getParameter("next");
		return req;		
	}

	private static HttpServletRequest getLoginServletRequestWitUserWithNext() {
		HttpServletRequest req = getLoginServletRequestWithoutUserWithNext();
		Mockito.doReturn(USER_NAME).when(req).getRemoteUser();
		return req;		
	}

	private static HttpServletRequest getLoginServletRequest() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		Mockito.doReturn(context).when(req).getServletContext();
		Mockito.doReturn(CLIENT_ID_VALUE).when(context).getInitParameter(LoginService.CLIENT_ID_PARAM_NAME);
		Mockito.doReturn(GITHUB_AUTH_URL).when(context).getInitParameter(LoginService.GITHUB_AUTH_URL_PARAM_NAME);
		Mockito.doReturn(CALLBACK_URI_STRING).when(context).getInitParameter(LoginService.CALLBACK_URI_PARAM_NAME);
		Mockito.doReturn(NO_NEXT_URL).when(context).getInitParameter(LoginService.NO_NEXT_URL_PARAM_NAME);

		return req;
	}

	private static HttpServletRequest getReq() {
		HttpServletRequest req = Mockito.mock(HttpServletRequest.class);
		ServletContext context = Mockito.mock(ServletContext.class);
		Mockito.doReturn(context).when(req).getServletContext();
		Mockito.doReturn(NO_CODE_VALUE).when(context).getInitParameter(LoginCallbackService.NO_CODE_PARAM_NAME);
		Mockito.doReturn(TOKEN_URL_STRING).when(context).getInitParameter(LoginCallbackService.TOKEN_URL_PARAM_NAME);
		return req;
	}
	
}
