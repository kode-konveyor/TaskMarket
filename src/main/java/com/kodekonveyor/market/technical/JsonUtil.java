package com.kodekonveyor.market.technical;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.Option;
import com.jayway.jsonpath.TypeRef;
import com.jayway.jsonpath.spi.mapper.JacksonMappingProvider;

import java.lang.reflect.Type;
import java.util.List;

public class JsonUtil {

    private static Configuration jaywayConfig = Configuration.builder()
            .options(Option.SUPPRESS_EXCEPTIONS)
            .mappingProvider(new JacksonMappingProvider())
            .build();

    public static <T> T readPath(final DocumentContext gqlResponse, final String path, final Class<T> clazz) {
        return JsonPath.parse(
                gqlResponse.jsonString(),
                jaywayConfig
        )
                .read(path, clazz);
    }

    public static <T> List<T> readPathAsList(final DocumentContext gqlResponse, final String path, final Class<T> clazz) {
        return JsonPath.parse(
                gqlResponse.jsonString(),
                jaywayConfig
        )
                .read(path, constructListType(clazz));
    }

    public static <T> TypeRef<List<T>> constructListType(final Class<T> clazz) {
        return new TypeRef<>() {
            @Override
            public Type getType() {
                return TypeFactory.defaultInstance().constructCollectionType(List.class, clazz);
            }
        };
    }
}
