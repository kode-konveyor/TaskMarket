package com.kodekonveyor.market.integrationtests;

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
    final String user = "user1";
    final String pass = "password1";
    final WebDriver driver = SeleniumTestHelper.getDriver();
    driver.get(
        "https://localhost:1443/market/member/login?next=/market/member/user"
    );
    final WebElement githubLoginButton =
        SeleniumTestHelper.waitFor(".auth0-lock-social-button-text");
    githubLoginButton.click();
    final WebElement loginField = SeleniumTestHelper.waitFor("#login_field");
    loginField.sendKeys(user);
    final WebElement passwordField = SeleniumTestHelper.waitFor("#password");
    passwordField.sendKeys(pass);
    final WebElement loginButton = SeleniumTestHelper.waitFor(".btn");
    loginButton.click();
    final WebElement name = SeleniumTestHelper.waitFor(".objectBox-string");
    final String myName = name.getText();
    assertEquals("\"" + user + "\"", myName);
  }

}
