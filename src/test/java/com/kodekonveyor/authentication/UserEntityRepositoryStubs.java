package com.kodekonveyor.authentication;

import static org.mockito.Mockito.*;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class UserEntityRepositoryStubs {

  public static void behaviour(
      final UserEntityRepository userRepository
  ) {
    reset(userRepository);
    doReturn(UserEntityTestData.list()).when(userRepository)
        .findByLogin(UserEntityTestData.LOGIN);
    doReturn(UserEntityTestData.listEmpty()).when(userRepository)
        .findByLogin(UserEntityTestData.LOGIN_BAD);

    final Answer<UserEntity> answer = new Answer<>() {

      @Override
      public UserEntity answer(final InvocationOnMock invocation) {
        final Object[] args = invocation.getArguments();
        final UserEntity user = (UserEntity) args[0];
        user.setId(UserEntityTestData.ID_BAD);
        return null;
      }
    };
    when(userRepository.save(UserEntityTestData.getIdUninitialized()))
        .then(answer);

  }
}
