package com.kodekonveyor.authentication;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

public class UserEntityRepositoryStubs {

	public static void behaviour(final UserEntityRepository userRepository, final UserTestData userTestData) {
		doReturn(userTestData.USER_LIST).when(userRepository).findByAuth0id(userTestData.AUTH0ID);
		doReturn(userTestData.EMPTY_LIST).when(userRepository).findByAuth0id(userTestData.BAD_AUTH0ID);

		final Answer<UserEntity> answer = new Answer<>() {

			@Override
			public UserEntity answer(final InvocationOnMock invocation) {
				final Object[] args = invocation.getArguments();
				final UserEntity user = (UserEntity) args[0];
				user.setId(userTestData.BAD_USER_ID);
				return null;
			}
		};
		when(userRepository.save(userTestData.BAD_USER_BEFORE_SAVE)).then(answer);

	}
}
