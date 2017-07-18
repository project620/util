package com.jim.util.rest.client;

/**
 * Created by jim.huang on 2017/07/17.
 */
public class RestClientException extends RuntimeException {


    private int statusCode;

    public static final int EXECUTE_ERROR = 600;
    public static final int CONSUME_ERROR = 601;


    public RestClientException(int statusCode) {

        this(statusCode, null, null);
    }


    public RestClientException(int statusCode, String message) {
        this(statusCode, message, null);
    }


    public RestClientException(int statusCode, Throwable e) {
        this(statusCode, null, e);
    }


    public RestClientException(int statusCode, String message, Throwable e) {
        super(message, e);
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }

}
