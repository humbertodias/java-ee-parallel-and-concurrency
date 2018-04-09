package practice.task;

public class SimpleTask implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread started.");
    }

}