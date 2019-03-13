package top.gunplan.security;



import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GunExecutors {
    public GunExecutors() {
    }

    public static ExecutorService newFixedThreadPool(int size) {
        return new ThreadPoolExecutor(size, size, 0L, TimeUnit.MILLISECONDS, new ArrayBlockingQueue(16), Executors.defaultThreadFactory());
    }
}

