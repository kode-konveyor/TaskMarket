package com.kodekonveyor.market.proxies;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

@Service
@ExcludeFromCodeCoverage("interface to underlying library")
public class ObjectMapperService {

  private static final ObjectMapper mapper = new ObjectMapper(); //NOPMD

  static {
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public <ValueType> ValueType
      call(final InputStream content, final Class<ValueType> cls)
          throws IOException {
    return mapper.readValue(content, cls);
  }

}
