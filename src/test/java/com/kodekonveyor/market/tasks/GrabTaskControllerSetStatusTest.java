package com.kodekonveyor.market.tasks;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("record grab date")
@TestedService("GrabTaskController")
public class GrabTaskControllerSetStatusTest
    extends GrabTaskControllerTestBase {

  @Test
  @DisplayName("record grab date for tasks")
  public void testGrabDate() {

    grabTaskController.call(TaskTestData.ID);

    final ArgumentCaptor<TaskEntity> entity =
        ArgumentCaptor.forClass(TaskEntity.class);
    verify(taskEntityRepository).save(entity.capture());
    assertNotNull(
        entity.getValue().getGrabDate()
    );

  }

}
