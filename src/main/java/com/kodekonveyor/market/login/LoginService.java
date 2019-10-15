package com.kodekonveyor.market.login;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class LoginService implements ContextParameters {

	public void call(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		ServletContext context = req.getServletContext();
		String next = req.getParameter("next");
		if (null == next) {
			String noNextUrl = context.getInitParameter(NO_NEXT_URL_PARAM_NAME);
			resp.sendRedirect(noNextUrl);
			return;
		}
		String user = req.getRemoteUser();
		if(null != user) {
			resp.sendRedirect(next);
			return;
		}
		String clientId = context.getInitParameter(CLIENT_ID_PARAM_NAME);
		String githubAuthUrl = context.getInitParameter(GITHUB_AUTH_URL_PARAM_NAME);
		String callbackUri = context.getInitParameter(CALLBACK_URI_PARAM_NAME);
		String uri = githubAuthUrl +
				 "?client_id="+
				 clientId
				+ "&redirect_uri="
				+ URLEncoder.encode(callbackUri,"UTF-8");
		resp.sendRedirect(uri);
	}
	

}
