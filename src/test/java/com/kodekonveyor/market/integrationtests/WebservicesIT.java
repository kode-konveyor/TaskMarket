package com.kodekonveyor.market.integrationtests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.MarketTestData;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.LeadDTO;
import com.kodekonveyor.market.lead.LeadTestData;
import com.kodekonveyor.market.register.MarketUserDTO;
import com.kodekonveyor.market.register.RegisterTestData;

import net.minidev.json.parser.ParseException;

@TestedBehaviour("roles")
@TestedService("ListLeadController")
@Testable
@Tag("IntegrationTest")
public class WebservicesIT {

  private ObjectMapper mapper;
  private UserTestData userTestData;

  private LeadTestData leadTestData;
  private MarketTestData marketTestData;
  private String urlBase;

  @BeforeEach
  public void setUp() {
    RegisterTestData registerTestData;
    mapper = new ObjectMapper();
    userTestData = new UserTestData();
    registerTestData = new RegisterTestData(userTestData);
    leadTestData = new LeadTestData(registerTestData);
    marketTestData = new MarketTestData(userTestData);
    urlBase = "http://127.0.0.1:8080/market";
  }

  @Test
  @DisplayName("A user can get its data at member/user")
  public void test1() throws IOException, ParseException {
    final URL url =
        new URL(urlBase + UrlMapConstants.SHOW_USER_PATH);
    final HttpURLConnection connection =
        (HttpURLConnection) url.openConnection();
    connection
        .setRequestProperty("OIDC_CLAIM_nickname", userTestData.GITHUB_ID);
    final MarketUserDTO marketUser = mapper
        .readValue((InputStream) connection.getContent(), MarketUserDTO.class);
    assertEquals(marketTestData.EMPTY_USER, marketUser);
  }

  @Test
  @DisplayName("/lead accepts application/x-www-form-urlencoded")
  public void test2() throws IOException, ParseException {
    final HttpURLConnection connection =
        openPostConnection(urlBase, UrlMapConstants.LEAD_PATH);
    connection.setRequestProperty(
        "Content-Type", "application/x-www-form-urlencoded"
    );
    final String text = marketTestData.LEAD_DTO_AS_URLENCODED;
    try (OutputStream outputStream = connection.getOutputStream()) {
      mapper.writeValue(outputStream, text);
    }
    final LeadDTO returnLead =
        mapper.readValue((InputStream) connection.getContent(), LeadDTO.class);
    assertEquals(leadTestData.LEAD, returnLead);
  }

  @Test
  @DisplayName("/lead accepts application/json")
  public void test3() throws IOException, ParseException {
    final HttpURLConnection connection =
        openPostConnection(urlBase, UrlMapConstants.LEAD_PATH);
    connection
        .setRequestProperty("Content-Type", "application/json; charset=utf-8");
    final LeadDTO lead = leadTestData.LEAD;
    try (OutputStream outputStream = connection.getOutputStream()) {
      mapper.writeValue(outputStream, lead);
    }
    final LeadDTO returnLead =
        mapper.readValue((InputStream) connection.getContent(), LeadDTO.class);
    assertEquals(leadTestData.LEAD, returnLead);
  }

  private HttpURLConnection
      openPostConnection(final String urlBase, final String path)
          throws MalformedURLException, IOException {
    final URL url =
        new URL(urlBase + path);
    final HttpURLConnection connection =
        (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setDoOutput(true);
    return connection;
  }

}
