package com.jim.util.rest.client;

/**
 * Created by jim.huang on 2017/07/17.
 */
public class ConverterFactory {

    private ConverterFactory() {

    }


    public static ConverterFactory getInstance() {
        return new ConverterFactory();
    }

    public Converter getConverter(Object t) {
        return null;
    }

}
