package com.jim.util.reactive;

import java.util.concurrent.*;

/**
 * Created by jim.huang on 2017/07/13.
 */
public class Executor {

    private static final ExecutorService executorService =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

    private static final ExecutorService singleService =
            Executors.newSingleThreadExecutor();

    public static <T> void execute(Task<T> task, ResultHandler<T> resultHandler) {
        Runnable runnable = () -> {
            CompletableFuture<T> future = CompletableFuture.supplyAsync(() -> task.run(), executorService);
            future.thenAccept(rs -> resultHandler.handle(rs));
        };
        singleService.execute(runnable);
    }

}
