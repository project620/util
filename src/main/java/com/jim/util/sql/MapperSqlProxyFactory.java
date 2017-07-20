package com.jim.util.sql;

import java.lang.reflect.Proxy;

/**
 * @author jim.huang
 *
 */
public class MapperSqlProxyFactory<T> {

    @SuppressWarnings("unchecked")
    public T newInstance(final Class<?> interfaceClass) {
        final T t = (T)Proxy.newProxyInstance(interfaceClass.getClass().getClassLoader(),
                new Class[]{interfaceClass}, new MapperSqlProxy<>());
        return t;
    }

}
