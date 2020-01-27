package com.kodekonveyor.market.register;

import javax.swing.JCheckBox;

public class RegisterTestData {

  public static final JCheckBox CHECKED_BOX =
      new JCheckBox("General Terms", true);

  public final static String INVALID_PAYMENT_CHANNEL =
      "sedpa:AAAABBC230";
  public final static String INVALID_PAYMENT_DETAILS1 = "sesepa:AAAAr2BBC230";
  public final static String INVALID_PAYPAL_PAYMENT_DETAILS =
      "paypal:john.bigbootexample.com";
  public final static String INVALID_SEPA_PAYMENT_DETAILS =
      "sepa:DE89370400440532013000";
  public final static String INVALID_TRANSFERWISE_PAYMENT_DETAILS =
      "transferwise:DE8937040044053201fjioe&3000";
  public static final String PAYMENT_DETAILS =
      "paypal:john.bigboot@example.com";

  public final static String PAYMENT_DETAILS_SEPA = "sepa:AAAABBC230";

  public final static String PAYMENT_DETAILS_TRANSFERWISE =
      "transferwise:DE89370400440532013000";
  public final static String PRIVATE_PROJECTNAME = "kode-konveyor/task-market";

  public final static String PROJECTNAME = "kode-konveyor/example";

  public final static String PROJECTROLE = "coder";

  public static final String PROJECTROLE_MANAGER = "manager";

  public static final String ROLE_KODEKONVEYOR_CONTRACT =
      "kodekonveyor_contract";

}
