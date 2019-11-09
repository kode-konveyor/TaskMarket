package com.kodekonveyor.authentication;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.BeforeEach;
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

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@RunWith(MockitoJUnitRunner.class)
@TestedBehaviour("Have a message")
@TestedService("NotLoggedInException")
public class NotLoggedInExceptionTest {

  private static final String MESSAGE = "message";
  private NotLoggedInException exception;

  @BeforeEach
  public void setUp() {
    exception = new NotLoggedInException(MESSAGE);
  }

  @DisplayName("the message is the exception message")
  @Test
  public void test() {
    assertEquals(MESSAGE, exception.getMessage());
  }

}
