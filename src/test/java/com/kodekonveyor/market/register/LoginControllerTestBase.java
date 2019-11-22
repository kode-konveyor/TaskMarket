package com.kodekonveyor.market.register;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;

import com.kodekonveyor.authentication.UserTestData;

public class LoginControllerTestBase {

  @InjectMocks
  LoginController loginController;
  UserTestData userTestData;
  RegisterTestData registerTestData;

  @BeforeEach
  void setUp() {
    userTestData = new UserTestData();
    registerTestData = new RegisterTestData(userTestData);
  }
}
