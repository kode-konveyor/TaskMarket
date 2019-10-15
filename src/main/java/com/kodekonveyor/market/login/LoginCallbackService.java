package com.kodekonveyor.market.login;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.kodekonveyor.market.http.HttpErrorService;
import com.kodekonveyor.market.http.HttpPostService;

public class LoginCallbackService {
	
	public static final String REQUESTPARAM_NEXT = "next";

	@Autowired
	HttpPostService httpPostService;
	@Autowired
	HttpErrorService httpErrorService;
	
	public static final String NO_CODE_PARAM_NAME = "noCode";
	public static final String TOKEN_URL_PARAM_NAME = "tokenUrl";
	public static final String CODE_REQ_PARAM_NAME = "code";


	public void call(HttpServletRequest req, HttpServletResponse resp) {
		ServletContext context = req.getServletContext();
		String next = req.getParameter(REQUESTPARAM_NEXT);
		if (null == next) {
			String noCodeUrl = context.getInitParameter(NO_CODE_PARAM_NAME);
			try {
				resp.sendRedirect(noCodeUrl);
			} catch (IOException e) {
				httpErrorService.call(e,req,resp,"Bad Url for "+ NO_CODE_PARAM_NAME+":"+noCodeUrl);
			}
			return;
		}
		String tokenUrl = context.getInitParameter(TOKEN_URL_PARAM_NAME);
		String content = next;
		HashMap<String,String> a = new HashMap<>();
		a.put(CODE_REQ_PARAM_NAME, content);
		try {
			httpPostService.call(new URI(tokenUrl), a);
		} catch (IOException | URISyntaxException e) {
			httpErrorService.call(e,req,resp,"Bad Url for "+ TOKEN_URL_PARAM_NAME+":"+tokenUrl);
		}

	}

}
