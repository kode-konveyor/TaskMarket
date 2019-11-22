package com.kodekonveyor.market;

import org.slf4j.MDC;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.annotations.InterfaceClass;

@InterfaceClass
@ExcludeFromCodeCoverage("wrapper")
public class SlfMDCWrapper {

  public void put(final String key, final String val) {
    MDC.put(key, val);
  }

  public void clear() {
    MDC.clear();
  }
}
