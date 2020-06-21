package com.kodekonveyor.technical;

import com.kodekonveyor.exception.ThrowableTester;
import com.kodekonveyor.market.technical.GithubGetService;
import org.junit.Assert;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

import static com.kodekonveyor.technical.GitConstantsTestData.EXPECTED_ERROR_MSG_WHEN_4XX_ERROR;
import static com.kodekonveyor.technical.GitConstantsTestData.EXPECTED_ERROR_MSG_WHN_INVALID_TYPE;
import static com.kodekonveyor.technical.GitConstantsTestData.EXPECTED_NODE_ID;
import static com.kodekonveyor.technical.GitConstantsTestData.NODE_ID;
import static com.kodekonveyor.technical.GitConstantsTestData.TEST_GET_GITHUB_REPO_COMMAND;
import static com.kodekonveyor.technical.GitConstantsTestData.TEST_INVALID_GITHUB_REPO_COMMAND;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Tag("IntegrationTest")
@TestPropertySource(locations = "/test.properties")
public class GithubGetServiceIT {

  @Autowired
  GithubGetService githubGetService;

  @Test
  @DisplayName(
    "Repository details are successfully returned with get call to fetch repository details from github."
  )
  void test1() {
    Map<String, Object> call = githubGetService.call(TEST_GET_GITHUB_REPO_COMMAND, Map.class);

    Assert.assertEquals(call.get(NODE_ID), EXPECTED_NODE_ID);
  }

  @Test
  @DisplayName(
          "When wrong valuetype is passed, error message is thrown."
  )
  void test2() {
    ThrowableTester.assertThrows(() -> githubGetService.call(TEST_GET_GITHUB_REPO_COMMAND, List.class))
    .assertException(ResponseStatusException.class);

  }

  @Test
  @DisplayName(
          "When wrong valuetype is passed, error message thrown is : cannot connect to github"
  )
  void test3() {
    ThrowableTester.assertThrows(() -> githubGetService.call(TEST_GET_GITHUB_REPO_COMMAND, List.class))
            .assertMessageContains(EXPECTED_ERROR_MSG_WHN_INVALID_TYPE);

  }

  @Test
  @DisplayName(
          "When github call is not 2XX successful, exception is thrown."
  )
  void test4() {
    ThrowableTester.assertThrows(() -> githubGetService.call(TEST_INVALID_GITHUB_REPO_COMMAND, Map.class))
            .assertException(ResponseStatusException.class);

  }

  @Test
  @DisplayName(
          "When github call is not 2XX successful, error message is thrown with status."
  )
  void test5() {
    ThrowableTester.assertThrows(() -> githubGetService.call(TEST_INVALID_GITHUB_REPO_COMMAND, Map.class))
            .assertMessageContains(EXPECTED_ERROR_MSG_WHEN_4XX_ERROR);

  }

}
