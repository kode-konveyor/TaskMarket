package com.kodekonveyor.market;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Service;

import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("legacy factory")
public class MDCFactory implements FactoryBean<SlfMDCWrapper> {

  private static SlfMDCWrapper mdc = new SlfMDCWrapper();

  @Override
  public SlfMDCWrapper getObject() throws Exception {
    return mdc;
  }

  @Override
  public Class<SlfMDCWrapper> getObjectType() {
    return SlfMDCWrapper.class;
  }

}
