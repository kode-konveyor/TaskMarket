package com.kodekonveyor.market.tasks;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import com.kodekonveyor.authentication.RoleEntityRepository;
import com.kodekonveyor.authentication.UserEntityRepository;
import com.kodekonveyor.market.project.MilestoneEntityRepository;
import com.kodekonveyor.market.register.MarketUserEntityRepository;
import com.kodekonveyor.market.technical.MessageUserOnDiscordService;

public class CheckUpforgrabTasksServiceTestBase {

  @InjectMocks
  CheckUpforgrabTasksService checkUpforgrabTasksService;

  @Mock
  MilestoneEntityRepository milestoneEntityRepository;

  @Mock
  MessageUserOnDiscordService messageUserOnDiscordService;

  @Mock
  MarketUserEntityRepository marketUserEntityRepository;

  @Mock
  UserEntityRepository userEntityRepository;

  @Mock
  RoleEntityRepository roleEntityRepository;

}
