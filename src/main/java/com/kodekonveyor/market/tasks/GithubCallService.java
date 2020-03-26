package com.kodekonveyor.market.tasks;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.github.GithubConstants;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@ExcludeFromCodeCoverage("Github request response impementation")
@Service
public class GithubCallService {

  public JSONArray call(final String repoName) {

    final OkHttpClient client = new OkHttpClient().newBuilder()
        .build();
    final Request request = new Request.Builder()
        .url(
            GithubConstants.GITHUB_API_URL_BASE + GithubConstants.REPOS +
                repoName + GithubConstants.ISSUES
        )
        .method(GithubConstants.GET, null)
        .addHeader(
            GithubConstants.AUTHORIZATION, GithubConstants.ACCESS_TOKEN
        )
        .build();

    JSONArray array;

    try (Response response = client.newCall(request).execute()) {

      array = new JSONArray(response.body().string());

    } catch (IOException | JSONException exception) {

      throw new ResponseStatusException(
          HttpStatus.NOT_FOUND,
          GithubConstants.CANNOT_CONNECT_TO_GITHUB, exception
      );
    }

    return array;
  }
}
