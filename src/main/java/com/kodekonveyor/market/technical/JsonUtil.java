package com.kodekonveyor.market.technical;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

public class JsonUtil {

    private static Configuration jaywayConfig = Configuration.builder()
            .options(Option.SUPPRESS_EXCEPTIONS)
            .mappingProvider(new JacksonMappingProvider())
            .build();

    public static <T> T readPath(final DocumentContext gqlResponse, final String path, final TypeRef<T> typeRef) {
        return JsonPath.parse(
                gqlResponse.jsonString(),
                jaywayConfig
        )
                .read(path, typeRef);
    }
}
