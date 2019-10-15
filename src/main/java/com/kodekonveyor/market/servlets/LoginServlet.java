package com.kodekonveyor.market.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.XmlWebApplicationContext;

import com.kodekonveyor.market.login.LoginService;

@Service
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 8390922491404037095L;
	private final Logger logger = LoggerFactory.getLogger(LoginServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		LoginService loginService;
		
		try (XmlWebApplicationContext ctx = new XmlWebApplicationContext()) {
			ctx.setConfigLocations("applicationContext.xml");
			loginService = ctx.getBean(LoginService.class);
			}
		logger.info("service:"+loginService);
		loginService.call(req,resp);
	}
}