package com.kodekonveyor.market;

public class MarketConstants {

  public static final String CAN_BE_PAID_ROLE = "can_be_payed";

  public static final String CHANNEL_NAME_PAYPAL = "paypal";

  public static final String CHANNEL_NAME_SEPA = "sepa";

  public static final String CHANNEL_NAME_TRANSFERWISE = "transferwise";

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
