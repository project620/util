package com.jim.util.reactive;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jim.huang on 2017/07/13.
 */
public class ExecutorTest {

    private static final String testStr = "hello world";

    @Test
    public void testExecute() {
        Executor.execute(() -> testStr, rs -> Assert.assertEquals(rs, testStr));
    }

}
