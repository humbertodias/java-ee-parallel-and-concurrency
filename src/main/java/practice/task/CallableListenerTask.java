package practice.task;

import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.concurrent.ManagedTask;
import javax.enterprise.concurrent.ManagedTaskListener;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;

public class CallableListenerTask implements Callable<Long>, ManagedTask, ManagedTaskListener {

    private int id;

    public CallableListenerTask(int id) {
        this.id = id;
    }

    public Long call() {

        long summation = 0;

        for (int i = 1; i <= id; i++) {
            summation += i;
        }

        return summation;

    }

    public void taskSubmitted(Future<?> f, ManagedExecutorService es, Object obj) {
        System.out.println("Task Submitted! " + f);

    }

    public void taskDone(Future<?> f, ManagedExecutorService es, Object obj, Throwable exc) {
        System.out.println("Task DONE! " + f);
    }

    public void taskStarting(Future<?> f, ManagedExecutorService es, Object obj) {
        System.out.println("Task Starting! " + f);
    }

    public void taskAborted(Future<?> f, ManagedExecutorService es, Object obj, Throwable exc) {
        System.out.println("Task Aborted! " + f);
    }

    @Override
    public ManagedTaskListener getManagedTaskListener() {
        return this;
    }

    @Override
    public Map<String, String> getExecutionProperties() {
        return null;
    }
}