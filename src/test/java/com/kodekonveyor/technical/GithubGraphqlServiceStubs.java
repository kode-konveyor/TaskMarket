package com.kodekonveyor.technical;

import org.mockito.Mockito;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.kodekonveyor.market.project.PullRequestTestData;
import com.kodekonveyor.market.technical.GithubConstants;
import com.kodekonveyor.market.technical.GithubGraphqlService;

public class GithubGraphqlServiceStubs {

  private static final String query = String
      .format(
          GithubConstants.PULL_REQUEST_QUERY, GithubConstants.KODE_KONVEYOR,
          GithubConstants.TASK_MARKET, PullRequestTestData.ID
      );
  private static final DocumentContext SUCCESSFULL_REVIEW_AND_COMMIT_UNDER_THREE_DAYS =
      JsonPath.parse(
          TechnicalTestData.SUCCESSFULL_REVIEW_AND_COMMIT_UNDER_THREE_DAYS_ANSWER
      );
  private static final DocumentContext SUCCESSFULL_REVIEW_AND_COMMIT_EQUAL_TO_FOUR_DAYS =
      JsonPath.parse(
          TechnicalTestData.SUCCESSFULL_REVIEW_AND_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER
      );
  private static final DocumentContext SUCCESSFULL_REVIEW_AND_COMMIT_MORE_THAN_THREE_DAYS =
      JsonPath.parse(
          TechnicalTestData.SUCCESSFULL_REVIEW_AND_COMMIT_MORE_THAN_THREE_DAYS_ANSWER
      );
  private static final DocumentContext FAILURE_COMMIT_MORE_THAN_THREE_DAYS =
      JsonPath.parse(
          TechnicalTestData.FAILURE_COMMIT_MORE_THAN_THREE_DAYS_ANSWER
      );
  private static final DocumentContext FAILURE_COMMIT_EQUAL_TO_FOUR_DAYS =
      JsonPath.parse(
          TechnicalTestData.FAILURE_COMMIT_EQUAL_TO_FOUR_DAYS_ANSWER
      );
  private static final DocumentContext FAILURE_COMMIT_LESS_THAN_THREE_DAYS =
      JsonPath.parse(
          TechnicalTestData.FAILURE_COMMIT_LESS_THAN_THREE_DAYS_ANSWER
      );

  public static void successfullReviewAndCommitUnderThreeDays(
      final GithubGraphqlService githubGraphqlService
  ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(SUCCESSFULL_REVIEW_AND_COMMIT_UNDER_THREE_DAYS);
  }

  public static void successfullReviewAndCommitEqualToFourDays(
      final GithubGraphqlService githubGraphqlService
  ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(SUCCESSFULL_REVIEW_AND_COMMIT_EQUAL_TO_FOUR_DAYS);
  }

  public static void successfullReviewAndCommitMoreThanDays(
      final GithubGraphqlService githubGraphqlService
  ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(SUCCESSFULL_REVIEW_AND_COMMIT_MORE_THAN_THREE_DAYS);

  }

  public static void failureCommitMoreThanThreeDays(
      final GithubGraphqlService githubGraphqlService
  ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(FAILURE_COMMIT_MORE_THAN_THREE_DAYS);

  }

  public static void
      failureCommitEqualToFourDays(
          final GithubGraphqlService githubGraphqlService
      ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(FAILURE_COMMIT_EQUAL_TO_FOUR_DAYS);

  }

  public static void failureCommitEqualLessThanThreeDays(
      final GithubGraphqlService githubGraphqlService
  ) {
    Mockito.when(
        githubGraphqlService.call(query)
    )
        .thenReturn(FAILURE_COMMIT_LESS_THAN_THREE_DAYS);

  }

}
