package com.kodekonveyor.market.github;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.authentication.UserDTO;
import com.kodekonveyor.authentication.UserTestData;

public class GithubGetStubs {

  public static void behaviour(
      final GithubGetService githubGetService, final UserTestData testData
  ) {
    doReturn(testData.USER_DTO).when(githubGetService)
        .call("/users/" + testData.GITHUB_ID, UserDTO.class);
  }

}
