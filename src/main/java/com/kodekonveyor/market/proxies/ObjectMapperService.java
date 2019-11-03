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

  private static final ObjectMapper mapper = new ObjectMapper();

  public <ValueType> ValueType
      readValue(final URL url, final Class<ValueType> cls)
          throws JsonParseException, JsonMappingException, IOException {
    return mapper.readValue(url, cls);
  }

  public void
      configure(final DeserializationFeature feature, final boolean state) {
    mapper.configure(feature, state);
  }
}
