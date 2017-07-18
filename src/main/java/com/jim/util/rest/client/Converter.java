package com.jim.util.rest.client;

/**
 * Created by jim.huang on 2017/07/17.
 */
public interface Converter<T> {

    T toObject(String body);

    String toString(T t);

}
