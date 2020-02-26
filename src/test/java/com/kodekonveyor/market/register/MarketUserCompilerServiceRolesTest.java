package com.kodekonveyor.market.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

import java.util.Set;

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
import com.kodekonveyor.authentication.AuthenticatedUserStubs;
import com.kodekonveyor.authentication.RoleEntityTestData;
import com.kodekonveyor.authentication.UserEntityTestData;
import com.kodekonveyor.exception.ThrowableTester;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("roles")
@TestedService("MarketUserCompilerService")
public class MarketUserCompilerServiceRolesTest
    extends MarketUserCompilerServiceTestBase {

  MarketUserDTOTestData registerTestData;

  @Test
  @DisplayName(
    "Users with up to date contract information should be added to the can_be_payed role"
  )
  void test() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    MarketUserStubs.upTodatedUser(marketUserEntityRepository, registerTestData);
    marketUserCompilerService.call(UserEntityTestData.ID);

    verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getCanBePayed());
  }

  @Test
  @DisplayName(
    "whenever the information becomes outdated, they should be removed from that role"
  )
  void test1() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    MarketUserStubs.outdatedUser(marketUserEntityRepository, registerTestData);
    marketUserCompilerService.call(UserEntityTestData.ID);
    verify(marketUserEntityRepository)
        .save(MarketUserEntityTestData.getCanBePayedRemoved());
  }

  @Test
  @DisplayName(
    "If user information is valid and user in can_be_payed role, no exception is thrown"
  )
  void test2() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    MarketUserStubs.upTodatedUser(marketUserEntityRepository, registerTestData);
    ThrowableTester.assertNoException(
        () -> marketUserCompilerService.call(UserEntityTestData.ID)
    );
  }

  @Test
  @DisplayName(
    "Check can_be_payed role is assigned for user with up to date information"
  )
  void test3() {
    AuthenticatedUserStubs.authenticated(authenticatedUserService);
    MarketUserStubs.upTodatedUser(marketUserEntityRepository, registerTestData);
    marketUserCompilerService.call(UserEntityTestData.ID);
    assertEquals(
        Set.of(RoleEntityTestData.getNameCanBePayed()), MarketUserEntityTestData.getCanBePayed().getLogin().getRoles()

    );
  }

  @Test
  @DisplayName(
    "Check can_be-payed role is removed for user with outdated information"
  )
  void test4() {
    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
    MarketUserStubs.outdatedUser(marketUserEntityRepository, registerTestData);
    marketUserCompilerService.call(UserEntityTestData.ID);
    assertEquals(
        Set.of(),
        MarketUserEntityTestData.getCanBePayedRemoved().getLogin().getRoles()

    );
  }

  //  @Test
  //  @DisplayName(
  //    "Check can_be_payed role is assigned for user with up to date information"
  //  )
  //  void test5() {
  //    AuthenticatedUserStubs.canBePayed(authenticatedUserService);
  //    MarketUserStubs.outdatedUser(marketUserEntityRepository, registerTestData);
  //    marketUserCompilerService.call(UserEntityTestData.ID);
  //    ThrowableTester.assertNoException(
  //        () -> marketUserCompilerService.call(UserEntityTestData.ID)
  //    );
  //
  //  }

  //  @Test
  //  @DisplayName(
  //    "Check can_be-payed role is removed for user with outdated information"
  //  )
  //  void test6() {
  //
  //    MarketUserStubs.outdatedUser(marketUserEntityRepository, registerTestData);
  //
  //    //    assertSame(
  //        MarketUserEntityTestData.getCanBePayedRemoved().getLogin()
  //            .getRoles(),
  //        Set.of()
  //    );

  //  }
}
