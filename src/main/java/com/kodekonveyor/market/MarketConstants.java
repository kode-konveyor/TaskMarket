package com.kodekonveyor.market;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MarketConstants {

  public static final String CALL = "call";

  public static final String CAN_BE_PAID_ROLE = "can_be_payed";

  public static final String CHANNEL_NAME_PAYPAL = "paypal";

  public static final String CHANNEL_NAME_SEPA = "sepa";

  public static final String CHANNEL_NAME_TRANSFERWISE = "transferwise";

  public static final String EMAIL_NULL_EXCEPTION = "Email cannot be null";

  public static final String FIRST_NAME_NULL_EXCEPTION =
      "First name cannot be null";

  public static final String GITHUB_CALL = "githubCall";

  public static final String GITHUB_ERROR = "cannot connect to github";

  public static final String IN_ADD_TO_ROLE = "in add to role";
  public static final String IN_PAYMENT_UPDATE = "in payment update";

  public static final String INTEREST_NULL_EXCEPTION =
      "Interest cannot be null";

  public static final String INTERNAL_ERROR = "internal error";

  public static final String INVALID_EMAIL_FORMAT_EXCEPTION =
      "Please enter valid email address (e.g. abc@def.com)";

  public static final String INVALID_FIRST_NAME_FORMAT_EXCEPTION =
      "Please enter valid first name";

  public final static String INVALID_PAYMENT_DETAILS =
      "Please enter valid payment details";

  public static final String KODEKONVEYOR_SALES_ROLE = "kodekonveyor_sales";

  public static final String LEAD_RECEIVED = "lead.received";

  public static final Logger logger =
      LoggerFactory.getLogger(LoggerService.class);
  public static final String LOGIN = "login";
  public static final ObjectMapper mapper = new ObjectMapper();

  public static final Map<String, LogMarker> markers =
      new ConcurrentHashMap<>();

  public static final String NO_AUTHENTICATION = "No Authentication";

  public static final String NO_CREDENTIAL = "No Credential";

  public static final String PAYMMENT_CHANNEL_SEPARATOR = ":";

  public static final String REGEX_PAYPAL = "^[A-Za-z0-9+_.-]+@(.+)$";

  public static final String REGEX_SEPA =
      "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}";

  public static final String REGEX_TRANSFERWISE =
      "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b";

  public static final String THIS_SHOULD_NOT_HAPPEN = "This should not happen";

  public static final String UNATHORIZED_EXCEPTION = "Unauthorized";

  public static final String URI = "https://api.github.com";

}
