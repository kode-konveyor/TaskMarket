package com.kodekonveyor.market.github;

import static org.mockito.Mockito.doReturn;

import com.kodekonveyor.market.login.RegisterTestData;
import com.kodekonveyor.market.login.UserDTO;

public class GithubGetStubs {

  public static void behaviour(
      final GithubGetService githubGetService, final RegisterTestData testData
  ) {
    doReturn(testData.USER_DTO).when(githubGetService)
        .call("/users/" + testData.GITHUB_USER, UserDTO.class);
  }

}
