package com.jim.util.rest.client;

import java.util.Map;

/**
 * Created by jim.huang on 2017/07/17.
 */

/**
 * @param <R> result
 */
public interface SyncRestClient<R> {

    R doGet(String url, Map<String, Object> headers) throws RestClientException;

    R doPost(String url, Map<String, Object> headers, Object body) throws RestClientException;

    R doDelete(String url, Map<String, Object> headers) throws RestClientException;

    R doPut(String url, Map<String, Object> headers) throws RestClientException;

}
