package com.jim.util.rest.client;

import org.apache.http.HttpEntity;
import org.apache.http.StatusLine;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by jim.huang on 2017/07/17.
 * 依赖jar包 <org.apache.httpcomponents, httpclient, 4.5.3>
 */
public class ApacheRestClient<R> extends AbstractSyncRestClient<R> {

    private static CloseableHttpClient httpClient;


    /**
     * use default pool setting
     *
     * @TODO make connection pool configurable
     */
    public ApacheRestClient() {
        PoolingHttpClientConnectionManager connectionManager = new PoolingHttpClientConnectionManager();
        httpClient = HttpClients.custom().setConnectionManager(connectionManager).build();
    }


    @Override
    public R doGet(String url, Map<String, Object> headers) {
        return null;
    }

    @Override
    public R doPost(String url, Map<String, Object> headers, Object body) {
        return null;
    }

    @Override
    public R doDelete(String url, Map<String, Object> headers) {
        return null;
    }

    @Override
    public R doPut(String url, Map<String, Object> headers) {
        return null;
    }


    /**
     * @param requestBase
     * @return String result
     * @throws RestClientException throw when fail to execute http request, or get error status code,
     *                             or fail to consume the response stream
     */
    private String runInClient(HttpRequestBase requestBase) throws RestClientException {
        String requestMessage = requestBase.getMethod() + " on " + requestBase.getURI().getPath();
        try {
            CloseableHttpResponse response = httpClient.execute(requestBase);
            StatusLine statusLine = response.getStatusLine();
            int statusCode = statusLine.getStatusCode();
            if (statusCode % 100 > 3) {
                throw new RestClientException(statusCode, "get error status code when execute " + requestMessage);
            }
            HttpEntity httpEntity = response.getEntity();
            return consumeHttpEntity(httpEntity, requestMessage);
        } catch (IOException e) {
            throw new RestClientException(RestClientException.EXECUTE_ERROR, "fail to execute " + requestMessage, e);
        } finally {
            requestBase.releaseConnection();
        }
    }

    private String consumeHttpEntity(HttpEntity httpEntity, String requestMessage) throws RestClientException {
        InputStream inputStream = null;
        try {
            inputStream = httpEntity.getContent();
            int size = inputStream.available();
            byte[] bytes = new byte[size];
            inputStream.read(bytes);
            return new String(bytes);
        } catch (IOException e) {
            throw new RestClientException(RestClientException.CONSUME_ERROR,
                    "fail to consume http result: " + requestMessage, e);
        }
    }

}
