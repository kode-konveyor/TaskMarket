package com.kodekonveyor.market.integrationtests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

class SeleniumTestHelper {

  private static final int WAIT_INTERVAL = 20;
  private static final String HEADLESS = "--headless";
  private static FirefoxDriver driver;

  static {
    final FirefoxOptions firefoxOptions = new FirefoxOptions();
    firefoxOptions.addArguments(HEADLESS);
    driver = new FirefoxDriver(firefoxOptions);
  }

  public static FirefoxDriver getDriver() {
    return driver;
  }

  public static void close() {
    driver.close();
  }

  public static WebElement waitFor(final String cssSelector) {
    final WebDriverWait wait = new WebDriverWait(driver, WAIT_INTERVAL);
    return wait.until(
        ExpectedConditions.elementToBeClickable(
            By.cssSelector(cssSelector)
        )
    );
  }

}
