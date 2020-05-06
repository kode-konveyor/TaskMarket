package com.kodekonveyor.market.tasks;

import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;

@Service
public class UpdateTasksService {

  @Autowired
  private TaskEntityRepository taskEntityRepository;

  @Autowired
  private AuthenticatedUserService authenticatedUserService;

  @Autowired
  private ProjectEntityRepository projectEntityRepository;

  @Autowired
  private MarketUserEntityRepository marketUserEntityRepository;

  public void call(final TaskDTO taskDTO) {
    final ProjectEntity project = projectEntityRepository
        .findByName(MarketConstants.KODE_KONVEYOR_PROJECT_NAME).get();
    final UserEntity user = authenticatedUserService.call();
    if (!CheckRoleUtil.hasRole(user, project, MarketConstants.MANAGER))
      throw new UnauthorizedException(TaskConstants.NO_PROJECT_MANAGER_ROLE);

    final Optional<TaskEntity> taskEntityDB =
        taskEntityRepository.findByServiceAndBehaviour(
            taskDTO.getService(), taskDTO.getBehaviour()
        );

    final Optional<MarketUserEntity> marketUser =
        marketUserEntityRepository.findByUser(user);
    final MarketUserEntity marketUserEntity = marketUser.get();

    if (taskEntityDB.isPresent()) {
      final TaskEntity taskEntity = taskEntityDB.get();
      if (!taskDTO.getDescription().equals(taskEntity.getDescription())) {
        final String replacement = StringUtils.substringBetween(
            taskDTO.getDescription(), TaskConstants.START_DELIMETER,
            TaskConstants.END_DELIMETER
        );
        final String target = StringUtils.substringBetween(
            taskEntity.getDescription(), TaskConstants.START_DELIMETER,
            TaskConstants.END_DELIMETER
        );

        taskEntity.setDescription(
            taskEntity.getDescription().replace(target, replacement)
        );
        taskEntityRepository.save(taskEntity);

      }
    } else
      storage(taskDTO, marketUserEntity);
  }

  private void storage(
      final TaskDTO taskDTO, final MarketUserEntity marketUserEntity
  ) {
    final TaskEntity taskEntity = new TaskEntity();
    taskEntity.setBehaviour(taskDTO.getBehaviour());
    taskEntity.setService(taskDTO.getService());
    taskEntity.setDescription(taskDTO.getDescription());
    taskEntity.setGithubId(taskDTO.getGithubId());
    taskEntity.setMarketUser(marketUserEntity);
    taskEntityRepository.save(taskEntity);
  }

}
