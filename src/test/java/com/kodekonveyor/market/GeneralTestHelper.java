package com.kodekonveyor.market;

import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.Map;

public class GeneralTestHelper {

  private static final String FOR_KEY = " for key ";
  private static final String IN = " in ";
  private static final String NO = "no ";

  public static void
      assertContains(final String contained, final String container) {
    if (!container.contains(contained))
      fail(NO + contained + IN + container);
  }

  public static void assertMapContainsAtKey(
      final String value, final String key, final Map<String, String> map
  ) {
    if (!map.get(key).equals(value))
      fail(NO + value + FOR_KEY + key + IN + map);
  }

  public static void
      assertContains(final String contained, final List<String> allValues) {
    for (final String value : allValues)
      if (value.contains(contained))
        return;
    fail(NO + contained + IN + allValues);
  }

}
