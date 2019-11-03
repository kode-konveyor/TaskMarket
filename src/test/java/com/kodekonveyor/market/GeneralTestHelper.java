package com.kodekonveyor.market;

import static org.assertj.core.api.Assertions.fail;

import java.util.List;
import java.util.Map;

public class GeneralTestHelper {

  public static void
      assertContains(final String contained, final String container) {
    if (!container.contains(contained))
      fail("no " + contained + " in " + container);
  }

  public static void assertMapContainsAtKey(
      final String value, final String key, final Map<String, String> map
  ) {
    if (!map.get(key).equals(value))
      fail("no " + value + " for key " + key + " in " + map);
  }

  public static void
      assertContains(final String contained, final List<String> allValues) {
    for (final String value : allValues)
      if (value.contains(contained))
        return;
    fail("no " + contained + " in " + allValues);
  }

}
