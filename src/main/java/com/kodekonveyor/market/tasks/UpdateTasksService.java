package com.kodekonveyor.market.tasks;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.project.ProjectConstants;

@Service
public class UpdateTasksService {

  @Autowired
  private TaskEntityRepository taskEntityRepository;

  public TaskEntity call(final TaskEntity inputTask) {
    final Optional<TaskEntity> storedTask =
        taskEntityRepository.findByServiceAndBehaviour(
            inputTask.getService(), inputTask.getBehaviour()
        );

    if (!storedTask.isEmpty())
      return updateTask(inputTask, storedTask.get());

    return createTaskOnGithub(inputTask);

  }

  private TaskEntity createTaskOnGithub(final TaskEntity task) {
    task.setDescription(
        ProjectConstants.TASK_DESCRIPTION_START + task.getDescription() +
            ProjectConstants.TASK_DESCRIPTION_END
    );
    task.setStatus(TaskStatusEnum.NOT_IN_MODEL);

    return task;
  }

  private TaskEntity
      updateTask(final TaskEntity inputTask, final TaskEntity storedTask) {

    if (
      storedTask.getDescription().contains(
          ProjectConstants.TASK_DESCRIPTION_START
      ) || storedTask.getDescription().contains(
          ProjectConstants.TASK_DESCRIPTION_END
      )
    ) {
      final String[] descriptionArray =
          storedTask.getDescription().split(
              ProjectConstants.TASK_DESCRIPTION_START + ProjectConstants.PIPE +
                  ProjectConstants.TASK_DESCRIPTION_END
          );

      final StringBuffer desciptionBuffer = new StringBuffer();

      for (final String string : descriptionArray)
        desciptionBuffer.append(string);

      final String actualDescription = desciptionBuffer.toString();

      if (actualDescription.equals(inputTask.getDescription()))
        return storedTask;

      final String differenceInDescription = StringUtils
          .difference(
              actualDescription, inputTask.getDescription()
          );

      updateDescription(storedTask, actualDescription, differenceInDescription);
      return storedTask;
    }

    final String differenceInDescription = StringUtils
        .difference(
            storedTask.getDescription(), inputTask.getDescription()
        );
    updateDescription(
        storedTask, storedTask.getDescription(), differenceInDescription
    );
    return storedTask;
  }

  private void updateDescription(
      final TaskEntity task, final String actualDescription,
      final String differenceInDescription
  ) {
    task.setDescription(
        ProjectConstants.TASK_DESCRIPTION_START +
            actualDescription +
            ProjectConstants.TASK_DESCRIPTION_END + ProjectConstants.DIFF +
            differenceInDescription
    );
  }
}
