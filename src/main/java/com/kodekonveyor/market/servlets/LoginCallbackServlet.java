package com.kodekonveyor.market.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.kodekonveyor.market.WebAppInitializer;
import com.kodekonveyor.market.login.LoginCallbackService;

@WebServlet("/callback")
public class LoginCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = -661561285565686194L;

	private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		LoginCallbackService loginCallbackService = WebAppInitializer.context.getBean(LoginCallbackService.class);

		logger.info("service:"+loginCallbackService);

		loginCallbackService.call(req,resp);
	}
}
