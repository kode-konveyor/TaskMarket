package com.kodekonveyor.market.proxies;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import com.kodekonveyor.market.MarketConstants;

@Service
@ExcludeFromCodeCoverage("interface to underlying library")
public class ObjectMapperService {

  public void //NOPMD
      configure(final DeserializationFeature feature, final boolean state) {
    MarketConstants.mapper.configure(feature, state);
  }

  public <ValueType> ValueType //NOPMD
      readValue(final URL url, final Class<ValueType> cls)
          throws JsonParseException, JsonMappingException, IOException {
    return MarketConstants.mapper.readValue(url, cls);
  }
}
