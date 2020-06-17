package com.kodekonveyor.market.project;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Generated;

import com.kodekonveyor.authentication.RoleEntityTestData;
import com.kodekonveyor.market.register.MarketUserTestData;

@Generated("by zenta-tools")
public class ProjectEntityTestData {

  public final static ProjectEntity get() {
    final ProjectEntity projectEntity = new ProjectEntity();
    projectEntity.setId(ProjectTestData.ID);
    projectEntity.setRole(Set.of(RoleEntityTestData.get()));
    projectEntity.setMilestone(Set.of(MilestoneEntityTestData.get()));
    projectEntity.setName(ProjectTestData.NAME);
    projectEntity.setBudgetInCents(ProjectTestData.BUDGET_IN_CENTS);
    projectEntity.setIsPublic(ProjectTestData.IS_PUBLIC);

    return projectEntity;
  }

  public static ProjectEntity getNullIdWithUrlAndPullRequest() {
    final ProjectEntity projectEntity = getUrlAndPullRequest();
    projectEntity.setId(null);
    projectEntity.setBudgetInCents(0L);
    return projectEntity;
  }

  public static ProjectEntity getNullId() {
    final ProjectEntity projectEntity = get();
    projectEntity.setId(null);
    projectEntity.setBudgetInCents(0L);
    return projectEntity;
  }

  public static ProjectEntity getNullIdWithoutMilestone() {
    final ProjectEntity projectEntity = getUrlAndPullRequest();
    projectEntity.setId(null);
    projectEntity.setBudgetInCents(0L);
    projectEntity.setMilestone(new HashSet<>());
    return projectEntity;
  }

  public static Object getNameKodeKonveyor() {
    final ProjectEntity projectEntity = get();
    projectEntity.setId(ProjectTestData.ID_KODE_KONVEYOR);
    projectEntity.setName(ProjectTestData.NAME_KODE_KONVEYOR);
    projectEntity.setBudgetInCents(0L);
    projectEntity.setMilestone(new HashSet<>());
    projectEntity.setRole(
        Set.of(
            RoleEntityTestData.getNameCanbepaid(),
            RoleEntityTestData.getNameProjectManager(),
            RoleEntityTestData.getRoleKodekonveyorContract(),
            RoleEntityTestData.getRoleSales()
        )
    );
    return projectEntity;
  };

  public static ProjectEntity getUrlAndPullRequest() {
    final ProjectEntity projectEntity = get();
    projectEntity.setUrl(ProjectTestData.URL);
    projectEntity.setDescription(ProjectTestData.DESCRIPTION);
    projectEntity.setProjectId(ProjectTestData.PROJECT_ID);
    projectEntity.setPullRequest(Set.of(PullrequestEntityTestData.get()));
    return projectEntity;
  }

  public final static ProjectEntity getManagerRole() {
    final ProjectEntity projectEntity = get();
    projectEntity.setId(ProjectTestData.ID_BUDGET);
    projectEntity.setRole(Set.of(RoleEntityTestData.getNameProjectManager()));
    projectEntity.setBudgetInCents(ProjectTestData.BUDGET_IN_LESSER_AMOUNT);
    projectEntity.setPullRequest(Set.of(PullrequestEntityTestData.get()));
    return projectEntity;
  }

  public static ProjectEntity getAddFunds() {
    final ProjectEntity projectEntity = getUrlAndPullRequest();
    projectEntity.setId(ProjectTestData.ID_ADD_FUNDS);
    return projectEntity;
  };

  public static ProjectEntity getUpdatedAddFunds() {
    final ProjectEntity projectEntity = getAddFunds();
    projectEntity.setBudgetInCents(
        projectEntity.getBudgetInCents() + MarketUserTestData.LESS_BALANCE
    );
    return projectEntity;
  };

  public static ProjectEntity getPullRequest() {
    final ProjectEntity projectEntity = get();
    projectEntity.setPullRequest(Set.of(PullrequestEntityTestData.get()));
    return projectEntity;
  }
}
