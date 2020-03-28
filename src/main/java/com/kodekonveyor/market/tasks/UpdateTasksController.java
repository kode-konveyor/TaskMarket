package com.kodekonveyor.market.tasks;

import java.util.List;

import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kodekonveyor.authentication.AuthenticatedUserService;
import com.kodekonveyor.authentication.UserEntity;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.MarketConstants;
import com.kodekonveyor.market.UnauthorizedException;
import com.kodekonveyor.market.UrlMapConstants;
import com.kodekonveyor.market.github.GithubConstants;
import com.kodekonveyor.market.lead.CheckRoleUtil;
import com.kodekonveyor.market.project.ProjectEntity;
import com.kodekonveyor.market.project.ProjectEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntity;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.register.RegisterConstants;

@RestController
public class UpdateTasksController {

  private static TaskEntity get() {
    return new TaskEntity();
  }

  @Autowired
  AuthenticatedUserService authenticatedUserService; //NOPMD

  @Autowired
  GetRepositoryTasksService getRepositoryTasksService; //NOPMD

  @Autowired
  MarketUserEntityRepository marketUserEntityRepository; //NOPMD

  @Autowired
  ProjectEntityRepository projectEntityRepository; //NOPMD

  @Autowired
  TaskEntityRepository taskEntityRepository; //NOPMD

  @Autowired
  UserEntityRepository userEntityRepository; //NOPMD

  @PutMapping(UrlMapConstants.TASK_UPDATE_PATH)
  public void call() throws JSONException {
    final UserEntity user = authenticatedUserService.call();
    checkRole(user);

    updateTask();

  }

  private void checkRole(final UserEntity user) {
    if (!CheckRoleUtil.hasRole(user, MarketConstants.KODEKONVEYOR_CONTRACT))
      throw new UnauthorizedException(
          RegisterConstants.UNAUTHORIZED_NOT_ENOUGH_RIGHTS
      );
  }

  private void updateTask() throws JSONException {
    final List<TaskDTO> dtoList =
        getRepositoryTasksService.call(GithubConstants.REPO_NAME);

    final ProjectEntity project =
        projectEntityRepository.findByName(dtoList.get(0).getProject()).get(0);

    for (final TaskDTO taskDTO : dtoList) {
      final TaskEntity entity = get();
      entity.setGithubId(taskDTO.getGithubId());
      entity.setName(taskDTO.getName());
      entity.setStatus(taskDTO.getStatus());

      entity.setProject(project);

      final UserEntity user =
          userEntityRepository.findByLogin(taskDTO.getResponsible()).get(0);

      final MarketUserEntity responsible =
          marketUserEntityRepository.findByLogin(user).get(0);

      entity.setResponsible(responsible);

      taskEntityRepository.save(entity);

    }
  }
}
