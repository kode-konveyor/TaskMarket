package com.kodekonveyor.market;

import static org.junit.Assert.fail;

import java.util.Map;

public class TestHelper {

	public static void assertContains(String contained, String container) {
		if(!container.contains(contained)) {
			fail("no "+contained+" in "+container);
		}
	}

	public static void assertMapContainsAtKey(String value, String key, Map<String,String> map) {
		if(!map.get(key).equals(value)) {
			fail("no "+value+" for key "+key+" in "+ map);
		}
	}

}
