package com.kodekonveyor.market.github;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import com.kodekonveyor.authentication.UserDTO;
import com.kodekonveyor.authentication.UserDTOTestData;
import com.kodekonveyor.authentication.UserEntityTestData;

public class GithubGetStubs {

  public static void behaviour(final GithubGetService githubGetService) {
    reset(githubGetService);
    doReturn(UserDTOTestData.get()).when(githubGetService)
        .call("/users/" + UserEntityTestData.LOGIN, UserDTO.class);
  }

}
