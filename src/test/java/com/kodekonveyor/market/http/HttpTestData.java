package com.kodekonveyor.market.http;

import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.methods.HttpUriRequest;

import com.kodekonveyor.market.login.RegisterTestData;

public class HttpTestData {

	private static final String ACCESS_TOKEN = "the_access_token";
	public static final String REQUEST_PARAM = "code";
	public static final Map<String, String> CONTENT = get_CONTENT();
	public static final HttpUriRequest POST = null;
	public static final String SERVICE_REPLY =
			"access_token="
			+ ACCESS_TOKEN
			+ "&token_type=bearer";
	public static final String URLENCODED_RESPONSE =
			REQUEST_PARAM
			+ "="
			+ RegisterTestData.CODE_TOKEN_VALUE;

	private static Map<String, String> get_CONTENT() {
		Map<String, String> content = new HashMap<>();
		content.put(HttpTestData.REQUEST_PARAM, RegisterTestData.CODE_TOKEN_VALUE);
		return content;
	}

}
