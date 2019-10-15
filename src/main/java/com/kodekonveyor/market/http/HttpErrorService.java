package com.kodekonveyor.market.http;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

@Service
public class HttpErrorService {

	public void call(Exception exception, HttpServletRequest req, HttpServletResponse resp, String message) {
		
	}

}
