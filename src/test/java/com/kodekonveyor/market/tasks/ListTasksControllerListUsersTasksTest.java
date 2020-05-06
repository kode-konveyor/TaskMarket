package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.fail;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.AuthenticatedUserServiceStubs;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("List user's tasks")
@TestedService("ListTasksController")
public class ListTasksControllerListUsersTasksTest
    extends ListTasksControllerTestBase {

  @Override
  @BeforeEach
  void setUp() {
    super.setUp();
    MarketUserEntityRepositoryStubs
        .behaviour(marketUserEntityRepository);
    AuthenticatedUserServiceStubs.authenticated(authenticatedUserService);
  }

  @Test
  @DisplayName("List all In Progress task for a user")
  public void test() {
    final long issueId = TaskEntityTestData.ISSUE_ID_INPROGRESS;
    assertListContainsId(issueId);
  }

  @Test
  @DisplayName("List all Up For Grab task for closed projects for a user")
  public void test1() {
    final long issueId = TaskEntityTestData.ISSUE_ID_UPFORGRAB_CLOSED;
    assertListContainsId(issueId);
  }

  @Test
  @DisplayName("List all Up For Grab task for open projects")
  public void test2() {
    final long issueId = TaskEntityTestData.ISSUE_ID_UPFORGRAB_OPEN;
    assertListContainsId(issueId);
  }

  @Test
  @DisplayName("List all closed task for a user")
  public void test3() {
    final long issueId = TaskEntityTestData.ISSUE_ID_CLOSED;
    assertListContainsId(issueId);
  }

  private void assertListContainsId(final long issueId) {
    final List<TaskDTO> results = listTasksController.call();
    for (final TaskDTO result : results)
      if (result.getGithubId().equals(issueId))
        return;
    fail();
  }

}
