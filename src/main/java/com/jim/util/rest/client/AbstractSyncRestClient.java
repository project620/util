package com.jim.util.rest.client;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * Created by jim.huang on 2017/07/17.
 */
public abstract class AbstractSyncRestClient<R> implements SyncRestClient<R> {

    Class<R> resultClass;


    public Class<R> getResultClass() {
        Type genType = getClass().getGenericSuperclass();
        Type[] params = ((ParameterizedType) genType).getActualTypeArguments();
        resultClass = (Class<R>) params[0];
        return resultClass;
    }

}
