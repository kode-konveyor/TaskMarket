package com.kodekonveyor.market.http;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HttpPostService {

	@Autowired
	FormEntityCreatorService formEntityCreatorService;
	@Autowired
	HttpPostFactory httpPostFactory;
	@Autowired
	HttpClientFactory httpClientFactory;
	
	public String call(URI url, Map<String,String> content) throws IOException, URISyntaxException {
		HttpPost post = httpPostFactory.call();
		post.setURI(url);
		CloseableHttpClient httpClient = httpClientFactory.call();
        UrlEncodedFormEntity htmlEntity = formEntityCreatorService.call(content);
		post.setEntity(htmlEntity);
        CloseableHttpResponse response = httpClient.execute(post);
        HttpEntity responseEntity = response.getEntity();
    	String returnValue = EntityUtils.toString(responseEntity);
		return returnValue;
	}


}
