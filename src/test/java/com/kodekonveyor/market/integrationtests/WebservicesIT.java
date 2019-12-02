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
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.market.MarketUserDTOTestData;
import com.kodekonveyor.market.RegisterInterestControllerTestData;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.lead.LeadDTO;
import com.kodekonveyor.market.lead.LeadDTOTestData;
import com.kodekonveyor.market.register.MarketUserDTO;

import net.minidev.json.parser.ParseException;

@TestedBehaviour("roles")
@TestedService("ListLeadController")
@Testable
@Tag("IntegrationTest")
public class WebservicesIT {

  private ObjectMapper mapper;

  private String urlBase;

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

  @BeforeEach
  public void setUp() {
    mapper = new ObjectMapper();
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
        .setRequestProperty("OIDC_CLAIM_nickname", UserEntityTestData.LOGIN);
    final MarketUserDTO marketUser = mapper
        .readValue((InputStream) connection.getContent(), MarketUserDTO.class);
    assertEquals(MarketUserDTOTestData.get(), marketUser);
  }

  @Test
  @DisplayName("/lead accepts application/x-www-form-urlencoded")
  public void test2() throws IOException, ParseException {
    final HttpURLConnection connection =
        openPostConnection(urlBase, UrlMapConstants.LEAD_PATH);
    connection.setRequestProperty(
        "Content-Type", "application/x-www-form-urlencoded"
    );
    final String text =
        RegisterInterestControllerTestData.LEAD_DTO_AS_URLENCODED;
    try (OutputStream outputStream = connection.getOutputStream()) {
      mapper.writeValue(outputStream, text);
    }
    final LeadDTO returnLead =
        mapper.readValue((InputStream) connection.getContent(), LeadDTO.class);
    assertEquals(LeadDTOTestData.get(), returnLead);
  }

  @Test
  @DisplayName("/lead accepts application/json")
  public void test3() throws IOException, ParseException {
    final HttpURLConnection connection =
        openPostConnection(urlBase, UrlMapConstants.LEAD_PATH);
    connection
        .setRequestProperty("Content-Type", "application/json; charset=utf-8");
    final LeadDTO lead = LeadDTOTestData.get();
    try (OutputStream outputStream = connection.getOutputStream()) {
      mapper.writeValue(outputStream, lead);
    }
    final LeadDTO returnLead =
        mapper.readValue((InputStream) connection.getContent(), LeadDTO.class);
    assertEquals(LeadDTOTestData.get(), returnLead);
  }

}
