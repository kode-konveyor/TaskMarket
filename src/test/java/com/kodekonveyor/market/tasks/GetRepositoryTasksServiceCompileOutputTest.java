package com.kodekonveyor.market.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.json.JSONException;
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
import com.kodekonveyor.market.register.MarketUserEntityRepositoryStubs;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("compile output")
@TestedService("GetRepositoryTasksService")
public class GetRepositoryTasksServiceCompileOutputTest
    extends GetRepositoryTasksServiceTestBase {

  @Test
  @DisplayName(
    "TaskEntites are returned successfully"
  )
  void test1() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        List.of(TaskEntityTestData.get(), TaskEntityTestData.getOtherTask()),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME)
    );
  }

  @Test
  @DisplayName(
    "Status of TaskEntity are returned successfully"
  )
  void test8() throws JSONException {
    MarketUserEntityRepositoryStubs.behaviour(marketUserEntityRepository);
    assertEquals(
        TaskEntityTestData.get().getStatus(),
        getRepositoryTasksService
            .call(GetRepositoryTasksServiceTestData.REPO_NAME).get(0).getStatus()
    );
  }
}
