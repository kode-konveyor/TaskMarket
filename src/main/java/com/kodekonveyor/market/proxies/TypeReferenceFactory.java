package com.kodekonveyor.market.proxies;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.jsonpath.TypeRef;
import com.kodekonveyor.annotations.ExcludeFromCodeCoverage;

import java.lang.reflect.Type;
import java.util.List;

@ExcludeFromCodeCoverage("interface to underlying library")
public class TypeReferenceFactory {

    private static final TypeFactory typeFactory = TypeFactory.defaultInstance();

    public static <T> TypeRef<List<T>> constructListType(final Class<T> clazz) {
        return new TypeRef<>() {
            @Override
            public Type getType() {
                return typeFactory.constructCollectionType(List.class, clazz);
            }
        };
    }

}
