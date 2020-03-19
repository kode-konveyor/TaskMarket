package com.kodekonveyor.market;

import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.Map;

public class GeneralTestHelper {

  private static final String FOR_KEY_TEMPLATE = "%s for key %s";
  private static final String NO_IN_TEMPLATE = "no %s in %s";

  public static void
      assertContains(final String contained, final String container) {
    if (!container.contains(contained))
      fail(String.format(NO_IN_TEMPLATE, contained, container));
  }

  public static void assertMapContainsAtKey(
      final String value, final String key, final Map<String, String> map
  ) {
    if (!map.get(key).equals(value))
      fail(
          String.format(
              NO_IN_TEMPLATE, String.format(FOR_KEY_TEMPLATE, value, key), map
          )
      );
  }

  public static void
      assertContains(final String contained, final List<String> allValues) {
    for (final String value : allValues)
      if (value.contains(contained))
        return;
    fail(String.format(NO_IN_TEMPLATE, contained, allValues));
  }

}
