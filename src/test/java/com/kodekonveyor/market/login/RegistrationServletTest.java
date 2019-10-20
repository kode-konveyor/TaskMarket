package com.kodekonveyor.market.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.modelmapper.ModelMapper;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.market.LoggerService;
import com.kodekonveyor.market.NotLoggedInException;
import com.kodekonveyor.market.TestHelper;
import com.kodekonveyor.market.github.GithubGetService;
import com.kodekonveyor.market.github.GithubGetStubs;
import com.kodekonveyor.market.servlets.RegistrationServlet;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
public class RegistrationServletTest {

  @InjectMocks
  RegistrationServlet registrationServlet;

  @Mock
  private UserRepository userRepository;

  @Mock
  GithubGetService githubGetService;

  @Mock
  LoggerService loggerService;

  @Mock
  ModelMapper modelMapper;

  @Captor
  ArgumentCaptor<String> captor;

  private RegisterTestData testData;

  @BeforeEach
  private void setUp() {
    testData = new RegisterTestData();
    GithubGetStubs.behaviour(githubGetService, testData);
    doReturn(testData.USER_DTO).when(modelMapper)
        .map(testData.USER_ENTITY, UserDTO.class);
  }

  @Test
  @TestedBehaviour("logs 'register'")
  void test() {
    registrationServlet
        .call(testData.AUTHENTICATED_REQUEST, testData.USER_DTO);
    verify(loggerService, atLeast(1)).call(captor.capture());
    TestHelper
        .assertContains(testData.REGISTER_LOG, captor.getAllValues());
  }

  @Test
  @TestedBehaviour("redirects to /market/members/user if no authenticated user")
  void test2() {
    final String uri = assertThrows(
        NotLoggedInException.class,
        () -> registrationServlet
            .call(testData.UNAUTHENTICATED_REQUEST, testData.USER_DTO)
    ).getRedirectUri();
    assertEquals("/market/member/user", uri);
  }

}
