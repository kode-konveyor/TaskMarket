package com.kodekonveyor.market.servlets;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.kodekonveyor.market.login.LoginCallbackService;

@WebServlet("/callback")
public class LoginCallbackServlet extends HttpServlet {
	private static final long serialVersionUID = -661561285565686194L;

	@Autowired
	LoginCallbackService loginCallbackService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
			loginCallbackService.call(req,resp);
	}
}
