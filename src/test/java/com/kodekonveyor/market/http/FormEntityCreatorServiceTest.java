package com.kodekonveyor.market.http;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(JUnitPlatform.class)
public class FormEntityCreatorServiceTest {

	@InjectMocks
	FormEntityCreatorService formEntityCreatorService;

	@Test
	void returns_an_url_encoded_form_entity() throws IOException {
		UrlEncodedFormEntity result = formEntityCreatorService.call(HttpTestData.CONTENT);
		String resultString = IOUtils.toString(result.getContent(), StandardCharsets.UTF_8);
		assertEquals(HttpTestData.URLENCODED_RESPONSE, resultString);
	}
	
}
