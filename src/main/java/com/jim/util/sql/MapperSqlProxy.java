package com.jim.util.sql;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @author jim.huang
 *
 */
public class MapperSqlProxy <T> implements InvocationHandler, Serializable{

    private static final long serialVersionUID = 3163780740865361950L;

    /**
     * java proxy
     */
    @Override
    public Object invoke(final Object proxyObject, final Method method, final Object[] params) throws Throwable {
        final String packageName = proxyObject.getClass().getName();
        final String methodName = method.getName();
        final String statementId = packageName + "." + methodName;
        final DynamicSql dynamicSql = new DynamicSql(statementId, params);
        final String sql = dynamicSql.getSql();
        System.out.println(sql);
        return null;
    }

}
