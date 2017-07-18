package com.jim.util.reactive;

import com.jim.util.rest.client.ApacheRestClient;
import org.junit.Test;

/**
 * Created by jim.huang on 2017/07/17.
 */
public class ApacheRestClientTest {


    @Test
    public void testClassType() {
        ApacheRestClient<Stream> client = new ApacheRestClient();
        Class clazz = client.getResultClass();
        System.out.println(clazz.getName());
    }

}
