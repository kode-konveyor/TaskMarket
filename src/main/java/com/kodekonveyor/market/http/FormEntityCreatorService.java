package com.kodekonveyor.market.http;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Service;

@Service
public class FormEntityCreatorService {

	public UrlEncodedFormEntity call(Map<String, String> content) throws UnsupportedEncodingException {
		List<NameValuePair> urlParameters = new ArrayList<>();
        for(  Entry<String, String> entry : content.entrySet()) {
        	urlParameters.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        UrlEncodedFormEntity htmlEntity = new UrlEncodedFormEntity(urlParameters);
		return htmlEntity;
	}

}
