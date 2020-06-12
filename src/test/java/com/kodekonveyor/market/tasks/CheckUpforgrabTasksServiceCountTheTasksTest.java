package com.kodekonveyor.market.tasks;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;
import com.kodekonveyor.authentication.RoleEntityRepositoryStubs;
import com.kodekonveyor.authentication.UserEntityRepositoryStubs;
import com.kodekonveyor.market.project.MilestoneEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectDTOTestData;
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;
import com.kodekonveyor.market.register.MarketUserEntityTestData;
import com.kodekonveyor.technical.TechnicalTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("count the tasks")
@TestedService("CheckUpforgrabTasksService")

public class CheckUpforgrabTasksServiceCountTheTasksTest
    extends CheckUpforgrabTasksServiceTestBase {

  @Test
  @DisplayName(
    "Do nothing, if the count of up for grab tasks in the project is more than minimum for grab"
  )
  void test() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    MilestoneEntityRepositoryStubs
        .tasksMoreThanMinimumForGrab(milestoneEntityRepository);

    checkUpforgrabTasksService
        .call(ProjectDTOTestData.getMinimumForGab());
    Mockito.verify(messageUserOnDiscordService, Mockito.times(0)).call(
        TechnicalTestData.MESSAGE, MarketUserEntityTestData.getRoleManager()
    );

  }

  @Test
  @DisplayName(
    "If the count of up for grab tasks in the project is equal to minimum for grab, message is sent to project manager on discord"
  )
  void test1() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    MilestoneEntityRepositoryStubs
        .tasksEqualToMinimumForGrab(milestoneEntityRepository);

    checkUpforgrabTasksService
        .call(ProjectDTOTestData.getMinimumForGab());
    Mockito.verify(messageUserOnDiscordService).call(
        TechnicalTestData.TASKS_EQUAL_MESSAGE,
        MarketUserEntityTestData.getRoleManager()
    );

  }

  @Test
  @DisplayName(
    "If the count of up for grab tasks in the project is less than minimum for grab, message is sent to project manager on discord"
  )
  void test2() {
    RoleEntityRepositoryStubs.behaviour(roleEntityRepository);
    UserEntityRepositoryStubs.behaviour(userEntityRepository);
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    MilestoneEntityRepositoryStubs
        .behaviour(milestoneEntityRepository);
    checkUpforgrabTasksService.call(ProjectDTOTestData.getMinimumForGab());
    Mockito.verify(messageUserOnDiscordService).call(
        TechnicalTestData.MESSAGE, MarketUserEntityTestData.getRoleManager()
    );

  }

}
