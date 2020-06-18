package com.kodekonveyor.market.tasks;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.market.project.ProjectConstants;

@Service
public class UpdateTasksService {

  @Autowired
  private TaskEntityRepository taskEntityRepository;

  public TaskEntity call(final TaskEntity task) {
    final List<TaskEntity> taskfromRepository =
        taskEntityRepository.findByServiceAndBehaviour(
            task.getService(), task.getBehaviour()
        );

    if (!taskfromRepository.isEmpty())
      return updateTask(task, taskfromRepository.get(0));

    return createTaskOnGithub(task);

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
      updateTask(final TaskEntity task, final TaskEntity taskfromRepository) {

    if (
      taskfromRepository.getDescription().contains(
          ProjectConstants.TASK_DESCRIPTION_START
      )
    ) {
      final String actaulDescription =
          taskfromRepository.getDescription().substring(
              taskfromRepository.getDescription().lastIndexOf(
                  ProjectConstants.TASK_DESCRIPTION_START
              ) + ProjectConstants.INDEX,
              taskfromRepository.getDescription()
                  .indexOf(ProjectConstants.TASK_DESCRIPTION_END)
          );

      if (actaulDescription.equals(task.getDescription()))
        return taskfromRepository;
      else {
        final String differenceInDescription = StringUtils
            .difference(
                actaulDescription, task.getDescription()
            );

        taskfromRepository.setDescription(

            taskfromRepository.getDescription() + ProjectConstants.DIFF +
                differenceInDescription
        );
        return taskfromRepository;
      }

    }

    if (!taskfromRepository.getDescription().equals(task.getDescription())) {
      final String differenceInDescription = StringUtils
          .difference(
              taskfromRepository.getDescription(), task.getDescription()
          );

      taskfromRepository.setDescription(
          ProjectConstants.TASK_DESCRIPTION_START +
              taskfromRepository.getDescription() +
              ProjectConstants.TASK_DESCRIPTION_END + ProjectConstants.DIFF +
              differenceInDescription
      );
      return taskfromRepository;
    }
    taskfromRepository.setDescription(
        ProjectConstants.TASK_DESCRIPTION_START +
            taskfromRepository.getDescription() +
            ProjectConstants.TASK_DESCRIPTION_END
    );
    return taskfromRepository;
  }
}
