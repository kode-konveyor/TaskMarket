package com.kodekonveyor.market;

public class MarketConstants {

  public static final String CAN_BE_PAID_ROLE = "can_be_payed";

  public static final String CHANNEL_NAME_PAYPAL = "paypal";

  public static final String CHANNEL_NAME_SEPA = "sepa";

  public static final String CHANNEL_NAME_TRANSFERWISE = "transferwise";

  public static final String EMAIL_NULL_EXCEPTION = "Email cannot be null";

  public static final String FIRST_NAME_NULL_EXCEPTION =
      "First name cannot be null";

  public static final String INTEREST_NULL_EXCEPTION =
      "Interest cannot be null";

  public static final String INVALID_EMAIL_FORMAT_EXCEPTION =
      "Please enter valid email address (e.g. abc@def.com)";

  public static final String INVALID_FIRST_NAME_FORMAT_EXCEPTION =
      "Please enter valid first name";

  public final static String INVALID_PAYMENT_DETAILS =
      "Please enter valid payment details";

  public static final String KODEKONVEYOR_SALES_ROLE = "kodekonveyor_sales";

  public static final String PAYMMENT_CHANNEL_SEPARATOR = ":";

  public static final String REGEX_PAYPAL = "^[A-Za-z0-9+_.-]+@(.+)$";

  public static final String REGEX_SEPA =
      "^[a-zA-Z]{4}[a-zA-Z]{2}[a-zA-Z0-9]{2}[XXX0-9]{0,3}";

  public static final String REGEX_TRANSFERWISE =
      "\\b[A-Z]{2}[0-9]{2}(?:[ ]?[0-9]{4}){4}(?!(?:[ ]?[0-9]){3})(?:[ ]?[0-9]{1,2})?\\b";

}
