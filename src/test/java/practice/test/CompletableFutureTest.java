package practice.test;

import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CompletableFutureTest {

    private static int currentId;

    @BeforeClass
    public void setUp() {
        currentId = 0;
   }

    private CompletableFuture<Integer> getNextIdAsync() {
        return CompletableFuture.completedFuture(nextId());
    }

    private Integer nextId(){
        Util.delay();
        return currentId++;
    }

    @Test
    public void nextFutureTest() throws ExecutionException, InterruptedException {

        Integer expected0 = 0;
        Integer expected1 = 1;
        Integer expected2 = 2;

        CompletableFuture<Integer> nextIdAsync0 = getNextIdAsync();
        CompletableFuture<Integer> nextIdAsync1 = getNextIdAsync();
        CompletableFuture<Integer> nextIdAsync2 = getNextIdAsync();

        assertEquals(expected0, nextIdAsync0.get());
        assertEquals(expected1, nextIdAsync1.get());
        assertEquals(expected2, nextIdAsync2.get());

    }

}
