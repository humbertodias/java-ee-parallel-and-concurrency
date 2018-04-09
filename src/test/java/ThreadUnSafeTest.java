import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ThreadUnSafeTest {

    class Counter{
        private int count = 0;
        // synchronized, because count++ is not thread safe
        public synchronized void increment(){
            count++;
        }
        public int getCount(){
            return count;
        }
    }

    @Test
    public void threadCount() throws InterruptedException {

        int expected = 1_000_000;
        Counter counter = new Counter();

        Runnable task = () -> {
            for(int i=0; i < expected; i++) {
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
