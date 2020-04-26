package com.kodekonveyor.market.register;

import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.doReturn;

import java.util.HashSet;
import java.util.Optional;

import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.kodekonveyor.authentication.UserEntityTestData;

public class MarketUserEntityRepositoryStubs {

  public static void
      behaviour(
          final MarketUserEntityRepository marketUserEntityRepository
      ) {
    doReturn(Optional.of(MarketUserEntityTestData.get()))
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.get());
    doReturn(Optional.of(MarketUserEntityTestData.get()))
        .when(marketUserEntityRepository).findById(MarketUserTestData.ID);

    doReturn(Optional.of(MarketUserEntityTestData.getRoleCanBePaid()))
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getRoleCanbePaid());

    doReturn(
        Optional.of(MarketUserEntityTestData.getIsTerrmsAcceptedFalse())
    )
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getContractTermsNotAccepted());

    doReturn(
        Optional.of(MarketUserEntityTestData.getRoleKodeKonveyorContract())
    )
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getRoleKodekonveyorContract());

    doAnswer(new Answer<Void>() {

      @Override
      public Void answer(final InvocationOnMock invocation) throws Throwable {
        final Object[] arguments = invocation.getArguments();
        final MarketUserEntity user = (MarketUserEntity) arguments[0];
        if (user.getId() == null)
          user.setId(MarketUserTestData.ID_NOT_IN_DATABASE);
        user.setBill(new HashSet<>());
        user.setPaymentDetail(new HashSet<>());
        user.setProject(new HashSet<>());
        user.setPullRequest(new HashSet<>());

        return null;
      }
    }).

        when(
            marketUserEntityRepository
        ).save(Mockito.any(MarketUserEntity.class));

  }

  public static void contractTermsAccepted(
      final MarketUserEntityRepository marketUserEntityRepository,
      final MarketUserDTOTestData registerTestData
  ) {
    doReturn(Optional.of(MarketUserEntityTestData.getAcceptedContractuser()))
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getRoleKodekonveyorContract());
  }

  public static void
      contractTermsNotAccepted(
          final MarketUserEntityRepository marketUserEntityRepository,
          final MarketUserDTOTestData registerTestData
      ) {
    doReturn(Optional.of(MarketUserEntityTestData.getUnacceptedContractuser()))
        .when(marketUserEntityRepository)
        .findByLogin(UserEntityTestData.getRoleKodekonveyorContract());
  }
}
