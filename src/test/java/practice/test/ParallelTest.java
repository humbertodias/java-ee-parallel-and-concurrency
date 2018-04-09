package practice.test;

import org.junit.jupiter.api.Test;
import practice.Util;

import java.util.concurrent.*;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParallelTest {

    IntStream range = IntStream.rangeClosed(1, 10);

    @Test
    public void sum(){
        assertEquals(55, range.sum());
    }

    @Test
    public void sumParallel(){
        assertEquals(55, range.parallel().sum());
    }

    @Test
    public void count(){
        assertEquals(10, range.count());
    }

    @Test
    public void tasksInWithNameParallel() throws ExecutionException, InterruptedException {

        Callable<String> cTask1 = () -> {
            Util.delay(2);
            return Thread.currentThread().getName();
        };

        Callable<String> cTask2 = () -> {
            Util.delay(3);
            return Thread.currentThread().getName();
        };

        Callable<String> cTask3 = () -> {
            Util.delay(3);
            return Thread.currentThread().getName();
        };

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Future<String> submit1 = executorService.submit(cTask1);
        Future<String> submit2 = executorService.submit(cTask2);
        Future<String> submit3 = executorService.submit(cTask3);

        long begin = System.currentTimeMillis();

        String s1 = submit1.get();
        String s2 = submit2.get();
        String s3 = submit3.get();

        long end = System.currentTimeMillis();
        long timeSpend = end - begin;

        assertTrue(submit1.isDone());
        assertTrue(submit2.isDone());
        assertTrue(submit3.isDone());

        assertTrue(timeSpend >= 3000 && timeSpend <= 3500);

        assertTrue(s1.contains("thread-1"));
        assertTrue(s2.contains("thread-2"));
        assertTrue(s3.contains("thread-3"));

        executorService.shutdown();

    }

    @Test
    public void tasksInParallel() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(3);

        Runnable task1 = () -> { Util.delay(2); };
        Runnable task2 = () -> { Util.delay(3); };
        Runnable task3 = () -> { Util.delay(3); };

        long begin = System.currentTimeMillis();

        Future<?> submit1 = executorService.submit(task1);
        Future<?> submit2 = executorService.submit(task2);
        Future<?> submit3 = executorService.submit(task3);

        submit1.get();
        submit2.get();
        submit3.get();

        long end = System.currentTimeMillis();
        long timeSpend = end - begin;

        assertTrue(submit1.isDone());
        assertTrue(submit2.isDone());
        assertTrue(submit3.isDone());

        assertTrue(timeSpend >= 3000 && timeSpend <= 3500);

        executorService.shutdown();

    }

    @Test
    public void tasksOldWaySequencial() {
        Runnable task1 = () -> Util.delay(2);
        Runnable task2 = () -> Util.delay(3);
        Runnable task3 = () -> Util.delay(3);

        long begin = System.currentTimeMillis();
        new Thread(task1).run();
        new Thread(task2).run();
        new Thread(task3).run();
        long end = System.currentTimeMillis();
        long timeSpend = end - begin;
        assertTrue(timeSpend >= 7000 && timeSpend <= 8500);
    }


}
