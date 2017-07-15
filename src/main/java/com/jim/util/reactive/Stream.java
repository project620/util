package com.jim.util.reactive;

import java.util.LinkedList;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by jim.huang on 2017/07/13.
 */
public class Stream {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final ExecutorService singleService =
            Executors.newSingleThreadExecutor();

    private LinkedList<CompletableFuture> linkedList = new LinkedList<>();

    public <T> Stream start(Task.Start<T> start) {
        Runnable runnable = () -> {
            CompletableFuture<T> future = CompletableFuture
                    .supplyAsync(start::run, executorService);
            linkedList.add(future);
        };
        singleService.execute(runnable);
        return this;
    }


    public <P, R> Stream process(Task.Process<P, R> process) {
        CompletableFuture<P> future = linkedList.peekLast();
        CompletableFuture<R> nextFuture;
        if (future == null) {
            nextFuture = CompletableFuture.supplyAsync(() -> process.run(null), executorService);
        } else {
            nextFuture = future.thenApplyAsync(process::run, executorService);
        }
        linkedList.addLast(nextFuture);
        return this;
    }

    public <T> Stream end(Task.End<T> end) {
        CompletableFuture<T> future = linkedList.peekLast();
        CompletableFuture<Void> nextFuture;
        if (future == null) {
            nextFuture = CompletableFuture.runAsync(() -> end.run(null), executorService);
        } else {
            nextFuture = future.thenAcceptAsync(end::run, executorService);
        }
        linkedList.addLast(nextFuture);
        return this;
    }

}
