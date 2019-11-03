package com.kodekonveyor.authentication;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

@Getter
public class UserTestData {

	public final String AUTH0ID = "github|424242@kode-konveyor.eu.auth0.com/";
	public final String BAD_AUTH0ID = "github|424241@kode-konveyor.eu.auth0.com/";
	public final String LOGIN = "424242";
	public final String BAD_LOGIN = "424241";
	public final UserEntity USER = getUSER();
	public final UserEntity BAD_USER_BEFORE_SAVE = getBAD_USER_BEFORE_SAVE();
	public final UserEntity BAD_USER = getBAD_USER();
	public final Object USER_LIST = getUSER_LIST();
	public final long USER_ID = 4242;
	public final long BAD_USER_ID = 4241;
	public final List<Object> EMPTY_LIST = new ArrayList<>();
	public final String NO_AUTHENTICATION = "No Authentication";
	public final String NO_CREDENTIAL = "No Credential";

	public Object getUSER_LIST() {
		return List.of(USER);
	}

	public UserEntity getUSER() {
		final UserEntity userEntity = new UserEntity();
		userEntity.setAuth0id(AUTH0ID);
		userEntity.setLogin(LOGIN);
		userEntity.setId(USER_ID);
		return userEntity;
	}

	public UserEntity getBAD_USER() {
		final UserEntity userEntity = getBAD_USER_BEFORE_SAVE();
		userEntity.setId(BAD_USER_ID);
		return userEntity;
	}

	public UserEntity getBAD_USER_BEFORE_SAVE() {
		final UserEntity userEntity = new UserEntity();
		userEntity.setAuth0id(BAD_AUTH0ID);
		userEntity.setLogin(BAD_LOGIN);
		return userEntity;
	}

}
