package com.kodekonveyor.market.proxies;

import java.io.IOException;
import java.net.URL;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

@Service
@ExcludeFromCodeCoverage("interface to underlying library")
public class ObjectMapperService {

  private static final ObjectMapper mapper = new ObjectMapper(); //NOPMD

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public <ValueType> ValueType
      call(final URL url, final Class<ValueType> cls)
          throws JsonParseException, JsonMappingException, IOException {
    return mapper.readValue(url, cls);
  }

}
