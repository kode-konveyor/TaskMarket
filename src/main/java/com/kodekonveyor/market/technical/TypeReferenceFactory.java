package com.kodekonveyor.market.technical;

import com.fasterxml.jackson.databind.type.TypeFactory;
import com.jayway.jsonpath.TypeRef;

import java.lang.reflect.Type;
import java.util.List;

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
