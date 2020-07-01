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
import com.kodekonveyor.market.project.MilestoneEntityRepositoryStubs;
import com.kodekonveyor.market.project.ProjectDTOTestData;
import com.kodekonveyor.market.register.MarketUserEntityTestData;
import com.kodekonveyor.technical.TechnicalTestData;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("alert project manage")
@TestedService("CheckUpforgrabTasksService")
public class CheckUpforgrabTasksServiceAlertProjectManagerTest
    extends CheckUpforgrabTasksServiceTestBase {

  @Test
  @DisplayName(
    "If the up for grab tasks are below minimum for grab, all the project manager are alerted"
  )
  void test2() {
    MilestoneEntityRepositoryStubs
        .behaviour(milestoneEntityRepository);
    checkUpforgrabTasksService.call(ProjectDTOTestData.getMinimumForGab());
    Mockito.verify(messageUserOnDiscordService).call(
        TechnicalTestData.MESSAGE, MarketUserEntityTestData.getRoleManager()
    );
  }

  @Test
  @DisplayName(
    "If the up for grab tasks are more than minimum for grab, nothing is done"
  )
  void test() {
    MilestoneEntityRepositoryStubs
        .tasksMoreThanMinimumForGrab(milestoneEntityRepository);

    checkUpforgrabTasksService
        .call(ProjectDTOTestData.getMinimumForGab());
    Mockito.verify(messageUserOnDiscordService, Mockito.times(0)).call(
        TechnicalTestData.MESSAGE, MarketUserEntityTestData.getRoleManager()
    );

  }
}
