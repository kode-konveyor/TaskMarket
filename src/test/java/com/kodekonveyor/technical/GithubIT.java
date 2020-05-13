package com.kodekonveyor.technical;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.json.JSONException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.jayway.jsonpath.DocumentContext;
import com.kodekonveyor.market.technical.GithubGraphqlService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("IntegrationTest")
@TestPropertySource(locations = "/test.properties")
public class GithubIT {

  @Autowired
  GithubGraphqlService githubGetGraphqlService;

  @Test
  @DisplayName(
    "returns the github Graphql query result as a JsonPath DocumentContext"
  )
  void test1() throws IOException, JSONException {

    final String query = String
        .format(
            TechnicalTestData.QUERY, TechnicalTestData.KODE_KONVEYOR,
            TechnicalTestData.TASK_MARKET
        );
    final DocumentContext context = githubGetGraphqlService.call(query);
    assertEquals(
        TechnicalTestData.REPOSITORY_ID,
        context.read(TechnicalTestData.REPOSITORY_ID_QUERY).toString()
    );
  }
}
