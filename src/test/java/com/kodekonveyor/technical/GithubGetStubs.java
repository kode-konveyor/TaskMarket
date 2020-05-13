package com.kodekonveyor.technical;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.reset;

import com.kodekonveyor.authentication.UserDTO;
import com.kodekonveyor.authentication.UserDTOTestData;
import com.kodekonveyor.authentication.UserTestData;
import com.kodekonveyor.market.technical.GithubGetService;

public class GithubGetStubs {

  private static final String USERS = "/users/";

  public static void behaviour(final GithubGetService githubGetService) {
    reset(githubGetService);
    doReturn(UserDTOTestData.get()).when(githubGetService)
        .call(USERS + UserTestData.LOGIN, UserDTO.class);
  }

}
