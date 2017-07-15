package com.jim.util.reactive;

/**
 * Created by jim.huang on 2017/07/13.
 */
public interface Task {

    interface Start<T> {
        T run();
    }

    interface Process<P, R> {
        R run(P p);
    }

    interface End<T> {
        void run(T t);
    }


    interface ErrorHandler {
        void run(Throwable e);
    }

}
