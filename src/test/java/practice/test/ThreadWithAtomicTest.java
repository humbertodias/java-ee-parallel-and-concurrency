package practice.test;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ThreadWithAtomicTest {

    class CounterAtomic {
        private AtomicInteger count = new AtomicInteger();

        // we don't need synchronized here, because count++ now is threadSafe
        public void increment() {
            count.getAndIncrement();
        }

        public int getCount() {
            return count.intValue();
        }
    }

    @Test
    public void threadCount() throws InterruptedException {

        int expected = 1_000_000;
        CounterAtomic counter = new CounterAtomic();

        Runnable task = () -> {
            for (int i = 0; i < expected; i++) {
                counter.increment();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        int expected_after_two_started_threads = expected * 2;

        assertEquals(expected_after_two_started_threads, counter.getCount());


    }

}
