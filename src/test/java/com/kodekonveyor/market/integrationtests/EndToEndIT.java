package com.kodekonveyor.market.integrationtests;

import static com.kodekonveyor.market.integrationtests.EndToEndTestData.*; //NOPMD it's not unused import
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.kodekonveyor.annotations.TestedBehaviour;
import com.kodekonveyor.annotations.TestedService;

@TestedBehaviour("roles")
@TestedService("ListLeadController")
@Testable
@Tag("IntegrationTest")
public class EndToEndIT {

  @Test
  public void seleniumTest() {
    final WebDriver driver = SeleniumTestHelper.getDriver();
    driver.get(
        URL
    );
    final WebElement githubLoginButton =
        SeleniumTestHelper.waitFor(LOGIN_BUTTON_SELECTOR);
    githubLoginButton.click();
    final WebElement loginField =
        SeleniumTestHelper.waitFor(LOGIN_FIELD_SELECTOR);
    loginField.sendKeys(USER);
    final WebElement passwordField =
        SeleniumTestHelper.waitFor(PASSWORD_SELECTOR);
    passwordField.sendKeys(PASSWORD);
    final WebElement loginButton = SeleniumTestHelper.waitFor(BUTTON_SELECTOR);
    loginButton.click();
    final WebElement name = SeleniumTestHelper.waitFor(OBJECT_BOX_SELECTOR);
    final String myName = name.getText();
    assertEquals(String.format(QUOTES_TEMPLATE, USER), myName);
  }

}
