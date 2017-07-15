package com.jim.util.reactive;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDateTime;

/**
 * Created by jim.huang on 2017/07/13.
 */
public class StreamTest {

    final String startMessage = "hello start";
    final int processMessage = 123456;
    final LocalDateTime endMessage = LocalDateTime.now();

    @Test
    public void testStart() {
        Stream stream = new Stream();
        stream.start(() -> {
            System.out.println("begin testStart");
            return startMessage;
        });
        stream.process(rs -> {
            System.out.println("testStart " + rs);
            Assert.assertEquals(startMessage, rs);
            return processMessage;
        });
        stream.end(rs -> {
            System.out.println("testStart " + rs);
            Assert.assertEquals(processMessage, rs);
        });
    }


    @Test
    public void testProcess() {
        Stream stream = new Stream();
        stream.process(rs -> {
            System.out.println("begin testProcess");
            return startMessage;
        });
        stream.process(rs -> {
            System.out.println("testProcess " + rs);
            Assert.assertEquals(startMessage, rs);
            return processMessage;
        });
        stream.process(rs -> {
            System.out.println("testProcess " + rs);
            Assert.assertEquals(processMessage, rs);
            return endMessage;
        });
        stream.end(rs -> {
            System.out.println("testProcess " + rs);
            Assert.assertEquals(endMessage, rs);
        });
    }

    @Test
    public void testEnd() {
        Stream stream = new Stream();
        stream.end(rs -> {
            System.out.println("begin testEnd");
            Assert.assertNull(rs);
        });
    }

    @Test
    public void testException() {
        Stream stream = new Stream();
        stream.start(() -> {
            System.out.println("begin testException");
            throw new NullPointerException();
        });
        stream.process(rs -> {
            System.out.println("testException " + rs);
            Assert.assertEquals(startMessage, rs);
            return processMessage;
        });
        stream.end(rs -> {
            System.out.println("testException " + rs);
            Assert.assertEquals(processMessage, rs);
        });
    }
}
