import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({ParallelTest.class, StreamsTest.class, SortTest.class, ThreadUnSafeTest.class, ThreadWithAtomicTest.class, CompletableFutureTest.class, ReduceTest.class})
public class AllTests {
}
