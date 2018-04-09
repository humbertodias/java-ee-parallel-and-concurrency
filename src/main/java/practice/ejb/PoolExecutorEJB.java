package practice.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.enterprise.concurrent.ManagedThreadFactory;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Singleton
public class PoolExecutorEJB {

    private ExecutorService threadPoolExecutor = null;

    int corePoolSize = 5;

    int maxPoolSize = 10;

    long keepAliveTime = 5000;

    @Resource(name = "DefaultManagedThreadFactory")
    ManagedThreadFactory factory;

    public ExecutorService getThreadPoolExecutor() {

        return threadPoolExecutor;

    }

    @PostConstruct
    public void init() {

        threadPoolExecutor = new ThreadPoolExecutor(corePoolSize, maxPoolSize,

                keepAliveTime, TimeUnit.SECONDS,

                new ArrayBlockingQueue<Runnable>(10), factory);

    }

    @PreDestroy
    public void releaseResources() {

        threadPoolExecutor.shutdown();

    }

}