package com.kodekonveyor.market.http;

import org.apache.http.client.methods.HttpPost;
import org.springframework.stereotype.Service;

@Service
public class HttpPostFactory {

	public HttpPost call() {
		return new HttpPost();
	}

}
