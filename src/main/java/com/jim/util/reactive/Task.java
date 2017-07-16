package com.jim.util.reactive;

/**
 * Created by jim.huang on 2017/07/13.
 */
public interface Task {


    /**
     * 向线程池提交任务，返回结果 T
     * @param <T>
     */
    interface Start<T> {
        T run();
    }

    /**
     * 接收上一个异步操作的结果，向线程池提交一个任务，返回另一个结果
     * @param <P> parameter : 接收的参数的类型
     * @param <R> result : 返回结果的数据类型
     */
    interface Process<P, R> {
        R run(P p);
    }

    /**
     * 接收上一个任务的结果，并且结束stream
     * @param <T>
     */
    interface End<T> {
        void run(T t);
    }


    /**
     * exception handler, not implement
     */
    interface onException {
        void run(Throwable e);
    }

}
